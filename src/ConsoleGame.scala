class ConsoleGame(startingBoard: Board, players: Array[Player]) extends Game(startingBoard, players) {

  def refreshBoardState(): Unit = {
    Console.println(ConsoleDisplay.toString(board))
  }

  def refreshBoardState(str: String): Unit = {
    Console.println(str)
    refreshBoardState()
  }
}