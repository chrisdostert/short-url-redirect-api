package com.urlable.redirectapi.restapi

import akka.actor.ActorSystem
import com.urlable.redirectapi.restapi.json.JsonSupport
import com.urlable.redirectapi.restapi.routes.RedirectRoute
import com.urlable.shorturlsvcsdk.ShortUrlSvcSdk
import com.softwaremill.macwire._

private[restapi]
class CompositionRoot(
  val shortUrlSvcSdk: ShortUrlSvcSdk,
  val system: ActorSystem
) {

  val jsonSupport = JsonSupport

  val redirectRoute: RedirectRoute = wire[RedirectRoute]

}
