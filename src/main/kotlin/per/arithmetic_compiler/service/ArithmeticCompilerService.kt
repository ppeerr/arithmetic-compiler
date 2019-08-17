package per.arithmetic_compiler.service

import per.arithmetic_compiler.io.FormulaObjectsParser
import per.arithmetic_compiler.io.InputOutputService

class ArithmeticCompilerService(
        private val inputOutputService: InputOutputService,
        private val parser: FormulaObjectsParser,
        private val converter: ReversePolishNotationConverter,
        private val calculator: ReversePolishNotationCalculator
) {
    constructor() : this(
            InputOutputService(),
            FormulaObjectsParser(),
            ReversePolishNotationConverter(),
            ReversePolishNotationCalculator())

    fun run() {
        val input = inputOutputService.getInput()
        val parsed = parser.parse(input)

        val converted = converter.convert(parsed)
        val answer = calculator.calculate(converted)

        inputOutputService.printlnAnswer(answer)
    }
}