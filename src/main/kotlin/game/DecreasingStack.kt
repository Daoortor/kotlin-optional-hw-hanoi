package game

import kotlin.collections.ArrayDeque

class DecreasingStack<T : Comparable<T>>(elementsList: List<T> = listOf()) {
    private val elements: ArrayDeque<T> = ArrayDeque()
    init {
        elementsList.forEach { push(it) }
    }

    fun push(newElement: T) = when {
        elements.isEmpty() || elements.last() > newElement -> elements.addLast(newElement)
        else -> error("element is bigger than top of the stack")
    }

    fun pop(): T = when {
        elements.isEmpty() -> error("stack is empty")
        else -> elements.removeLast()
    }

    fun size(): Int = elements.size

    fun isEmpty(): Boolean = elements.isEmpty()

    fun getElements(): ArrayDeque<T> = elements

    override fun equals(other: Any?) = hashCode() == other.hashCode()

    override fun hashCode(): Int {
        return elements.toList().hashCode()
    }
}
