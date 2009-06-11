import java.io.ByteArrayOutputStream
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class TestGame(board: Board, players: Array[Player]) extends Game(board, players) {
  override val fsm = new MockGameContext(this)
  def refreshBoardState(board: Board) ={}
  def decidePlayAgain: Unit ={}
  def decideGameType: Unit ={}
}

class GameSpec extends Spec with ShouldMatchers {
  describe("GameSpec"){

    it("should recognize a valid move"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.isValidMove(board, 0) should be(true)
    }

    it("should recognize an invalid move"){
      val board = new MockBoard(List(
        "X", null, null,
        null, null, null,
        null, null, null))
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")

      val game = new TestGame(board, Array(player1, player2))

      game.isValidMove(board, 0) should be(false)
    }

    it("should start a game"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.start

      game.fsm.getState() should equal(GameFSM.Starting)
    }

    it("should make move on behalf of player"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.playerMove(0, 3)
      game.board.positions(3) should equal("X")
      game.board.positions(0) should equal(null)
    }

    it("should advance the game state if the move is valid"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.pickIfValidMove(board, 0)

      game.fsm.pickSquareCalled should equal(true)
    }

    it("should not advance the game state if the move is invalid"){
      val board = new MockBoard(List(
        "X", null, null,
        null, null, null,
        null, null, null))
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.pickIfValidMove(board, 0)

      game.fsm.pickSquareCalled should equal(false)
    }

    it("should get next move from the player if the game is not over"){
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.getNextMove(0, board)

      player1.moveCalled should equal(true)
      game.fsm.pickSquareCalled should equal(true)
    }

    it("should end game if the game is over"){
      val board = new MockBoard(List(
      "X", "X", "X",
      null, null, null,
      null, null, null
        ))
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new TestGame(board, Array(player1, player2))

      game.getNextMove(0, board)

      game.fsm.gameOverCalled should equal(true)
    }
  }
}