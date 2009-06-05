import java.awt.{Color, Font}
import swing.{GridPanel, Label, Frame}
import javax.swing.BorderFactory

class SwingGame(frame: Frame, startingBoard: Board, players: Array[Player]) extends Game(startingBoard, players) {

  def refreshBoardState() ={
    setFrameAttributes(frame, board)
  }

  def setFrameAttributes(frame: Frame, board: Board) ={
    frame.title = "Tic-Tac-Toe"
    frame.preferredSize = (500, 500)
    frame.contents = new GridPanel(3, 3) {
      for (i <- 0 to 8){
        val thisLabel = new Label(board.positions(i)) {
          border = BorderFactory.createLineBorder(Color.black, 1)
          font = new Font("SansSerif", Font.PLAIN, 100)
        }
        contents += thisLabel
      }
    }
  }
}