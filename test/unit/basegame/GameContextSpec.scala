package trptcolin.tictactoescala.basegame

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, Spec}
class GameContextSpec extends Spec with ShouldMatchers with BeforeAndAfter
{
  var board: MockBoard = _
  var player1: MockPlayer = _
  var player2: MockPlayer = _
  var game: MockGame = _
  var fsm: GameContext = _

  override def beforeEach(): Unit =
  {
    board = new MockBoard()
    player1 = new MockPlayer("X")
    player2 = new MockPlayer("O")
    game = new MockGame(board, Array(player1, player2))
    fsm = game.fsm
  }

  describe("GameContext")
  {

    it("should start by deciding on the game type")
    {
      fsm.enterStartState()

      fsm.getState.getName should equal("GameFSM.Starting")
      game.decideGameTypeCalled should equal(true)
    }

    it("should clear board and go to Player1 when game type chosen")
    {
      fsm.GameTypeChosen()

      fsm.getState().getName() should equal ("GameFSM.Player1")
      game.clearBoardCalled should equal(true)
    }

    it("should refresh board state and get next move on first move for Player1")
    {
      fsm.GameTypeChosen()
     
      game.refreshBoardStateCalled should equal (true)
      game.getNextMoveCalled should equal (true)
      game.getNextMoveCalledWith should equal(0, board)
    }

    it("should pick a square for Player1")
    {
      fsm.GameTypeChosen()
      fsm.getState().getName() should equal("GameFSM.Player1")

      fsm.PickSquare(board, 0)

      fsm.getState.getName() should equal("GameFSM.Player2")
      game.playerMoveCalled should equal(true)
      game.playerMoveCalledWith should equal(0, 0)
    }

    it("should quit on Player1")
    {
      fsm.GameTypeChosen()
      fsm.getState().getName() should equal("GameFSM.Player1")
      
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
    }

    it("should refresh board state and get next move on first move for Player2")
    {
      fsm.GameTypeChosen()
      game.refreshBoardStateCalled = false
      game.getNextMoveCalled = false
      game.getNextMoveCalledWith = (-1, board)

      fsm.PickSquare(board, 0)

      game.refreshBoardStateCalled should equal (true)
      game.getNextMoveCalled should equal (true)
      game.getNextMoveCalledWith should equal(1, board)
    }

    it("should pick a square for Player2")
    {
      fsm.GameTypeChosen()
      fsm.PickSquare(board, 6)

      fsm.getState().getName() should equal("GameFSM.Player2")
      fsm.PickSquare(board, 4)

      fsm.getState.getName() should equal("GameFSM.Player1")
      game.playerMoveCalled should equal(true)
      game.playerMoveCalledWith should equal(1, 4)
    }

    it("should quit on Player2")
    {
      fsm.GameTypeChosen()
      fsm.PickSquare(board, 5)

      fsm.getState().getName() should equal("GameFSM.Player2")
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
    }

    it("should play again")
    {
      fsm.GameTypeChosen()
      fsm.GameOver()

      fsm.getState.getName() should equal("GameFSM.Ending")
      fsm.PlayAgain()

      fsm.getState.getName() should equal("GameFSM.Starting")
    }
  }
}