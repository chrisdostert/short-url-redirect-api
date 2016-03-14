package com.urlable.redirectapi.server

object Server extends App {

  private val compositionRoot =
    new CompositionRoot()

  compositionRoot
    .restApi

}
