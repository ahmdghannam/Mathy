package arrays

/**
 * Transposes a 2D array (matrix) of integers.
 *
 * This function converts the current 2D array of shape [rows x columns] into a new array of shape [columns x rows],
 * swapping the rows and columns. For example, if the original matrix is:
 * ```
 * [
 *   [1, 2, 3],
 *   [4, 5, 6]
 * ]
 * ```
 * The transposed matrix will be:
 * ```
 * [
 *   [1, 4],
 *   [2, 5],
 *   [3, 6]
 * ]
 * ```
 *
 * @return A new 2D array representing the transposed matrix. If the original array is empty, an empty array is returned.
 */
fun Array<IntArray>.transpose(): Array<IntArray> {
    if (isEmpty()) return emptyArray()
    val result = Array(this[0].size) { IntArray(this.size) }
    for (i in result.indices) {
        for (j in result[i].indices) {
            result[i][j] = this[j][i]
        }
    }
    return result
}