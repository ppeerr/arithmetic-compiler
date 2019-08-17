package per.arithmetic_compiler.service

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import spock.lang.Specification

class ReversePolishNotationCalculatorSpec extends Specification {

    def "should calc complex formula. 6+9-4/1+1*2+1"() {
        given:
        List<FormulaObject> input = [new Operand(6F),
                                     new Operand(9F),
                                     new Operator("+"),
                                     new Operand(4F),
                                     new Operand(1F),
                                     new Operator("/"),
                                     new Operator("-"),
                                     new Operand(1F),
                                     new Operand(2F),
                                     new Operator("*"),
                                     new Operator("+"),
                                     new Operand(1F),
                                     new Operator("+")]

        when:
        def answer = reversePolishNotationCalculator()
                .calculate(input)

        then:
        answer == 14F
    }

    def "should throw exception if there sre not enough operators"() {
        given:
        List<FormulaObject> input = [new Operand(6F),
                                     new Operand(9F),
                                     new Operator("+"),
                                     new Operand(4F),
                                     new Operand(1F),
                                     new Operator("/"),
                                     new Operator("-"),
                                     new Operand(1F),
                                     new Operand(2F),
                                     new Operator("*"),
                                     new Operator("+"),
                                     new Operand(1F)]

        when:
        reversePolishNotationCalculator()
                .calculate(input)

        then:
        RuntimeException ex = thrown()
        ex.message == "Wrong operators count"
    }


    private static ReversePolishNotationCalculator reversePolishNotationCalculator() {
        new ReversePolishNotationCalculator()
    }
}
