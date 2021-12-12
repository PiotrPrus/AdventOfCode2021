import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val lines = input.map { line ->
            val twoPoints = line.split(" -> ")
            val startPoint = twoPoints.first().split(",").map { it.toInt() }
            val endPoint = twoPoints[1].split(",").map { it.toInt() }
            Line(
                start = Point(x = startPoint.first(), y = startPoint[1]),
                end = Point(x = endPoint.first(), y = endPoint[1])
            )
        }

        val points = mutableListOf<Point>()

        lines.filter { it.isHorizontalOrVertical }.forEach { line ->
            if (line.start.x == line.end.x) {
                if (line.start.y > line.end.y) {
                    (line.start.y.downTo(line.end.y)).forEach {
                        points.add(Point(x = line.start.x, y = it))
                    }
                } else {
                    (line.start.y..line.end.y).forEach {
                        points.add(Point(x = line.start.x, y = it))
                    }
                }
            } else {
                if (line.start.x > line.end.x) {
                    (line.start.x.downTo(line.end.x)).forEach {
                        points.add(Point(x = it, y = line.start.y))
                    }
                } else {
                    (line.start.x..line.end.x).forEach {
                        points.add(Point(x = it, y = line.start.y))
                    }
                }
            }
        }

        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.size
    }

    fun part2(input: List<String>): Int {
        val lines = input.map { line ->
            val twoPoints = line.split(" -> ")
            val startPoint = twoPoints.first().split(",").map { it.toInt() }
            val endPoint = twoPoints[1].split(",").map { it.toInt() }
            Line(
                start = Point(x = startPoint.first(), y = startPoint[1]),
                end = Point(x = endPoint.first(), y = endPoint[1])
            )
        }

        val points = mutableListOf<Point>()

        lines.forEach { line ->
            when {
                line.isHorizontalOrVertical -> {
                    if (line.start.x == line.end.x) {
                        if (line.start.y > line.end.y) {
                            (line.start.y.downTo(line.end.y)).forEach {
                                points.add(Point(x = line.start.x, y = it))
                            }
                        } else {
                            (line.start.y..line.end.y).forEach {
                                points.add(Point(x = line.start.x, y = it))
                            }
                        }
                    } else {
                        if (line.start.x > line.end.x) {
                            (line.start.x.downTo(line.end.x)).forEach {
                                points.add(Point(x = it, y = line.start.y))
                            }
                        } else {
                            (line.start.x..line.end.x).forEach {
                                points.add(Point(x = it, y = line.start.y))
                            }
                        }
                    }
                }
                line.isDiagonal -> {
                    when {
                        (line.start.x < line.end.x && line.start.y < line.end.y) -> {
                            (line.start.x..line.end.x).forEachIndexed { index, i ->
                                points.add(Point(x = i, y = line.start.y.plus(index)))
                            }
                        }
                        (line.start.x > line.end.x && line.start.y > line.end.y) -> {
                            (line.end.x..line.start.x).forEachIndexed { index, i ->
                                points.add(Point(x = i, y = line.end.y.plus(index)))
                            }
                        }
                        (line.start.x < line.end.x) -> {
                            (line.start.x..line.end.x).forEachIndexed { index, i ->
                                points.add(Point(x = i, y = line.start.y.minus(index)))
                            }
                        }
                        (line.start.y < line.end.y) -> {
                            (line.start.y..line.end.y).forEachIndexed { index, i ->
                                points.add(Point(x = line.start.x.minus(index), y = i))
                            }
                        }
                    }
                }
            }
        }

        return points.groupingBy { it }.eachCount().filter { it.value > 1 }.size
    }

    // test
    val testInput = readInput("Day05_test")
    assert(part1(testInput) == 5)
    assert(part2(testInput) == 12)

    val input = readInput("Day05")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

data class Line(
    val start: Point,
    val end: Point
) {
    val isHorizontalOrVertical: Boolean
        get() {
            return start.x == end.x || start.y == end.y
        }
    val isDiagonal: Boolean
        get() {
            return start.x.minus(end.x).absoluteValue == start.y.minus(end.y).absoluteValue
        }
}

data class Point(val x: Int, val y: Int)