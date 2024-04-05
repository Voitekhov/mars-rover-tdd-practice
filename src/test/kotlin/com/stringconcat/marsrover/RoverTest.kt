package com.stringconcat.marsrover

import com.stringconcat.marsrover.exception.BoundaryException
import com.stringconcat.marsrover.exception.CoordinateNotEmptyException
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RoverTest {

    @Test
    fun `rover created - with initial coordinates`() {
        val rover = Rover(Coordinate(x = 0, y = 0), Direction.NORTH)

        rover.coordinates shouldBe Coordinate(x = 0, y = 0)
        rover.direction shouldBe Direction.NORTH
    }

    // ----------------------------------------MOVE TESTS
    @Test
    fun `north faced rover moves - increase y`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)
        rover.move()

        rover.coordinates shouldBe Coordinate(0, 1)
        rover.direction shouldBe Direction.NORTH
    }

    @Test
    fun `east faced rover moves - increase x`() {
        val rover = Rover(Coordinate(0, 0), Direction.EAST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)
        rover.move()

        rover.coordinates shouldBe Coordinate(1, 0)
        rover.direction shouldBe Direction.EAST
    }

    @Test
    fun `west faced rover moves - decrease x`() {
        val rover = Rover(Coordinate(1, 0), Direction.WEST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)
        rover.move()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.WEST
    }

    @Test
    fun `south faced rover moves - decrease y`() {
        val rover = Rover(Coordinate(0, 1), Direction.SOUTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)
        rover.move()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.SOUTH
    }

    @Test
    fun `when rover go through SOUTH boundary - throw exception`() {
        val rover = Rover(Coordinate(0, 0), Direction.SOUTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        shouldThrowExactly<BoundaryException> {
            rover.move()
        }
    }

    @Test
    fun `when rover go through WEST boundary - throw exception`() {
        val rover = Rover(Coordinate(0, 0), Direction.WEST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        shouldThrowExactly<BoundaryException> {
            rover.move()
        }
    }

    @Test
    fun `when rover go through NORTH boundary - throw exception`() {
        val rover = Rover(Coordinate(0, 1), Direction.NORTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        shouldThrowExactly<BoundaryException> {
            rover.move()
        }
    }

    @Test
    fun `when rover go through EAST boundary - throw exception`() {
        val rover = Rover(Coordinate(1, 0), Direction.EAST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        shouldThrowExactly<BoundaryException> {
            rover.move()
        }
    }

    @Test
    fun `throw exception when coordinate busy by another rover`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val anotherRover = Rover(Coordinate(0, 1), Direction.EAST)
        val plateu = Plateu(2, 2)
        plateu.land(anotherRover)
        plateu.land(rover)

        shouldThrowExactly<CoordinateNotEmptyException> {
            rover.move()
        }
    }

    @Test
    fun `should not throw exception when 2 rover have different coordinates`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val anotherRover = Rover(Coordinate(1, 1), Direction.NORTH)
        val plateu = Plateu(2, 2)
        plateu.land(anotherRover)
        plateu.land(rover)

        shouldNotThrowAny {
            rover.move()
            anotherRover.move()
        }
    }

    @Test
    fun `coordinate should be empty again when rover move from it`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val anotherRover = Rover(Coordinate(0, 1), Direction.NORTH)
        val plateu = Plateu(2, 2)
        plateu.land(anotherRover)
        plateu.land(rover)

        shouldNotThrowAny {
            anotherRover.move()
            rover.move()
        }
    }


    // ----------------------------------------TURN TESTS
    // SOUTH turns
    @Test
    fun `when rover turn LEFT from SOUTH next direction should EAST`() {
        val rover = Rover(Coordinate(0, 0), Direction.SOUTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnLeft()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.EAST
    }

    @Test
    fun `when rover turn RIGHT from SOUTH next direction should WEST`() {
        val rover = Rover(Coordinate(0, 0), Direction.SOUTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnRight()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.WEST
    }

    // WEST turns
    @Test
    fun `when rover turn LEFT from WEST next direction should SOUTH`() {
        val rover = Rover(Coordinate(0, 0), Direction.WEST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnLeft()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.SOUTH
    }

    @Test
    fun `when rover turn RIGHT from WEST next direction should NORTH`() {
        val rover = Rover(Coordinate(0, 0), Direction.WEST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnRight()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.NORTH
    }

    // NORTH turns
    @Test
    fun `when rover turn RIGHT from NORH next direction should EAST`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnRight()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.EAST
    }

    @Test
    fun `when rover turn LEFT from NORTH next direction should WEST`() {
        val rover = Rover(Coordinate(0, 0), Direction.NORTH)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnLeft()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.WEST
    }

    // EAST turns
    @Test
    fun `when rover turn LEFT from EAST next direction should NORTH`() {
        val rover = Rover(Coordinate(0, 0), Direction.EAST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnLeft()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.NORTH
    }

    @Test
    fun `when rover turn RIGHT from EAST next direction should SOUTH`() {
        val rover = Rover(Coordinate(0, 0), Direction.EAST)
        val plateu = Plateu(1, 1)
        plateu.land(rover)

        rover.turnRight()

        rover.coordinates shouldBe Coordinate(0, 0)
        rover.direction shouldBe Direction.SOUTH
    }

}
