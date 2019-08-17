package per.arithmetic_compiler.io

import per.arithmetic_compiler.math.FormulaObject
import per.arithmetic_compiler.math.Operand
import per.arithmetic_compiler.math.Operator
import spock.lang.Specification

class InputValidatorSpec extends Specification {

    def "should fail validation when two consecutive operators"() {
        given:
        List<FormulaObject> formulaObjects = [
                new Operand(1F),
                new Operator("+"),
                new Operator("-")]

        when:
        inputValidator().validate(formulaObjects)

        then:
        RuntimeException ex = thrown()
        ex.message == "Formula can't consist of two consecutive operands or operators."
    }

    def "should fail validation when two consecutive operands"() {
        given:
        List<FormulaObject> formulaObjects = [
                new Operand(1F),
                new Operand(6F),
                new Operator("-")]

        when:
        inputValidator().validate(formulaObjects)

        then:
        RuntimeException ex = thrown()
        ex.message == "Formula can't consist of two consecutive operands or operators."
    }

    def "should not fail validation when 100 operands (max)"() {
        given:
        List<FormulaObject> formulaObjects = new ArrayList<>()
        for (int num in 1..99) {
            formulaObjects.add(new Operand(1F))
            formulaObjects.add(new Operator("+"))
        }
        formulaObjects.add(new Operand(1F))

        when:
        inputValidator().validate(formulaObjects)

        then:
        true
    }

    def "should fail validation when more than 100 operands"() {
        given:
        List<FormulaObject> formulaObjects = new ArrayList<>()
        for (int num in 1..101)
            formulaObjects.add(new Operand(1F))


        when:
        inputValidator().validate(formulaObjects)

        then:
        RuntimeException ex = thrown()
        ex.message == "Formula mustn't consist of more than 100 operands."
    }

    def "should fail validation when start by Operator"() {
        given:
        List<FormulaObject> formulaObjects = [
                new Operator("-"),
                new Operand(2F)]

        when:
        inputValidator().validate(formulaObjects)

        then:
        RuntimeException ex = thrown()
        ex.message == "Formula can't start or finish by operator."
    }

    def "should fail validation when finish by Operator"() {
        given:
        List<FormulaObject> formulaObjects = [
                new Operand(2F),
                new Operator("-")]

        when:
        inputValidator().validate(formulaObjects)

        then:
        RuntimeException ex = thrown()
        ex.message == "Formula can't start or finish by operator."
    }


    private static InputValidator inputValidator() {
        return new InputValidator()
    }
}
