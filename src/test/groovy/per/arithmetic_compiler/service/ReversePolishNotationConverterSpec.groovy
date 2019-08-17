package per.arithmetic_compiler.service

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import spock.lang.Specification

class ReversePolishNotationConverterSpec extends Specification {
    def "should convert simple formula. 5 / 2"() {
        given:
        List<FormulaObject> initialStructure = [
                new Operand(5F),
                new Operator("/"),
                new Operand(2F)]

        when:
        def convertedElements = reversePolishNotationConverter()
                .convert(initialStructure)

        then:
        convertedElements == [new Operand(5F),
                              new Operand(2F),
                              new Operator("/")]
    }

    def "should convert complex formula. 6+9-4/1+1*2+1"() {
        given:
        List<FormulaObject> initialStructure = [
                new Operand(6F),
                new Operator("+"),
                new Operand(9F),
                new Operator("-"),
                new Operand(4F),
                new Operator("/"),
                new Operand(1F),
                new Operator("+"),
                new Operand(1F),
                new Operator("*"),
                new Operand(2F),
                new Operator("+"),
                new Operand(1F)]

        when:
        def convertedElements = reversePolishNotationConverter()
                .convert(initialStructure)

        then:
        convertedElements == [new Operand(6F),
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
    }

    def "should convert single operand formula. 5"() {
        given:
        List<FormulaObject> initialStructure = [new Operand(5F)]

        when:
        def convertedElements = reversePolishNotationConverter()
                .convert(initialStructure)

        then:
        convertedElements == [new Operand(5F)]
    }


    private static ReversePolishNotationConverter reversePolishNotationConverter() {
        return new ReversePolishNotationConverter()
    }
}
