package com.stringconcat.marsrover

import com.stringconcat.marsrover.exception.CoordinateNotEmptyException
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class PlateuTest {
    // ---------------------------------------- PLATEU TESTS
    @Test
    fun `init Plateu`() {
        val plateu = Plateu(1, 1)
        plateu shouldBe Plateu(1, 1)
    }

    @Test
    fun `land rover should successfully land on empty place`() {
        val plateu = Plateu(10, 10)
        val rover = Rover(Coordinate(1, 1), Direction.NORTH)
        shouldNotThrowAny {
            plateu.land(rover)
        }
    }

    @Test
    fun `land rover should not land on busy place`() {
        val plateu = Plateu(10, 10)
        val firstRover = Rover(Coordinate(1, 1), Direction.NORTH)
        plateu.land(firstRover)
        shouldThrow<CoordinateNotEmptyException> {
            val secondRover = Rover(Coordinate(1, 1), Direction.NORTH)
            plateu.land(secondRover)
        }
    }
}
