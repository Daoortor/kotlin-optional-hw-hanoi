package app

import game.HanoiTower
import gui.DefaultTowerView
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream

object CLIApplication {
    private lateinit var game : HanoiTower
    private val towerView = DefaultTowerView

    private fun processInput(inputLine: String) {
        val parsedNumbers = inputLine.split(Regex(" +")).map {
            it.toIntOrNull() ?: error("Input should contain two numbers: the indices of the source and destination stack.")
        }
        when {
            parsedNumbers.size != 2 -> error("Input should contain two numbers: the indices of the source and destination stack.")
            else -> game.move(parsedNumbers[0] - 1, parsedNumbers[1] - 1)
        }
    }

    fun run(inputStream: InputStream = System.`in`, outputStream: OutputStream = System.out) {
        val reader = inputStream.bufferedReader()
        val writer = PrintStream(outputStream)
        writer.print("Enter tower size: ")
        var towerSize = (reader.readLine() ?: return).toIntOrNull()
        while (towerSize == null || towerSize < 2) {
            writer.print("Tower size should be an integer >= 2. Enter tower size: ")
            towerSize = (reader.readLine() ?: return).toIntOrNull()
        }
        game = HanoiTower(towerSize)
        do {
            writer.println(towerView.show(game))
            writer.print("Enter source and destination stacks: ")
            try {
                val inputLine = reader.readLine() ?: return
                processInput(inputLine)
            } catch (e: IllegalStateException) {
                writer.println(e.message)
            }
        } while (!game.checkWon())
        writer.println(towerView.show(game))
        writer.println("You finished in ${game.getMoveCount()} moves.")
    }
}
