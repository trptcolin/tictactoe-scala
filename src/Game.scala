abstract class Game(startingBoard: Board, players: Array[Player]){

  var board = startingBoard

  val fsm = new GameContext(this)

  // TODO: get this method under test
  def start(): Board ={
    fsm.enterStartState()
    fsm.GameTypeChosen("")
    return board
  }

  def playerMove(playerIndex: Int, square: Int) = {
    board = board.move(players(playerIndex).playerMark, square)
  }

  def getNextMove(playerIndex: Int, board: Board): Int = {
    if(over(board)) {
      return -1
    }
    else {
      val pickedPosition = players(playerIndex).move(board)
      fsm.PickSquare(board, pickedPosition)
      return pickedPosition
    }
  }

  def over(board: Board): Boolean ={
    return (board.full || board.won)
  }

  def over(): Boolean = {
    over(board)
  }

  def isValidMove(board: Board, square: Int): Boolean ={
    return board.positions(square) == null
  }

  def refreshBoardState(): Unit
}