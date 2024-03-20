package app

import game.HanoiTower
import gui.DefaultTowerView

object CLIApplication {
    private var game = HanoiTower()
    private val towerView = DefaultTowerView

    private fun processInput(inputLine: String) {
        val parsedNumbers = Regex("(\\d+)").findAll(inputLine).map { it.groupValues[1].toInt() }.toList()
        when {
            parsedNumbers.size != 2 -> error("Input should contain two numbers: the indices of the source and destination stack.")
            else -> game.move(parsedNumbers[0] - 1, parsedNumbers[1] - 1)
        }
    }

    fun run() {
        print("Enter tower size: ")
        var towerSize = readln().toIntOrNull()
        while (towerSize == null || towerSize < 2) {
            print("Tower size should be an integer >= 2. Enter tower size: ")
            towerSize = readln().toIntOrNull()
        }
        game = HanoiTower(towerSize)
        do {
            println(towerView.show(game))
            while (true) {
                print("Enter source and destination stacks: ")
                try {
                    processInput(readln())
                    break
                } catch (e: IllegalStateException) {
                    println(e.message)
                }
            }
        } while (!game.checkWon())
        println(towerView.show(game))
        println("You finished in ${game.getMoveCount()} moves.")
    }
}
