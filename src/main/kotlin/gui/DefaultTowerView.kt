package gui

import game.HanoiTower
import game.Utils

object DefaultTowerView: TowerView {
    private const val DISK_SYMBOL = "█"

    private fun showDisk(diskSize: Int, towerSize: Int): String = when {
        diskSize == 0 -> " ".repeat(towerSize - 1) + "|" + " ".repeat(towerSize - 1)
        else -> " ".repeat(towerSize - diskSize) + DISK_SYMBOL.repeat(2*diskSize - 1) + " ".repeat(towerSize - diskSize)
    }

    private fun showLine(line: List<Int>, towerSize: Int): String = line.map { showDisk(it, towerSize) }.joinToString(" ")

    private fun showStacks(tower: HanoiTower): String = Utils.transpose(
        tower.getStacks().map { stack ->
            (stack.getElements().toList() + List(tower.getSize() - stack.size()) { 0 }).reversed()
        }
    ).joinToString("\n") { line ->
        showLine(line, tower.getSize())
    }

    private fun showBase(towerSize: Int): String = (1..Utils.STACK_COUNT).joinToString(" ") {
        "_".repeat(2 * towerSize - 1)
    }

    private fun showSelectors(towerSize: Int): String = (1..Utils.STACK_COUNT).joinToString(" ") {
        " ".repeat(towerSize - 2) + "[$it]" + " ".repeat(towerSize - 2)
    }

    override fun show(tower: HanoiTower): String = "\n${showStacks(tower)}\n${showBase(tower.getSize())}\n${showSelectors(tower.getSize())}\n"
}
