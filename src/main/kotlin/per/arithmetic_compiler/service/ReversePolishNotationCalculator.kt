package per.arithmetic_compiler.service

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import java.lang.RuntimeException
import java.util.*

class ReversePolishNotationCalculator {

    fun calculate(elements: List<FormulaObject>): Float {
        val operands = ArrayDeque<Operand>()

        for (element in elements) {
            if (isOperator(element)) {
                val operator = element as Operator

                val right = operands.pop()
                val left = operands.pop()

                operands.push(operator.calc(left, right))
            } else {
                operands.push(element as Operand)
            }
        }

        if (operands.size > 1)
            throw RuntimeException("Wrong operators count")

        return operands.pop().value
    }


    private fun isOperator(element: FormulaObject) = element is Operator
}