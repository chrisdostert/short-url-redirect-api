package com.urlable.redirectapi.restapi

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.stream.ActorMaterializer
import com.urlable.shorturlsvcsdk.ShortUrlSvcSdk

class ReSTApi(
  private val shortUrlSvcSdk: ShortUrlSvcSdk
) {

  implicit val system =
    ActorSystem("short-url-api")

  implicit val actorMaterializer = ActorMaterializer()

  implicit val dispatcher = system.dispatcher

  val compositionRoot: CompositionRoot =
    new CompositionRoot(
      shortUrlSvcSdk = shortUrlSvcSdk,
      system = system
    )

  val routeWithLogging =
    DebuggingDirectives.logRequestResult(
      "",
      Logging.InfoLevel
    )(
      compositionRoot.redirectRoute.route
    )

  val bindToInterface = "0.0.0.0"
  val bindToPort = 8080

  val bindingFuture =
    Http()
      .bindAndHandle(
        handler = routeWithLogging,
        interface = bindToInterface,
        port = bindToPort
      )

}
