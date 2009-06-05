object TicTacToe{
  def main(args: Array[String]){
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")
    val player2 = new ComputerPlayer("O")

    new ConsoleGame(board, Array(player1, player2)).start
  }
}