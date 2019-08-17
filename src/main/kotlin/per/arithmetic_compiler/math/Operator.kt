package per.arithmetic_compiler.math

import per.arithmetic_compiler.math.MathOperation.*
import java.lang.RuntimeException

data class Operator(
        val operation: MathOperation
) : FormulaObject {

    constructor(value: String) : this(Companion.of(value))

    fun calc(left: Operand, right: Operand): Operand {
        return when (operation) {
            ADDITION -> left + right
            SUBTRACTION -> left - right
            MULTIPLICATION -> left * right
            DIVISION -> divide(left, right)
        }
    }

    private fun divide(left: Operand, right: Operand) : Operand {
        if (right.value == 0F)
            throw RuntimeException("Can't divide by 0")

        return left / right
    }
}