package game

object Utils {
    const val STACK_COUNT: Int = 3

    fun baseStack(size: Int): DecreasingStack<Int> = DecreasingStack((size downTo 1).toList())

    fun transpose(matrix: List<List<Int>>): List<List<Int>> = when {
        matrix.isEmpty() -> matrix
        else -> matrix[0].indices.map { colIndex ->
            matrix.indices.map { rowIndex ->
                matrix[rowIndex][colIndex]
            }
        }
    }
}