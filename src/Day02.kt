fun main() {
    fun part1(input: List<String>): Int {
        val converted = input.map {
            val split = it.split(" ")
            split[0] to split[1].toInt()
        }
        var forward = 0
        var depth = 0
        converted.forEach { pair ->
            when(pair.first) {
                "forward" -> forward = forward.plus(pair.second)
                "down" -> depth = depth.plus(pair.second)
                "up" -> depth = depth.minus(pair.second)
            }
        }
        return forward.times(depth)
    }
    fun part2(input: List<String>): Int {
        return 0
    }

    // test
    val testInput = readInput("Day02_test")
    assert(part1(testInput) == 14)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}
