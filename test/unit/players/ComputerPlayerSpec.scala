package trptcolin.tictactoescala.players

import basegame.MockBoard
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class ComputerPlayerSpec extends Spec with ShouldMatchers
{
  describe("ComputerPlayer")
  {
    it("should move to make a horizontal win")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        "X", "X", null,
        null, null, null,
        null, null, null))

      val moveMade = player.move(board)

      moveMade should equal(2)
    }

    it("should move to make another horizontal win")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        null, null, null,
        null, null, null,
        "X", "X", null))

      val moveMade = player.move(board)

      moveMade should equal(8)
    }

    it("should move to make a vertical win")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        "X", null, null,
        "X", null, null,
        null, null, null))

      val moveMade = player.move(board)

      moveMade should equal(6)
    }

    it("should move to block a vertical win by opponent")
    {
      val player = new ComputerPlayer("O")
      val board = new MockBoard(List(
        "X", null, null,
        "X", "O", null,
        null, null, null))

      val moveMade = player.move(board)

      moveMade should equal(6)
    }

    it("should move to block a horizontal win by opponent")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        "X", null, null,
        "O", "O", null,
        null, null, null))

      val moveMade = player.move(board)

      moveMade should equal(5)
    }

    it("should fork if possible")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        "X", "O", "X",
        null, null, null,
        null, null, "O"))

      val moveMade = player.move(board)

      moveMade should equal(6)
    }

    it("should avoid certain losses when X starts in corner")
    {
      val player = new ComputerPlayer("O")
      val board = new MockBoard(List(
        "X", null, null,
        null, null, null,
        null, null, null))

      val moveMade = player.move(board)

      moveMade should equal(4)
    }

    it("should get in position for a win if the other player makes a mistake")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard(List(
        "X", "O", null,
        null, null, null,
        null, null, null))

      val moveMade = player.move(board)

      List(3, 4, 7) should contain(moveMade)
    }

    it("should move first")
    {
      val player = new ComputerPlayer("X")
      val board = new MockBoard()

      player.move(board) should equal (0)
    }
  }
}