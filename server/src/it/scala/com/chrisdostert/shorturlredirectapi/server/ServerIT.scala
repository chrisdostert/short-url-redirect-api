package com.chrisdostert.shorturlredirectapi.server

import com.chrisdostert.shorturlredirectapi.restapi.tdk.BaseFunSpecTest

class ServerIT extends BaseFunSpecTest {

  describe("starting in current thread") {

    it("should not throw") {

      /** arrange/act/assert **/

      Server.main(Array())

    }

  }

}
