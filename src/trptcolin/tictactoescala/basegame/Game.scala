package trptcolin.tictactoescala.basegame

abstract class Game(startingBoard: Board, players: Array[Player]){
  val fsm = new GameContext(this)

  // TODO: try to eliminate modification of board state
  var board = startingBoard
  def clearBoard(): Unit = {
    board = new BoardImpl()
  }

  def refreshBoardState(board: Board): Unit
  def decidePlayAgain(): Unit
  def decideGameType(): Unit
  
  def start(): Unit ={
    fsm.enterStartState()
  }

  // TODO: test me
  def setGameType(player1: Player, player2: Player) {
    players(0) = player1
    players(1) = player2

    fsm.GameTypeChosen()
  }

  def playerMove(playerIndex: Int, square: Int) = {
    board = board.move(players(playerIndex).playerMark, square)
  }

  def getNextMove(playerIndex: Int, board: Board): Unit = {
    if(board.over()) {
      fsm.GameOver()
    }
    else {
      val pickedPosition = players(playerIndex).move(board)
      pickIfValidMove(board, pickedPosition)
    }
  }

  def pickIfValidMove(board: Board, square: Int): Unit = {
    if(isValidMove(board, square)) {
      fsm.PickSquare(board, square)
    }
  }

  def isValidMove(board: Board, square: Int): Boolean ={
    return board.openPositions.exists(_ == square)
  }

  def refreshBoardState(): Unit = {
    refreshBoardState(board)
  }
}

  