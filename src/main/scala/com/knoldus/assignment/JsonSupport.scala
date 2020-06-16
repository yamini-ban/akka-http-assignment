package com.knoldus.assignment

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.assignment.model.{HelloResponse, CalculatorRequest}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val getrequestFormat = jsonFormat2(HelloResponse)
  implicit val postrequestFormat = jsonFormat2(CalculatorRequest)
}
