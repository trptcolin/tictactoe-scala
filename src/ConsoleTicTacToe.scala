object ConsoleTicTacToe{
  def main(args: Array[String]){
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")
    val player2 = new ComputerPlayer("O")
    val display = new ConsoleDisplay()

    new Game(board, Array(player1, player2), display).start
  }
}