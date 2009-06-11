import java.awt.{Color, Font}
import javax.swing.BorderFactory
import swing._
import swing.event._

class SwingGame(startingBoard: Board, players: Array[Player], frame: Frame) extends Game(startingBoard, players) {
  private def generatePlayersButton(player1Type: String, player2Type: String): Component = {
    new Label (player1Type + " (X) vs. " + player2Type + " (O)") {
      SwingUi.addButtonStyles (this)
      reactions += {
        case MousePressed (source, point, modifiers, clicks, triggersPopup) =>
          setGameType (
            Player.generate(player1Type, "X"), Player.generate(player2Type, "O"))
      }
    }
  }

  // TODO: test me
  def decideGameType: Unit = {
    frame.contents = new BoxPanel(Orientation.Vertical) {
      contents += new BoxPanel(Orientation.Vertical) {
        xLayoutAlignment = 0.5

        border = BorderFactory.createMatteBorder(0, 0, 10, 0, new Color(0, 0, 0, 0))

        contents += new Label("Choose your Players:") {
          preferredSize = (frame.preferredSize.width, frame.preferredSize.height / 3)
          font = new Font("SansSerif", Font.BOLD, 28)
          foreground = SwingUi.lime
          verticalAlignment = Alignment.Bottom
        }
      }

      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        contents += generatePlayersButton("Computer", "Computer")
        contents += generatePlayersButton("Human", "Computer")
        contents += generatePlayersButton("Computer", "Human")
        contents += generatePlayersButton("Human", "Human")
      }
    }
  }

  def refreshBoardState(board: Board) = {
    frame.contents = boardStructure(board)
  }

  // TODO: test me
  def decidePlayAgain: Unit = {
    frame.contents = new BoxPanel(Orientation.Vertical) {
      contents += boardStructure(board)
      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        border = BorderFactory.createLineBorder(SwingUi.transparent, 15)

        contents += new Label("Play Again") {
          xLayoutAlignment = 0.5

          SwingUi.addButtonStyles(this)
          reactions += {
            case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
              fsm.PlayAgain()
          }
        }
      }
    }
  }

  // TODO: test me
  def boardStructure(board: Board) = {
    new GridPanel(3, 3) {
      border = BorderFactory.createLineBorder(SwingUi.transparent, 10)

      (0 to 8).foreach {
        i =>
                contents += new Label(board.positions(i)) {
                  opaque = true

                  val borderWidth = 3
                  val borderBottom = if ((i / 3) == 2) 0
                  else borderWidth
                  val borderRight = if ((i % 3) == 2) 0
                  else borderWidth
                  border = BorderFactory.createMatteBorder(0, 0, borderBottom, borderRight, SwingUi.lime)

                  font = new Font("SansSerif", Font.PLAIN, 100)
                  val teamColor =
                  if (board.positions(i) == "X")
                    SwingUi.turquoise
                  else
                    SwingUi.orange

                  foreground = teamColor

                  if (!board.over && isValidMove(board, i))
                    reactions += SwingUi.squareReactions(teamColor)
                  reactions += {
                    case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
                      pickIfValidMove(board, i)
                  }

                  listenTo(Mouse.clicks, Mouse.moves)
                }
      }
    }
  }
}