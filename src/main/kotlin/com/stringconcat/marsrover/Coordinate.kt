package com.stringconcat.marsrover

data class Coordinate(
    val x: Int,
    val y: Int
) {
    fun incY(): Coordinate {
        return Coordinate(x, y + 1)
    }

    fun incX(): Coordinate {
        return Coordinate(x + 1, y)
    }

    fun decX(): Coordinate {
        return Coordinate(x - 1, y)
    }

    fun decY(): Coordinate {
        return Coordinate(x, y - 1)
    }
}
