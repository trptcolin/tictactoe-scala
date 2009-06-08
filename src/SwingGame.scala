import java.awt.{Color, Font}
import swing._
import swing.event._
import javax.swing.BorderFactory

class SwingGame(startingBoard: Board, players: Array[Player], frame: Frame) extends Game(startingBoard, players) {
 
  frame.contents = boardStructure(board)

  // TODO: test me
  def decidePlayAgain: Unit = {
    val newContents = new BoxPanel(Orientation.Vertical) {
     
      contents += boardStructure(board)

      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        contents += new Button("Play Again") {
          xLayoutAlignment = 0.5
          reactions += {
            case ButtonClicked(b) =>
              fsm.PlayAgain()
          }
        }
      }
    }

    frame.contents = newContents
  }

  // TODO: test me
  def decideGameType: Unit = {
    frame.contents = new BoxPanel(Orientation.Vertical) {
      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        yLayoutAlignment = 0.5
        contents += new Label("What type of game would you like to play?")
      }

      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        contents += new Button("Computer (X) vs. Computer (O)") {
          reactions += { case ButtonClicked(b) => fsm.GameTypeChosen("ComputerVComputer") }
        }
        contents += new Button("Human (X) vs. Computer (O)") {
          reactions += { case ButtonClicked(b) => fsm.GameTypeChosen("HumanVComputer") }
        }
        contents += new Button("Computer (X) vs. Human (O)") {
          reactions += { case ButtonClicked(b) => fsm.GameTypeChosen("ComputerVHuman") }
        }
        contents += new Button("Human (X) vs. Human (O)") {
          reactions += { case ButtonClicked(b) => fsm.GameTypeChosen("HumanVHuman") }
        }
      }
    }
  }

  def refreshBoardState(board: Board) ={
    frame.contents = boardStructure(board)
  }

  // TODO: test
  def boardStructure(board: Board) = {
    new GridPanel(3, 3) {
      for (i <- 0 to 8){
        contents += new Label(board.positions(i)) {
          border = BorderFactory.createLineBorder(Color.black, 1)
          font = new Font("SansSerif", Font.PLAIN, 100)

          reactions += {
            case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
              fsm.PickSquare(board, i)
          }

          listenTo(Mouse.clicks)
        }
      }
    }
  }
}