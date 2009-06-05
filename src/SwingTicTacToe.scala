import scala.swing._

object SwingTicTacToe extends SimpleGUIApplication {
  def top = new MainFrame {
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")
    val player2 = new HumanPlayer("O")
    
    val display = new SwingDisplay(this)

    new Thread {
      override def run() = {
        new Game(board, Array(player1, player2), display).start
      }
    }.start
  }
}
