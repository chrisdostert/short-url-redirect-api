package com.chrisdostert.shorturlredirectapi.server

object Server extends App {

  private val compositionRoot =
    new CompositionRoot()

  compositionRoot
    .restApi

}
