package com.urlable.redirectapi.restapi.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.urlable.redirectapi.restapi.json.JsonSupport
import com.urlable.redirectapi.restapi.tdk.BaseFunSpecTest
import com.urlable.redirectapi.restapi.tdk.testobjects.ATest
import com.urlable.shorturlsvcsdk.ShortUrlSvcSdk
import org.mockito.Mockito._

import scala.concurrent.Future

class RedirectRouteTest
  extends BaseFunSpecTest
    with ScalatestRouteTest {

  private val jsonSupport = JsonSupport

  describe(s"GET /:id") {

    it("should return a Location header with value equal to target returned from short url svc") {

      /** arrange **/
      val shortUrlReturnedFromShortUrlSvcSdk =
        ATest.shortUrlView.nonNull

      val providedShortUrlId =
        ATest.string.nonEmpty

      val mockShortUrlSvcSdk = mock[ShortUrlSvcSdk]
      when(mockShortUrlSvcSdk.getShortUrlWithId(providedShortUrlId))
        .thenReturn(Future.successful(shortUrlReturnedFromShortUrlSvcSdk))

      val objectUnderTest =
        new RedirectRoute(
          jsonSupport,
          mockShortUrlSvcSdk
        )

      /** act/assert **/
      Get(
        s"/$providedShortUrlId"
      ) ~>
        objectUnderTest.route ~>
        check {
          header("Location").map(_.value()) shouldEqual Some(shortUrlReturnedFromShortUrlSvcSdk.target.toString)
        }

    }

    it("should return a StatusCode of 302") {

      /** arrange **/
      val shortUrlReturnedFromShortUrlSvcSdk =
        ATest.shortUrlView.nonNull

      val providedShortUrlId =
        ATest.string.nonEmpty

      val mockShortUrlSvcSdk = mock[ShortUrlSvcSdk]
      when(mockShortUrlSvcSdk.getShortUrlWithId(providedShortUrlId))
        .thenReturn(Future.successful(shortUrlReturnedFromShortUrlSvcSdk))

      val objectUnderTest =
        new RedirectRoute(
          jsonSupport,
          mockShortUrlSvcSdk
        )

      /** act/assert **/
      Get(
        s"/$providedShortUrlId"
      ) ~>
        objectUnderTest.route ~>
        check {
          status shouldEqual StatusCodes.Found
        }

    }

  }
}
