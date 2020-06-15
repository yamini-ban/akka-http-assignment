package com.knoldus.assignment.model

import scala.util.{Failure, Success, Try}

case class PostRequest(operands: Array[Int], operator: String) {

  def result: Int = if (operands.length < 2) 0 else compute

  def compute: Int = {
    val first = operands(0)
    operator match {
      case "+" => operands.sum
      case "-" => operands.foldLeft(first)((res, element) => res - element)
      case "*" => operands.product
      case "/" => divideOrModulo(operands.toList, operator)
      case "%" => divideOrModulo(operands.toList, operator)
      case _ => Int.MinValue
    }
  }

  private def divideOrModulo(list: List[Int], operator: String): Int = {
    @scala.annotation.tailrec
    def innerCompute(list: List[Int], result: Int): Int = {
      list match {
        case first :: rest if operator == "/" => innerCompute(rest, result / first)
        case first :: rest if operator == "%" => innerCompute(rest, result % first)
        case first :: Nil if operator == "/" => result / first
        case first :: Nil if operator == "%" => result % first
        case Nil => result
      }
    }
    val result = Try {
      list match {
        case first :: second :: rest if operator == "/" => innerCompute(rest, first/second)
        case first :: second :: rest if operator == "%" => innerCompute(rest, first%second)
        case _ => 0
      }
    }
    result match {
      case Success(value) => value
      case Failure(_) => 0
    }
  }
}
