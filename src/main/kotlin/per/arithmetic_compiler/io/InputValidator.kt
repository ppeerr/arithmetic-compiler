package per.arithmetic_compiler.io

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import java.lang.RuntimeException
import kotlin.reflect.KClass

class InputValidator {

    fun validate(elements: List<FormulaObject>) {
        shouldNotContainMoreThanMaxAllowedOperands(elements)

        shouldNotContainTwoConsecutiveOperandsOrOperators(elements)

        if (elements.isNotEmpty())
            shouldNotStartOrFinishByOperator(elements)
    }


    private fun shouldNotContainMoreThanMaxAllowedOperands(elements: List<FormulaObject>) {
        if (elements.asSequence()
                        .count { it is Operand } > MAX_OPERANDS_COUNT) {
            throw RuntimeException("Formula mustn't consist of more than 100 operands.")
        }
    }

    private fun shouldNotContainTwoConsecutiveOperandsOrOperators(elements: List<FormulaObject>) {
        var prevClass: KClass<*>? = null
        for (element in elements) {
            if (element::class == prevClass)
                throw RuntimeException("Formula can't consist of two consecutive operands or operators.")

            prevClass = element::class
        }
    }

    private fun shouldNotStartOrFinishByOperator(elements: List<FormulaObject>) {
        if (elements.first() is Operator
                || elements.last() is Operator)
            throw RuntimeException("Formula can't start or finish by operator.")
    }

    companion object {
        const val MAX_OPERANDS_COUNT = 100
    }
}