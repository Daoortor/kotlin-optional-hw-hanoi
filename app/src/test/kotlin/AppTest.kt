import java.io.*
import kotlin.test.Test
import app.CLIApplication
import kotlin.io.path.*
import kotlin.test.assertEquals

class AppTest {
    private fun runTestCase(input: String, expectedOutput: String) {
        val testIn = ByteArrayInputStream(input.toByteArray())
        val testOut = ByteArrayOutputStream()
        CLIApplication.run(testIn, testOut)
        assertEquals(expectedOutput, testOut.toString())
    }

    @Test
    fun test() {
        val testFilePath = Path("src/test/resources/tests")
        val inFiles = testFilePath.listDirectoryEntries("*.in").associate {
            Pair(it.fileName.toString().removeSuffix(".in"), it.readText())
        }
        val outFiles = testFilePath.listDirectoryEntries("*.out").associate {
            Pair(it.fileName.toString().removeSuffix(".out"), it.readText())
        }
        inFiles.map { (testName, input) ->
            runTestCase(input, outFiles[testName] ?: error("sample output file for test case $testName not found"))
        }
    }
}
