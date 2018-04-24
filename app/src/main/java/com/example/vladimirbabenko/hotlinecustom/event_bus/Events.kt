package com.example.vladimirbabenko.hotlinecustom.event_bus

class Events {

  class BascketEvent(val increment:Int? = 0)

  class CarFragmentRefresh()

  class NotebookFragmentRefresh()

  class PriceCorrectionEvent(increment : Int)
}