package com.example.vladimirbabenko.hotlinecustom.fragments.viewpager

import android.os.AsyncTask
import com.example.vladimirbabenko.hotlinecustom.entity.NoteBook
import com.example.vladimirbabenko.hotlinecustom.fragments.NoteBookFragment
import java.lang.ref.WeakReference

class MyAsyncLoader:AsyncTask<Void, Int, NoteBook>() {

  lateinit var view: WeakReference<NoteBookFragment>

  override fun onPreExecute() {
    super.onPreExecute()
  }

  /*
  * Do downloading in background and return new NoteBook to
   */

  override fun doInBackground(vararg params: Void?): NoteBook {

    TODO()
  }
}