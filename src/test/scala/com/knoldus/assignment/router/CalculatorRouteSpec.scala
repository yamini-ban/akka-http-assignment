package com.knoldus.assignment.router

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.PostRequest
import org.scalatest.{FlatSpec, Matchers}

class CalculatorRouteSpec extends FlatSpec with ScalatestRouteTest with Matchers with JsonSupport{

  val req = HttpRequest(
    method = HttpMethods.POST,
    uri = "https://localhost:8080/calculator",
    entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, jsonFormat1(PostRequest(Constants.OPERANDS, Constants.OPERATOR)))
  )

  /*"""{
                  |	"operands":[111, 6,4],
                  |	"operator":"*"
                  |}""".stripMargin*/
  behavior of "route in CalculatorRoute"
    it should("return value after performing the operation.") in {
      req ~> CalculatorRoute.route ~> check {
        responseAs[String] shouldEqual("88")
      }
  }
}
