package com.example.vladimirbabenko.hotlinecustom.event_bus

sealed class Events {

  class BascketEvent(val increment:Int? = 0)

  class CarFragmentRefresh()

  class NotebookFragmentRefresh()

  class VideoCardRefreshFragment()

  class PriceCorrectionEvent(increment : Int)
}