package com.stringconcat.marsrover

import com.stringconcat.marsrover.exception.BoundaryException
import com.stringconcat.marsrover.exception.CoordinateNotEmptyException
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RoverMoveValidatorTest {

    @Test
    fun `should throw exception when coordinates is busy`() {
        val coordinates = mutableMapOf(Coordinate(1, 1) to false)
        val roverMoveValidator = RoverMoveValidator(10, 10, coordinates)

        shouldThrowExactly<CoordinateNotEmptyException> {
            roverMoveValidator.validate(
                coordinate = Coordinate(0, 1),
                newCoordinate = Coordinate(1, 1)
            )
        }
    }

    @Test
    fun `coordinate should be empty after move`() {
        val coordinates = mutableMapOf(Coordinate(1, 1) to false)
        val roverMoveValidator = RoverMoveValidator(10, 10, coordinates)

        roverMoveValidator.validate(
            coordinate = Coordinate(1, 1),
            newCoordinate = Coordinate(0, 1)
        )

        coordinates[Coordinate(1, 1)] shouldBe true
    }

    @Test
    fun `should throw exception when X coordinate not in boundary `() {
        val roverMoveValidator = RoverMoveValidator(10, 10, mutableMapOf())

        shouldThrowExactly<BoundaryException> {
            roverMoveValidator.validate(
                coordinate = Coordinate(10, 10),
                newCoordinate = Coordinate(11, 10)
            )
        }
    }

    @Test
    fun `should throw exception when Y coordinate not in boundary `() {
        val roverMoveValidator = RoverMoveValidator(10, 10, mutableMapOf())

        shouldThrowExactly<BoundaryException> {
            roverMoveValidator.validate(
                coordinate = Coordinate(10, 10),
                newCoordinate = Coordinate(10, 11)
            )
        }
    }
}
