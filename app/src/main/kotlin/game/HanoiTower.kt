package game

class HanoiTower(private val size: Int = 0) {
    private val stacks : List<Tower> = List(Utils.STACK_COUNT) { index ->
        when (index) {
            0 -> Utils.baseStack(size)
            else -> Tower()
        }
    }
    private var turnCount : Int = 0

    fun move(from: Int, to: Int) = when {
            from !in 0..<Utils.STACK_COUNT -> error("Source stack #${from+1} invalid")
            to !in 0..<Utils.STACK_COUNT -> error("Destination stack #${to+1} invalid")
            else -> {
                stacks[to].push(stacks[from].top())
                stacks[from].pop()
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
