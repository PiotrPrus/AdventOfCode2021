fun main() {
    fun part1(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val list = convertToListOfBoards(input)

        draws.forEach { draw ->
            list.onEach { board ->
                board.points.forEach {
                    if (draw == it.value) {
                        it.selected = true
                    }
                }
                if (board.completed) {
                    val unselectedSum = board.points.sumOf { if (!it.selected) it.value else 0 }
                    return unselectedSum.times(draw)
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val draws = input.first().split(",").map { it.toInt() }
        val boards = convertToListOfBoards(input)
        val completedBoards = mutableListOf<Board>()
        var lastDraw = 0

        draws.forEach { draw ->
            boards.onEach { board ->
                if (!board.completed) {
                    board.points.forEach {
                        if (draw == it.value) {
                            it.selected = true
                        }
                    }
                    if (board.completed) {
                        completedBoards.add(board)
                        lastDraw = draw
                    }
                }
            }
        }

        val lastBoard = completedBoards.last().points.sumOf { if (!it.selected) it.value else 0 }

        return lastBoard.times(lastDraw)
    }

    // test
    val testInput = readInput("Day04_test")
    assert(part1(testInput) == 4512)
    assert(part2(testInput) == 1924)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private fun convertToListOfBoards(input: List<String>): List<Board> {
    val boards = input.drop(1).chunked(6)
        .map { group -> group.drop(1) }.map { board ->
            board.map { line ->
                line.split("  ", " ")
                    .filter { it.isNotBlank() } // Leading whitespace
                    .map { it.toInt() }
            }
        }

    val list: List<Board> = boards.map { board ->
        val points = mutableListOf<BoardField>()
        board.forEachIndexed { indexX, column ->
            column.forEachIndexed { indexY, value ->
                points.add(BoardField(positionX = indexX, positionY = indexY, value = value))
            }
        }
        Board(points = points)
    }
    return list
}

data class Board(
    val points: List<BoardField>,
) {
    val completed: Boolean
        get() {
            return points.filter { it.selected }.groupingBy { it.positionX }
                .eachCount().values.contains(5) || points.filter { it.selected }.groupingBy { it.positionY }
                .eachCount().values.contains(5)
        }
}

data class BoardField(
    val positionX: Int,
    val positionY: Int,
    val value: Int,
    var selected: Boolean = false
)
