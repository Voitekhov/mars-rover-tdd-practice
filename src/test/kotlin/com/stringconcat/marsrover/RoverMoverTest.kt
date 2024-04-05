package com.stringconcat.marsrover

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RoverMoverTest {

    val roverValidator = RoverMoveValidator(10, 10, mutableMapOf())
    val roverMover = RoverMover(roverValidator)

    // ---------------------------------------- TURN TESTS
    @Test
    fun `RIGHT SOUTH _ WEST `() {
        roverMover.turn(Direction.SOUTH, Turn.RIGHT) shouldBe Direction.WEST
    }

    @Test
    fun `LEFT SOUTH _ EAST `() {
        roverMover.turn(Direction.SOUTH, Turn.LEFT) shouldBe Direction.EAST
    }

    @Test
    fun `RIGHT WEST _ NORTH `() {
        roverMover.turn(Direction.WEST, Turn.RIGHT) shouldBe Direction.NORTH
    }

    @Test
    fun `LEFT WEST _ SOUTH `() {
        roverMover.turn(Direction.WEST, Turn.LEFT) shouldBe Direction.SOUTH
    }

    @Test
    fun `RIGHT NORTH _ EAST `() {
        roverMover.turn(Direction.NORTH, Turn.RIGHT) shouldBe Direction.EAST
    }

    @Test
    fun `LEFT NORTH _ WEST `() {
        roverMover.turn(Direction.NORTH, Turn.LEFT) shouldBe Direction.WEST
    }

    @Test
    fun `RIGHT EAST _ SOUTH `() {
        roverMover.turn(Direction.EAST, Turn.RIGHT) shouldBe Direction.SOUTH
    }

    @Test
    fun `LEFT EAST _ NORTH `() {
        roverMover.turn(Direction.EAST, Turn.LEFT) shouldBe Direction.NORTH
    }

    // ---------------------------------------- MOVE TESTS
    @Test
    fun `go NORTH inc y`() {
        roverMover.move(Direction.NORTH, Coordinate(0, 0)) shouldBe Coordinate(0, 1)
    }

    @Test
    fun `go SOUTH dec y`() {
        roverMover.move(Direction.SOUTH, Coordinate(1, 1)) shouldBe Coordinate(1, 0)
    }

    @Test
    fun `go WEST dec x`() {
        roverMover.move(Direction.WEST, Coordinate(1, 1)) shouldBe Coordinate(0, 1)
    }

    @Test
    fun `go EAST inc x`() {
        roverMover.move(Direction.EAST, Coordinate(1, 1)) shouldBe Coordinate(2, 1)
    }
}
