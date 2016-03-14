package com.chrisdostert.shorturlredirectapi.restapi

import akka.actor.ActorSystem
import com.chrisdostert.shorturlredirectapi.restapi.json.JsonSupport
import com.chrisdostert.shorturlredirectapi.restapi.routes.RedirectRoute
import com.chrisdostert.shorturlsvcsdk.ShortUrlSvcSdk
import com.softwaremill.macwire._

private[restapi]
class CompositionRoot(
  val shortUrlSvcSdk: ShortUrlSvcSdk,
  val system: ActorSystem
) {

  val jsonSupport = JsonSupport

  val redirectRoute: RedirectRoute = wire[RedirectRoute]

}
