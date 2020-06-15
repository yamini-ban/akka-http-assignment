package com.knoldus.assignment.router

import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.http.scaladsl.server.Route
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.PostRequest

object CalculatorRoute extends JsonSupport {
  val route: Route = {
    post {
      path("calculator") {
        entity(as[PostRequest]) {
          request => {
            complete(s"${request.result}")
          }
        }
      }
    }
  }
}
