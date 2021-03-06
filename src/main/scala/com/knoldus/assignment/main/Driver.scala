package com.knoldus.assignment.main

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.knoldus.assignment.JsonSupport
import com.knoldus.assignment.router.{CalculatorRoute, HelloRoute}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

object Driver extends App with JsonSupport {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route: Route = {
    HelloRoute.route ~ CalculatorRoute.route
  }

  // `route` will be implicitly converted to `Flow` using `RouteResult.route2HandlerFlow`
  val ip = ConfigFactory.load().getString("host")
  val port = ConfigFactory.load().getString("port").toInt
  val bindingFuture = Http().bindAndHandle(route, ip, port)
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}
