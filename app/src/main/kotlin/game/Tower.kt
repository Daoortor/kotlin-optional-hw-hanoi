package game

class Tower(private val elements: MutableList<Int> = mutableListOf()) {

    fun push(newElement: Int) = when {
        elements.isEmpty() || elements.last() > newElement -> elements.add(newElement)
        else -> error("element is bigger than top of the stack")
    }

    fun top(): Int = when {
        elements.isEmpty() -> error("stack is empty")
        else -> elements.last()
    }

    fun pop(): Int = when {
        elements.isEmpty() -> error("stack is empty")
        else -> elements.removeLast()
    }

    fun size(): Int = elements.size

    fun isEmpty(): Boolean = elements.isEmpty()

    fun getElements(): MutableList<Int> = elements

    override fun equals(other: Any?) = hashCode() == other.hashCode()

    override fun hashCode(): Int {
        return elements.toList().hashCode()
    }
}
