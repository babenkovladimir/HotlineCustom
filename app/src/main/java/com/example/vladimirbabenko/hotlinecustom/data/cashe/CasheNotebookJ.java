package com.example.vladimirbabenko.hotlinecustom.data.cashe;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CasheNotebookJ {

  private String PREFS_KEY, jsonKey;
  private SharedPreferences prefs;
  private Gson gson;

  public CasheNotebookJ(Context context, String PREFS_KEY, String jsonKey) {
    gson = new Gson();
    this.jsonKey = jsonKey;
    this.PREFS_KEY = PREFS_KEY;
    prefs = context.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
  }

  public void saveList(List<NoteBook> list) {
    String json = gson.toJson(list);
    prefs.edit().putString(jsonKey, json).apply();
  }

  public List<NoteBook> getList() {
    String jsonToParce = prefs.getString(jsonKey, "NoNe string");
    Type type = new TypeToken<List<NoteBook>>() {}.getType();
    return gson.fromJson(jsonToParce, type);
  }
}

