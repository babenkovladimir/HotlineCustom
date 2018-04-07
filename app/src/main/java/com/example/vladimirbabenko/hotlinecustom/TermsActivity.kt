package com.example.vladimirbabenko.hotlinecustom

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ProgressBar
import com.example.vladimirbabenko.hotlinecustom.base.BaseActivity
import kotlinx.android.synthetic.main.activity_terms.btTermsClose
import kotlinx.android.synthetic.main.activity_terms.pbColntentLoadingProgressBar
import kotlinx.android.synthetic.main.activity_terms.wvTerms

class TermsActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_terms)

    setupUI()
  }

  private fun setupUI() {

    pbColntentLoadingProgressBar.visibility = ProgressBar.VISIBLE

    val timer:CountDownTimer = object: CountDownTimer(1000, 1000){
      override fun onTick(p0: Long) {
      }

      override fun onFinish() {
        wvTerms.loadUrl("file:///android_asset/Latin-Lipsum.html")
        pbColntentLoadingProgressBar.visibility = ProgressBar.INVISIBLE
      }
    }
    timer.start()
    btTermsClose.setOnClickListener({
      finish()
    })
  }
}
