package per.arithmetic_compiler.math

import spock.lang.Specification
import spock.lang.Unroll

class FormulaObjectFactorySpec extends Specification {

    def "should return created Operand for digit"() {
        given:
        def token = "1"

        when:
        def formulaObject = formulaObjectFactory()
                .get(token)

        then:
        formulaObject in Operand
    }

    @Unroll
    def "should return created Operator for symbol '#token'"(String token) {
        when:
        def formulaObject = formulaObjectFactory()
                .get(token)

        then:
        formulaObject in Operator

        where:
        token << ["+", "-", "/", "*"]
    }

    @Unroll
    def "should throw an Exception for not recognizable token or invalid operand ='#token'"(String token) {
        when:
        formulaObjectFactory()
                .get(token)

        then:
        thrown(RuntimeException)

        where:
        token << ["t", "-1", "10"]
    }


    private static FormulaObjectFactory formulaObjectFactory() {
        new FormulaObjectFactory()
    }
}