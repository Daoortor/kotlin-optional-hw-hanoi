package game

import app.CLIApplication

class HanoiTower(private val size: Int = 0) {
    private val stacks : List<DecreasingStack<Int>> = List(Utils.STACK_COUNT) { index ->
        when (index) {
            0 -> Utils.baseStack(size)
            else -> DecreasingStack()
        }
    }
    private var turnCount : Int = 0

    fun move(from: Int, to: Int) = when {
            from !in 0..<Utils.STACK_COUNT -> error("Source stack #${from} invalid")
            to !in 0..<Utils.STACK_COUNT -> error("Destination stack #${to} invalid")
            else -> {
                stacks[to].push(stacks[from].pop())
                turnCount++
            }
        }

    fun checkWon(): Boolean = stacks.withIndex().all {
        when {
            it.index == (Utils.STACK_COUNT - 1) -> it.value == Utils.baseStack(size)
            else -> it.value.isEmpty()
        }
    }

    fun getSize() = size

    fun getStacks() = stacks

    fun getMoveCount() = turnCount
}
