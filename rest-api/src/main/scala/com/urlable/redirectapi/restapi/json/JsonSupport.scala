package com.urlable.redirectapi.restapi.json

import java.net.URL

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.urlable.redirectapi.restapi.json.formats.UrlFormat
import spray.json._

trait JsonSupport
  extends SprayJsonSupport
    with DefaultJsonProtocol {

  implicit val urlFormat: RootJsonFormat[URL] = UrlFormat

}

object JsonSupport extends JsonSupport
