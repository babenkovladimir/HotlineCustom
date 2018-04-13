package com.example.vladimirbabenko.hotlinecustom.data.cashe

import android.content.Context
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook

class CasheNotebook(val context: Context, val key1:String, val key2:String) : BaseCashe<NoteBook, NoteBook>(context, key1, key2)