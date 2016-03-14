package com.urlable.redirectapi.server

import com.urlable.redirectapi.restapi.tdk.BaseFunSpecTest

class ServerIT extends BaseFunSpecTest {

  describe("starting in current thread") {

    it("should not throw") {

      /** arrange/act/assert **/

      Server.main(Array())

    }

  }

}
