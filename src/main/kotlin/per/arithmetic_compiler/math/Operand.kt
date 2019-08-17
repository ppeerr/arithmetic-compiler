package per.arithmetic_compiler.math

data class Operand(
        val value: Float
) : FormulaObject {

    constructor(value: String) : this(value.toFloat())

    operator fun plus(another: Operand) = Operand(this.value + another.value)

    operator fun minus(another: Operand) = Operand(this.value - another.value)

    operator fun times(another: Operand) = Operand(this.value * another.value)

    operator fun div(another: Operand) = Operand(this.value / another.value)
}