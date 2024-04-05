package com.stringconcat.marsrover

class RoverMover( val roverMoveValidator: RoverMoveValidator) {

    companion object {
        private val turnMap: MutableMap<Pair<Direction, Turn>, Direction> = HashMap()

        init {
            turnMap[Pair(Direction.NORTH, Turn.LEFT)] = Direction.WEST
            turnMap[Pair(Direction.NORTH, Turn.RIGHT)] = Direction.EAST

            turnMap[Pair(Direction.EAST, Turn.LEFT)] = Direction.NORTH
            turnMap[Pair(Direction.EAST, Turn.RIGHT)] = Direction.SOUTH

            turnMap[Pair(Direction.SOUTH, Turn.LEFT)] = Direction.EAST
            turnMap[Pair(Direction.SOUTH, Turn.RIGHT)] = Direction.WEST

            turnMap[Pair(Direction.WEST, Turn.LEFT)] = Direction.SOUTH
            turnMap[Pair(Direction.WEST, Turn.RIGHT)] = Direction.NORTH
        }
    }

    fun turn(currentDirection: Direction, turn: Turn): Direction {
        return turnMap[Pair(currentDirection, turn)]!!
    }

    fun move(direction: Direction, coordinate: Coordinate): Coordinate {
        val newCoordinate = when (direction) {
            Direction.NORTH -> coordinate.incY()
            Direction.EAST -> coordinate.incX()
            Direction.WEST -> coordinate.decX()
            else -> coordinate.decY()
        }
        roverMoveValidator.validate(coordinate = coordinate, newCoordinate = newCoordinate)
        return newCoordinate
    }
}
