package com.knoldus.assignment.services

import com.knoldus.assignment.model.CalculatorRequest

import scala.util.{Failure, Success, Try}

object CalculatorService {

  def result(calculatorEntity: CalculatorRequest): String = if (calculatorEntity.operands.length < 2) {
    "Please provide at least two operands."
  }
  else {
    compute(calculatorEntity)
  }

  private def compute(calculatorEntity: CalculatorRequest): String = {
    val first = calculatorEntity.operands(0)
    calculatorEntity.operator match {
      case "+" => calculatorEntity.operands.sum.toString
      case "-" => calculatorEntity.operands.foldLeft(first)((res, element) => res - element).toString
      case "*" => calculatorEntity.operands.product.toString
      case "/" => divideOrModulo(calculatorEntity.operands.toList, calculatorEntity.operator)
      case "%" => divideOrModulo(calculatorEntity.operands.toList, calculatorEntity.operator)
      case op => s"$op not supported."
    }
  }

  private def divideOrModulo(list: List[Int], operator: String): String = {
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
      case Success(value) => value.toString
      case Failure(_) => "Cannot perform Operation"
    }
  }

}
