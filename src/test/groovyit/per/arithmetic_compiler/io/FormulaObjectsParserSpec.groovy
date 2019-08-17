package per.arithmetic_compiler.io

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.FormulaObjectFactory
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import spock.lang.Specification

class FormulaObjectsParserSpec extends Specification {

    private FormulaObjectFactory formulaObjectFactory = new FormulaObjectFactory()
    private InputValidator validator = new InputValidator()

    def "should parse a simple formula"() {
        given:
        String inputString = "2 +2* 2"

        when:
        List<FormulaObject> formulaObjects = formulaObjectsParser()
                .parse(inputString)

        then:
        formulaObjects == [
                new Operand(2F),
                new Operator("+"),
                new Operand(2F),
                new Operator("*"),
                new Operand(2F)]
    }

    def "should parse single operand formula"() {
        given:
        String inputString = " 3  "

        when:
        List<FormulaObject> formulaObjects = formulaObjectsParser()
                .parse(inputString)

        then:
        formulaObjects == [new Operand(3F)]
    }


    private FormulaObjectsParser formulaObjectsParser() {
        new FormulaObjectsParser(formulaObjectFactory, validator)
    }
}
