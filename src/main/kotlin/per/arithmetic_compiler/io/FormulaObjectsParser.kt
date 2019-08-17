package per.arithmetic_compiler.io

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.FormulaObjectFactory
import java.util.*
import kotlin.collections.ArrayList

class FormulaObjectsParser(
        private val formulaObjectFactory: FormulaObjectFactory,
        private val validator: InputValidator
) {

    constructor() : this(FormulaObjectFactory(), InputValidator())

    fun parse(inputString: String): List<FormulaObject> {
        val result = getFormulaElements(inputString)
        validator.validate(result)

        return result
    }

    private fun getFormulaElements(inputString: String): ArrayList<FormulaObject> {
        val tokenizer = StringTokenizer(
                inputString,
                FORMULA_DELIMITERS,
                true)
        val result = ArrayList<FormulaObject>()

        while (tokenizer.hasMoreTokens()) {
            val token = tokenizer.nextToken().trim()
            result.add(formulaObjectFactory.get(token))
        }
        return result
    }

    companion object {
        const val FORMULA_DELIMITERS = "+-*/"
    }
}