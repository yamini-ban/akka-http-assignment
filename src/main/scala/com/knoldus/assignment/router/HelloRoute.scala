package com.knoldus.assignment.router

import akka.http.scaladsl.server.Directives.{complete, get, parameters, path}
import akka.http.scaladsl.server.Route
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.GetResponse

object HelloRoute extends JsonSupport{
  val route: Route = {
    get {
      path("hello") {
        parameters('name, 'message) {
          (name, message) => {
            complete(GetResponse(name, message))
          }
        }
      }
    }
  }
}
