import java.awt.{Font, Color}
import javax.swing.BorderFactory
import scala.swing._

object SwingTicTacToe extends SimpleGUIApplication {
  def top = new MainFrame {
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")
    val player2 = new ComputerPlayer("O")

    val game = new SwingGame(this, board, Array(player1, player2))

    game.setFrameAttributes(this, board)

    new Thread {
      override def run() = {
        game.start
      }
    }.start
  }
}
