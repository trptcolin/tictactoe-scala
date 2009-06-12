package trptcolin.tictactoescala.basegame

class MockGame(board: Board, players: Array[Player]) extends Game(board, players)
{
  override val fsm = new GameContext(this)

  var refreshBoardStateCalled = false
  var decideGameTypeCalled = false
  var clearBoardCalled = false

  var getNextMoveCalled = false
  var getNextMoveCalledWith: (Int, Board) = (-1, new MockBoard())

  var playerMoveCalled = false
  var playerMoveCalledWith = (-1, -1)

  def decideGameType(): Unit =
  {
    decideGameTypeCalled = true
  }

  def refreshBoardState(board: Board) =
  {
    refreshBoardStateCalled = true
  }
  def decidePlayAgain: Unit = {}

  override def clearBoard(): Unit =
  {
    clearBoardCalled = true
  }

  override def getNextMove(playerIndex: Int, board: Board) =
  {
    getNextMoveCalled = true
    getNextMoveCalledWith = (playerIndex, board)
  }

  override def playerMove(playerIndex: Int, square: Int) =
  {
    playerMoveCalled = true
    playerMoveCalledWith = (playerIndex, square)
  }
}

