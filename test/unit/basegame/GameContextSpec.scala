package trptcolin.tictactoescala.basegame

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class GameContextSpec extends Spec with ShouldMatchers {
  describe("GameContext") {
    it("should start by deciding on the game type") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm

      fsm.enterStartState()

      fsm.getState.getName should equal("GameFSM.Starting")
      game.decideGameTypeCalled should equal(true)
    }

    it("should clear board and go to Player1 when game type chosen") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm
      
      fsm.GameTypeChosen()

      fsm.getState().getName() should equal ("GameFSM.Player1")
      game.clearBoardCalled should equal(true)
    }

    it("should refresh board state and get next move on first move for Player1") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm

      fsm.GameTypeChosen()
     
      game.refreshBoardStateCalled should equal (true)
      game.getNextMoveCalled should equal (true)
      game.getNextMoveCalledWith should equal(0, board)
    }

    it("should pick a square for Player1") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm
      fsm.GameTypeChosen()
      fsm.getState().getName() should equal("GameFSM.Player1")

      fsm.PickSquare(board, 0)

      fsm.getState.getName() should equal("GameFSM.Player2")
      game.playerMoveCalled should equal(true)
      game.playerMoveCalledWith should equal(0, 0)
    }

    it("should quit on Player1") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm
      fsm.GameTypeChosen()
      fsm.getState().getName() should equal("GameFSM.Player1")
      
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
    }

    it("should refresh board state and get next move on first move for Player2") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm

      fsm.GameTypeChosen()
      game.refreshBoardStateCalled = false
      game.getNextMoveCalled = false
      game.getNextMoveCalledWith = (-1, board)

      fsm.PickSquare(board, 0)

      game.refreshBoardStateCalled should equal (true)
      game.getNextMoveCalled should equal (true)
      game.getNextMoveCalledWith should equal(1, board)
    }

    it("should pick a square for Player2") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm

      fsm.GameTypeChosen()
      fsm.PickSquare(board, 6)

      fsm.getState().getName() should equal("GameFSM.Player2")
      fsm.PickSquare(board, 4)

      fsm.getState.getName() should equal("GameFSM.Player1")
      game.playerMoveCalled should equal(true)
      game.playerMoveCalledWith should equal(1, 4)
    }

    it("should quit on Player2") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm
      fsm.GameTypeChosen()
      fsm.PickSquare(board, 5)

      fsm.getState().getName() should equal("GameFSM.Player2")
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
    }

    it("should play again") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new MockGame(board, Array(player1, player2))

      val fsm = game.fsm
      fsm.GameTypeChosen()
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
      fsm.PlayAgain()

      fsm.getState.getName() should equal("GameFSM.Starting")
    }
  }
}