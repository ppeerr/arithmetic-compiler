package per.arithmetic_compiler.math

import spock.lang.Specification

class OperatorSpec extends Specification {

    def "should calc 'a + b' for addition operator"() {
        given:
        Operator additionOperator = new Operator("+")
        Operand a = new Operand(1.3)
        Operand b = new Operand(2.1)

        when:
        def result = additionOperator.calc(a, b)

        then:
        result == a + b
    }

    def "should calc 'a - b' for subtract operator"() {
        given:
        Operator subtractionOperator = new Operator("-")
        Operand a = new Operand(1.1)
        Operand b = new Operand(2)

        when:
        def result = subtractionOperator.calc(a, b)

        then:
        result == a - b
    }

    def "should calc 'a * b' for multiplication operator"() {
        given:
        Operator multiplicationOperator = new Operator("*")
        Operand a = new Operand(9)
        Operand b = new Operand(2.0)

        when:
        def result = multiplicationOperator.calc(a, b)

        then:
        result == new Operand(18)
    }

    def "should calc 'a / b' for division operator"() {
        given:
        Operator divisionOperator = new Operator("/")
        Operand a = new Operand(1)
        Operand b = new Operand(3)

        when:
        def result = divisionOperator.calc(a, b)

        then:
        result == a / b
    }

    def "should throw an exception 'a / 0' for division operator"() {
        given:
        Operator divisionOperator = new Operator("/")
        Operand a = new Operand(1)

        when:
        divisionOperator.calc(a, new Operand(0))

        then:
        RuntimeException ex = thrown()
        ex.message == 'Can\'t divide by 0'
    }
}