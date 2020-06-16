package com.knoldus.assignment.router

import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post}
import akka.http.scaladsl.server.Route
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.model.CalculatorRequest
import com.knoldus.assignment.services.CalculatorService

object CalculatorRoute extends JsonSupport {
  val route: Route = {
    post {
      path("calculator") {
        entity(as[CalculatorRequest]) {
          request => {
            complete(s"${CalculatorService.result(request)}")
          }
        }
      }
    }
  }
}
