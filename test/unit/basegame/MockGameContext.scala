package trptcolin.tictactoescala.basegame

class MockGameContext(owner: Game) extends GameContext(owner)
{
  var pickSquareCalled = false
  var gameOverCalled = false
  var gameTypeChosenCalled = false

  override def PickSquare(board: Board, square: Int) =
  {
    pickSquareCalled = true
  }

  override def GameOver() =
  {
    gameOverCalled = true
  }

  override def GameTypeChosen() =
  {
    gameTypeChosenCalled = true
  }
}

