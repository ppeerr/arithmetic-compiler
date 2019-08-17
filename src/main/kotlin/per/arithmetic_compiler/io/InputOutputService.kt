package per.arithmetic_compiler.io

import java.io.InputStream
import java.io.OutputStream

class InputOutputService(
        private val inputStream: InputStream,
        private val outputStream: OutputStream
) {
    constructor() : this(System.`in`, System.out)


    fun getInput(): String {

        return inputStream
                .bufferedReader()
                .use {
                    it.readLine()
                }
    }

    fun printlnAnswer(answer: Float) {
        outputStream
                .bufferedWriter()
                .use {
                    it.write(answer.toString())
                    it.flush()
                }
    }
}