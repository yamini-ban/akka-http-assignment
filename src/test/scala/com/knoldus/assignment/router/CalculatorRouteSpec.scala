package com.knoldus.assignment.router

import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, MessageEntity}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.CalculatorRequest
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures._

class CalculatorRouteSpec extends FlatSpec with ScalatestRouteTest with Matchers with JsonSupport{

  val entity = Marshal(CalculatorRequest(Constants.OPERANDS, Constants.OPERATOR)).to[MessageEntity].futureValue
  val invalidOperands = Marshal(CalculatorRequest(Constants.INVALID_OPERANDS, Constants.OPERATOR)).to[MessageEntity].futureValue
  val invalidOperation = Marshal(CalculatorRequest(Constants.OPERANDS, Constants.INVALID_OPERATOR)).to[MessageEntity].futureValue

  behavior of "route in CalculatorRoute"
  it should("return value after performing the operation.") in {
    Post("https://localhost:8080/calculator").withEntity(entity) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "88"
    }
  }

  it should("return a message if there are less than two operands.") in {
    Post("https://localhost:8080/calculator").withEntity(invalidOperands) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual "Please provide at least two operands."
    }
  }

  it should("return operation not supported for any invalid operation.") in {
    Post("https://localhost:8080/calculator").withEntity(invalidOperation) ~> CalculatorRoute.route ~> check {
      responseAs[String] shouldEqual s"${Constants.INVALID_OPERATOR} not supported."
    }
  }
}
