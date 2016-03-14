package com.urlable.redirectapi.restapi.json

import java.net.URL
import com.urlable.redirectapi.restapi.tdk.BaseFunSpecTest
import spray.json._

class JsonSupportTest
  extends BaseFunSpecTest {

  describe("importing JsonSupport") {

    val jsonSupport = new JsonSupport {}
    import jsonSupport._

    it("should enable round tripping an instance of URL to JSON and back") {

      /** arrange **/
      val expectedUrl =
        new URL("http://google.com")

      /** act **/
      val actualUrl =
        expectedUrl
          .toJson
          .convertTo[URL]

      /** assert **/
      assert(actualUrl == expectedUrl)

    }

  }

}
