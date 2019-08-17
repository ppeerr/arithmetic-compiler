package per.arithmetic_compiler.service

import org.apache.commons.io.IOUtils
import per.arithmetic_compiler.io.FormulaObjectsParser
import per.arithmetic_compiler.io.InputOutputService
import spock.lang.Specification

class ArithmeticCompilerServiceSpec extends Specification {

    private static final String TEST_INPUT = "6+9-1/2+9*2+1"
    private static final String EXPECTED_ANSWER = "33.5"

    def "Run"() {
        given:
        def inputStream = IOUtils.toInputStream(TEST_INPUT)
        def outputStream = new ByteArrayOutputStream()

        when:
        arithmeticCompilerService(inputStream, new PrintStream(outputStream))
                .run()

        then:
        outputStream.toString().trim() == EXPECTED_ANSWER
    }


    private static ArithmeticCompilerService arithmeticCompilerService(
            InputStream inputStream,
            OutputStream outputStream
    ) {
        new ArithmeticCompilerService(
                new InputOutputService(inputStream, outputStream),
                new FormulaObjectsParser(),
                new ReversePolishNotationConverter(),
                new ReversePolishNotationCalculator())
    }
}