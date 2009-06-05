class Game(startingBoard: Board, players: Array[Player], display: Display){

  // TODO: eliminate board state on this game.
  var board = startingBoard

  val fsm = new GameContext(this)

  // TODO: test me
  def start(): Board ={
    fsm.enterStartState()
    fsm.GameTypeChosen("")
    return board
  }

  // TODO: test me
  def playerMove(playerIndex: Int, square: Int) = {
    board = board.move(players(playerIndex).playerMark, square)
  }

  // TODO: test me
  def getNextMove(playerIndex: Int, board: Board): Int = {
    if(over(board)) {
      fsm.GameOver()
      return -1
    }
    else {
      val pickedPosition = players(playerIndex).move(board)
      fsm.PickSquare(board, pickedPosition)
      return pickedPosition
    }
  }

  // TODO: test me
  def over(board: Board): Boolean ={
    return (board.full || board.won)
  }

  // TODO: test me
  def over(): Boolean = {
    over(board)
  }

  // TODO: test me
  def isValidMove(board: Board, square: Int): Boolean ={
    return board.positions(square) == null
  }

  def refreshBoardState(): Unit = {
    display.refreshBoardState(board)
  }
}