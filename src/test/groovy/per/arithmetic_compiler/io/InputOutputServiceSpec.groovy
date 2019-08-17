package per.arithmetic_compiler.io

import org.apache.commons.io.IOUtils
import spock.lang.Specification

class InputOutputServiceSpec extends Specification {

    private static final String TEST_INPUT = "test"
    private static final Float TEST_ANSWER = 3.3

    def "should return line from InputStream"() {
        given:
        def inputStream = IOUtils.toInputStream(TEST_INPUT)

        when:
        def result = inputOutputService(inputStream, System.out)
                .getInput()

        then:
        result == TEST_INPUT
    }

    def "should close input stream after reading"() {
        given:
        def inputStream = Spy(IOUtils.toInputStream(TEST_INPUT))

        when:
        inputOutputService(inputStream, System.out)
                .getInput()

        then:
        1 * inputStream.close()
    }

    def "should put answer in OutputStream"() {
        given:
        def outputStream = new ByteArrayOutputStream()

        when:
        inputOutputService(System.in, new PrintStream(outputStream))
                .printlnAnswer(TEST_ANSWER)

        then:
        outputStream.toString().trim() == TEST_ANSWER.toString()
    }

    def "should close output stream after printing"() {
        given:
        def outputStream = Mock(OutputStream)

        when:
        inputOutputService(System.in, outputStream)
                .printlnAnswer(TEST_ANSWER)

        then:
        1 * outputStream.close()
    }


    InputOutputService inputOutputService(InputStream inputStream, OutputStream outputStream) {
        new InputOutputService(inputStream, outputStream)
    }
}