package com.chrisdostert.shorturlredirectapi.server

import com.chrisdostert.shorturlredirectapi.restapi.ReSTApi
import com.chrisdostert.shorturlsvcsdk.{ShortUrlSvcSdk, ShortUrlSvcSdkConfig}
import com.softwaremill.macwire._
import com.typesafe.config.{Config, ConfigFactory}
import net.ceedubs.ficus.Ficus._
import net.ceedubs.ficus.readers.ArbitraryTypeReader._

private[shorturlredirectapi]
class CompositionRoot {

  private val config: Config =
    ConfigFactory.load()

  private val shortUrlSvcSdkConfig: ShortUrlSvcSdkConfig =
    config.as[ShortUrlSvcSdkConfig]("short-url-svc-sdk")

  private lazy val shortUrlSvcSdk = wire[ShortUrlSvcSdk]

  lazy val restApi: ReSTApi = wire[ReSTApi]

}
