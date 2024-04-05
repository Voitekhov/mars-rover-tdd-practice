package com.stringconcat.marsrover

class Rover(
    var coordinates: Coordinate,
    var direction: Direction,
) {

    private lateinit var roverMover: RoverMover

    fun turnLeft() {
        direction = roverMover.turn(direction, Turn.LEFT)
    }

    fun move() {
        coordinates = roverMover.move(direction, coordinates)
    }

    fun turnRight() {
        direction = roverMover.turn(direction, Turn.RIGHT)
    }

    fun setPlateuCoordinateInfo(
        xMax: Int,
        yMax: Int,
        coordinatesMap: MutableMap<Coordinate, Boolean>
    ) {
        roverMover = RoverMover(RoverMoveValidator(xMax, yMax, coordinatesMap))
    }

}
