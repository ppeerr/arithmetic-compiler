package per.arithmetic_compiler.math

class FormulaObjectFactory {

    fun get(value: String): FormulaObject {
        return when {
            isOperator(value) -> Operator(value)
            isOperand(value) -> Operand(value)
            else -> throw RuntimeException("Can't construct object for '$value'. " +
                    "Operator is '+', '-', '*' or '/'. " +
                    "Operand is integer value between 0 and 9.")
        }
    }


    private fun isOperator(value: String): Boolean {
        return MathOperation.values().asSequence()
                .map { it.mathSign }
                .any { it == value }
    }

    private fun isOperand(value: String): Boolean {
        val intValue = value.toIntOrNull()

        return intValue != null
                && intValue in 0..9
    }
}