package per.arithmetic_compiler.math

enum class MathOperation(
        val mathSign: String
) {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    companion object {
        fun of(mathSign: String): MathOperation {
            return values().asSequence()
                    .filter { it.mathSign == mathSign }
                    .first()
        }
    }
}