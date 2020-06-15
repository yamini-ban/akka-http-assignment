package com.knoldus.assignment

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.knoldus.assignment.model.{GetResponse, PostRequest}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val getrequestFormat = jsonFormat2(GetResponse)
  implicit val postrequestFormat = jsonFormat2(PostRequest)
}
