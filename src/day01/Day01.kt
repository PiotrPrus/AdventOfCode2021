package day01

fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 6)
    val testList = testInput.map { it.toInt() }
    val testIncreased = countIncreased(testList)
    assert(testIncreased == 4)
    val testIncreasedByGroup = countIncreasedByGroup(testList)
    assert(testIncreasedByGroup == 3)

    val input = readInput("day01/Day01")
    countIncreased(input.map { it.toInt() })
    countIncreasedByGroup(input.map { it.toInt() })
}

// Day 1, part 2
private fun countIncreasedByGroup(list: List<Int>): Int {
    var increased = 0
    (0..list.lastIndex.minus(3)).forEach { index ->
        val currentGroup = list[index] + list[index.plus(1)] + list[index.plus(2)]
        val nextGroup = list[index.plus(1)] + list[index.plus(2)] + list[index.plus(3)]
        if (nextGroup > currentGroup) increased++
    }
    println("Increased by group: $increased")
    return increased
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
