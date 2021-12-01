package day01

fun main() {
    fun part1(input: List<String>): Int {
        val list = input.map { it.toInt() }
        var increased = 0
        (1..list.lastIndex).forEach { index ->
            if (list[index] > list[index.minus(1)]) increased++
        }
        return increased
    }
    fun part2(input: List<String>): Int {
        val list = input.map { it.toInt() }
        var increased = 0
        (0..list.lastIndex.minus(3)).forEach { index ->
            val currentGroup = list[index] + list[index.plus(1)] + list[index.plus(2)]
            val nextGroup = list[index.plus(1)] + list[index.plus(2)] + list[index.plus(3)]
            if (nextGroup > currentGroup) increased++
        }
        return increased
    }

    // test
    val testInput = readInput("day01/Day01_test")
    assert(part1(testInput) == 4)
    assert(part2(testInput) == 3)

    val input = readInput("day01/Day01")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
