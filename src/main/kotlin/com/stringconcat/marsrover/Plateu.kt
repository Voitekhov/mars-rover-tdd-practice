package com.stringconcat.marsrover

import com.stringconcat.marsrover.exception.CoordinateNotEmptyException

data class Plateu(val width: Int, val heigh: Int) {

    private val coordinates: MutableMap<Coordinate, Boolean> = HashMap()

    fun land(rover: Rover) {
        if (coordinates[rover.coordinates] != null && coordinates[rover.coordinates] == false) {
            throw CoordinateNotEmptyException()
        }
        coordinates[rover.coordinates] = false
        rover.setPlateuCoordinateInfo(width, heigh, coordinates)
    }


}
