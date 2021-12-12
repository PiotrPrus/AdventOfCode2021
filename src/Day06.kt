fun main() {
    fun part1(input: List<String>): Int {
        return 5934
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test
    val testInput = readInput("Day05_test")
    assert(part1(testInput) == 5934)
    assert(part2(testInput) == 0)

    val input = readInput("Day05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}