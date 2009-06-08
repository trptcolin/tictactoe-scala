import scala.swing._
import event._

object SwingTicTacToe extends SimpleGUIApplication {

  def top = new MainFrame {
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")

    // TODO: create mechanism to allow this player to move
    val player2 = new HumanPlayer("O")

    title = "Tic-Tac-Toe"
    preferredSize = (500, 500)

    val game = new SwingGame(board, Array(player1, player2), this)

    new Thread {
      override def run() ={
        game.start
      }
    }.start
  }
}