package day01

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 6)
    val testList = testInput.map { it.toInt() }
    val testIncreased = countIncreased(testList)
    assert(testIncreased == 4)

    val input = readInput("day01/Day01")
    countIncreased(input.map { it.toInt() })
}

private fun countIncreased(list: List<Int>): Int {
    var increased = 0
    (1..list.lastIndex).forEach { index ->
        if (list[index] > list[index.minus(1)]) increased++
    }
    println("Increased result: $increased")
    return increased
}

fun part1(input: List<String>): Int {
    return input.size
}
