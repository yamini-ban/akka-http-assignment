package com.knoldus.assignment.router

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FlatSpec, Matchers}

class HelloRouteSpec extends FlatSpec with ScalatestRouteTest with Matchers{
  it should("return json form of parameters 'name' and 'message'") in {
    Get("/hello?name=yamini&message=hello") ~> HelloRoute.route ~> check {
      responseAs[String] shouldEqual """{"message":"hello","name":"yamini"}""".stripMargin
    }
  }
}
