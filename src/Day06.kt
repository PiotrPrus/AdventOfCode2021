fun main() {
    fun part1(input: List<String>): Int {
        val fish = input.first().split(",").map { Lanternfish(timer = it.toInt()) }.toMutableList()
        var newFish = 0
        repeat(80) { _ ->
            if (newFish > 0) {
                fish.addAll(List(newFish) { Lanternfish() })
                newFish = 0
            }
            fish.onEach {
                if (it.timer == 0) {
                    it.reset()
                    newFish++
                } else {
                    it.timer = it.timer.minus(1)
                }
            }
        }

        fish.addAll(List(newFish) { Lanternfish() })
        return fish.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test
    val testInput = readInput("Day06_test")
    assert(part1(testInput) == 5934)
    assert(part2(testInput) == 0)

    val input = readInput("Day06")
    println("Part 1: ${part1(input)}")
//    println("Part 2: ${part2(input)}")
}

data class Lanternfish(
    var timer: Int = 8,
    var firstCicle: Boolean = true
) {
    fun reset() {
        timer = 6
        firstCicle = false
    }
}