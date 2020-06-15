package com.knoldus.assignment.router

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, MessageEntity}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.PostRequest
import org.scalatest.{FlatSpec, Matchers}

class CalculatorRouteSpec extends FlatSpec with ScalatestRouteTest with Matchers with JsonSupport{

  val entity = Marshal(PostRequest(Constants.OPERANDS, Constants.OPERATOR)).to[MessageEntity].futureValue
  /*"""{
                  |	"operands":[111, 6,4],
                  |	"operator":"*"
                  |}""".stripMargin*/
  behavior of "route in CalculatorRoute"
    it should("return value after performing the operation.") in {
      Post("https://localhost:8080/calculator").withEntity(entity) ~> CalculatorRoute.route ~> check {
        response shouldEqual 88
      }
  }
}
