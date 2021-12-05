import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var gamma = 0
        var epsilon = 0
        val cumulate = MutableList(input.first().length) { 0 }
        input.onEach {
            (0..it.lastIndex).forEach { index ->
                cumulate[index] = cumulate[index] + it[index].digitToInt()
            }
        }
        cumulate.map {
            if (it < input.size.div(2)) 0 else 1
        }.joinToString("").forEachIndexed { index, char ->
            gamma += 2.0.pow(cumulate.lastIndex.minus(index)).times(char.digitToInt()).toInt()
        }
        cumulate.map {
            if (it < input.size.div(2)) 1 else 0
        }.joinToString("").forEachIndexed { index, char ->
            epsilon += 2.0.pow(cumulate.lastIndex.minus(index)).times(char.digitToInt()).toInt()
        }
        return gamma.times(epsilon)
    }

    fun part2(input: List<String>): Int {
        val cumulate = MutableList(input.first().length) { 0 }
        var oxygenList = input
        (0..input.first().lastIndex).forEachIndexed { index, item ->
            if (oxygenList.size == 1) return@forEachIndexed
            oxygenList = oxygenList.onEach { str ->
                cumulate[index] = cumulate[index] + str[index].digitToInt()
            }.mapNotNull {
                val leadingNum = if (cumulate[index] >= oxygenList.size.div(2.0)) 1 else 0
                if (it[index].digitToInt() == leadingNum) it else null
            }
        }

        var scrubber = input
        val cumulate2 = MutableList(input.first().length) { 0 }
        (0..input.first().lastIndex).forEachIndexed { index, item ->
            if (scrubber.size == 1) return@forEachIndexed
            scrubber = scrubber.onEach { str ->
                cumulate2[index] = cumulate2[index] + str[index].digitToInt()
            }.mapNotNull {
                val leadingNum = if (cumulate2[index] >= scrubber.size.div(2.0)) 0 else 1
                if (it[index].digitToInt() == leadingNum) it else null
            }
        }
        return oxygenList.first().toDecimal().times(scrubber.first().toDecimal())
    }

    // test
    val testInput = readInput("Day03_test")
    assert(part1(testInput) == 198)
    assert(part2(testInput) == 230)

    val input = readInput("Day03")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private fun String.toDecimal(): Int {
    var decimal = 0
    forEachIndexed { index, char ->
        decimal += 2.0.pow(lastIndex.minus(index)).times(char.digitToInt()).toInt()
    }
    return decimal
}
