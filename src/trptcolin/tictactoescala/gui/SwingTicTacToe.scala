package trptcolin.tictactoescala.gui
import basegame.BoardImpl
import players._

import scala.swing._

object SwingTicTacToe extends SimpleGUIApplication {

  def top = new MainFrame {
    val board = new BoardImpl()
    val player1 = new ComputerPlayer("X")
    val player2 = new ComputerPlayer("O")

    SwingUi.setMainStyles(this)
    val game = new SwingGame(board, Array(player1, player2), this)

    new Thread {
      override def run() = {
        game.start
      }
    }.start
  }
}