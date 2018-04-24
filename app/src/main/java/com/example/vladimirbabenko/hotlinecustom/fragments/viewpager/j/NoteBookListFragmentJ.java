package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.j;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.vladimirbabenko.hotlinecustom.R;
import com.example.vladimirbabenko.hotlinecustom.data.DataManager;
import com.example.vladimirbabenko.hotlinecustom.fragments.viewpager.notebook_fragment.NoteBookRecyclerViewAdapter;
import java.util.List;

public class NoteBookListFragmentJ extends Fragment {

  private DataManager mDataManager;

  public static final String title = "NoteBooks";
  @BindView(R.id.rvNoteBookRecyclerView) RecyclerView recyclerView;

  public static NoteBookListFragmentJ newInstance(Bundle bundle) {
     NoteBookListFragmentJ fragment = new NoteBookListFragmentJ();
    Bundle args = bundle;
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDataManager = DataManager.Companion.getCreate();
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_notebook_list, container,false);
    ButterKnife.bind(this,view);

    NoteBookRecyclerViewAdapter adapter = new NoteBookRecyclerViewAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayout.VERTICAL, false));

    recyclerView.setAdapter(adapter);
    //adapter.setNoteBooks(mDataManager.fetchMocks(),new list); // This is old Mock

    return view;
  }
}
