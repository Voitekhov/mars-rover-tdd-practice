package com.stringconcat.marsrover

import com.stringconcat.marsrover.exception.BoundaryException
import com.stringconcat.marsrover.exception.CoordinateNotEmptyException

class RoverMoveValidator(
    private val xMax: Int,
    private val yMax: Int,
    private val coordinatesMap: MutableMap<Coordinate, Boolean>
) {
    fun validate(coordinate: Coordinate, newCoordinate: Coordinate) {
        if (newCoordinate.x > xMax || newCoordinate.x < 0 || newCoordinate.y > yMax || newCoordinate.y < 0) {
            throw BoundaryException()
        }
        if (coordinatesMap[newCoordinate] != null && coordinatesMap[newCoordinate] == false) {
            throw CoordinateNotEmptyException()
        }
        coordinatesMap[coordinate] = true
        coordinatesMap[newCoordinate] = false

    }
}
