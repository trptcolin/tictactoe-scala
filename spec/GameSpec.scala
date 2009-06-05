import java.io.ByteArrayOutputStream
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class GameSpec extends Spec with ShouldMatchers {
  describe("GameSpec"){
    it("should recognize game over for full board"){
      val board = new MockBoard()
      board.setFull(true)
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()
      val game = new Game(board, Array(player1, player2), display)

      game.over(board) should be(true)
    }

    it("should recognize game over for won board"){
      val board = new MockBoard()
      board.setWon(true)
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()
      val game = new Game(board, Array(player1, player2), display)

      game.over(board) should be(true)
    }

    it("should recognize game not over"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()
      val game = new Game(board, Array(player1, player2), display)

      game.over(board) should equal(false)
    }

//    it("should start with the first player"){
//      val board = new MockBoard()
//      val player1 = new MockPlayer("X")
//      val player2 = new MockPlayer("O")
//      val game = new ConsoleGame(board, Array(player1, player2))
//
//      val movePicked = game.getNextMove(0, board)
//
//      player1.timesMoveCalled should equal(1)
//      player2.timesMoveCalled should equal(0)
//
//    }
//
//    it("should continue with the second player"){
//      val board = new MockBoard()
//      val player1 = new MockPlayer("X")
//      val player2 = new MockPlayer("O")
//      val game = new ConsoleGame(board, Array(player1, player2))
//
//      game.getNextMove(0, board)
//      game.getNextMove(1, board)
//
//      player1.timesMoveCalled should equal(1)
//      player2.timesMoveCalled should equal(1)
//    }
//
//    it("should go back to the first player"){
//      val board = new MockBoard()
//      val player1 = new MockPlayer("X")
//      val player2 = new MockPlayer("O")
//      val game = new ConsoleGame(board, Array(player1, player2))
//
//      game.getNextMove(0, board)
//      game.getNextMove(1, board)
//      game.getNextMove(0, board)
//
//      player1.timesMoveCalled should equal(2)
//      player2.timesMoveCalled should equal(1)
//    }

    it("should start a game"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()
      val game = new Game(board, Array(player1, player2), display)

      val realOut = Console.out
      val out = new ByteArrayOutputStream()
      Console.setOut(out)
      board.setWon(true)

      val newBoard = game.start

      Console.setOut(realOut)

      game.over(newBoard) should be(true)
    }

    it("should recognize a valid move") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()
      val game = new Game(board, Array(player1, player2), display)

      game.isValidMove(board, 0) should be (true)
    }

    it("should recognize an invalid move") {
      val board = new MockBoard(List(
        "X", null, null,
        null, null, null,
        null, null, null))
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()

      val game = new Game(board, Array(player1, player2), display)

      game.isValidMove(board, 0) should be (false)
    }

    it("should refresh board state") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val display = new MockDisplay()

      val game = new Game(board, Array(player1, player2), display)

      game.refreshBoardState()

      display.timesRefreshed should equal (1)
    }

  }
}