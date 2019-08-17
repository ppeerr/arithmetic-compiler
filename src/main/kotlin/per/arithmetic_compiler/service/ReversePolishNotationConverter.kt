package per.arithmetic_compiler.service

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operator
import java.util.*
import kotlin.collections.ArrayList
import per.arithmetic_compiler.math.MathOperation.*

class ReversePolishNotationConverter {

    fun convert(elements: List<FormulaObject>): List<FormulaObject> {
        val output = ArrayList<FormulaObject>()
        val operations = Stack<Operator>()

        for (currentObject in elements) {
            if (isOperator(currentObject)) {
                val operator = currentObject as Operator

                while (!operations.empty()) {
                    if (isOperator(operations.lastElement())
                            && getOperatorPriority(operator) <= getOperatorPriority(operations.lastElement())) {
                        output.add(operations.pop())
                    } else {
                        break
                    }
                }
                operations.push(currentObject)
            } else {
                output.add(currentObject)
            }
        }

        while (!operations.empty()) {
            output.add(operations.pop())
        }

        return output
    }

    private fun getOperatorPriority(operator: Operator): Int {
        return when (operator.operation) {
            MULTIPLICATION, DIVISION -> 2
            SUBTRACTION, ADDITION -> 1
        }
    }

    private fun isOperator(element: FormulaObject): Boolean {
        return element is Operator
    }
}