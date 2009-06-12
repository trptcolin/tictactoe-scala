package trptcolin.tictactoescala.basegame

import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class TestBoard(val positions: List[String]) extends Board
{
  def this() =
  {
    this (List[String](
      null, null, null,
      null, null, null,
      null, null, null))
  }

  def clear(): Board =
  {
    new TestBoard()
  }
  def move(mark: String, position: Int): Board =
  {
    return new TestBoard(newPositions(mark, position))
  }
}

class BoardSpec extends Spec with ShouldMatchers
{
  describe("Board")
  {
    it("should not be full")
    {
      val board = new TestBoard()

      board.wonBy("X") should be(false)
      board should not be ('full)
    }

    it("should be full")
    {
      val positions = List(
        "X", "X", "X",
        "X", "X", "X",
        "X", "X", "X")
      val board = new TestBoard(positions)

      board should be('full)
      board should be('over)
    }

    it("should recognize generic win")
    {
      val positions = List(
        "X", "X", "X",
        null, null, null,
        null, null, null)

      val board = new TestBoard(positions)

      board should be('won)
      board should be('over)
    }

    it("should recognize 1st horizontal win")
    {
      val positions = List(
        "X", "X", "X",
        null, null, null,
        null, null, null)

      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 2nd horizontal win")
    {
      val positions = List(
        null, null, null,
        "X", "X", "X",
        null, null, null)
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 3rd horizontal win")
    {
      val positions = List(
        null, null, null,
        null, null, null,
        "X", "X", "X")
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 1st vertical win")
    {
      val positions = List(
        "X", null, null,
        "X", null, null,
        "X", null, null)
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 2nd vertical win")
    {
      val positions = List(
        null, "X", null,
        null, "X", null,
        null, "X", null)
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 3rd vertical win"){
      val positions = List(
        null, null, "X",
        null, null, "X",
        null, null, "X")
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 1st diagonal win")
    {
      val positions = List("X", null, null,
        null, "X", null,
        null, null, "X")
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should recognize 2nd diagonal win")
    {
      val positions = List(null, null, "X",
        null, "X", null,
        "X", null, null)
      val board = new TestBoard(positions)

      board.wonBy("X") should be(true)
    }

    it("should populate a square"){
      val board = new TestBoard().move("X", 0)

      board.positions(0) should equal("X")

      for (i <- 1 to 8)
        board.positions(i) should equal(null)
    }

    it("should recognize a move as invalid - out of range")
    {
      val board = new TestBoard()

      board.invalidMove(12) should equal(true)
    }

    it("should recognize a move as invalid - taken")
    {
      val board = new TestBoard().move("X", 0)

      board.invalidMove(0) should equal(true)
    }

    it("should have open positions 0-5")
    {
      val positions = List(null, null, null,
        null, null, null,
        "X", "X", "X")
      val board = new TestBoard(positions)

      board.openPositions should equal(List(0, 1, 2, 3, 4, 5))
    }
  }
}