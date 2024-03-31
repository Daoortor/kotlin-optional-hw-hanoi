package game

object Utils {
    const val STACK_COUNT: Int = 3

    fun baseStack(size: Int): Tower = Tower((size downTo 1).toMutableList())

    fun transpose(matrix: List<List<Int>>): List<List<Int>> = when {
        matrix.isEmpty() -> matrix
        else -> matrix[0].indices.map { colIndex ->
            matrix.indices.map { rowIndex ->
                matrix[rowIndex][colIndex]
            }
        }
    }
}