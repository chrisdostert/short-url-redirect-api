package com.urlable.redirectapi.server

import com.softwaremill.macwire._
import com.typesafe.config.{Config, ConfigFactory}
import com.urlable.redirectapi.restapi.ReSTApi
import com.urlable.shorturlsvcsdk.{ShortUrlSvcSdk, ShortUrlSvcSdkConfig}
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

private[redirectapi]
class CompositionRoot {

  private val config: Config =
    ConfigFactory.load()

  private val shortUrlSvcSdkConfig: ShortUrlSvcSdkConfig =
    config.as[ShortUrlSvcSdkConfig]("short-url-svc-sdk")

  private lazy val shortUrlSvcSdk = wire[ShortUrlSvcSdk]

  lazy val restApi: ReSTApi = wire[ReSTApi]

}
