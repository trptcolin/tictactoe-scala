package trptcolin.tictactoescala.gui
import basegame._
import players.PlayerFactory

import java.awt.{Color, Font}
import javax.swing.BorderFactory
import swing._
import swing.event._

class SwingGame(startingBoard: Board, players: Array[Player], frame: Frame) extends Game(startingBoard, players) {
  private def generatePlayersButton(player1Type: String, player2Type: String): Component = {
    new Label(player1Type + " (X) vs. " + player2Type + " (O)") {
      SwingUi.addButtonStyles(this)
      reactions += {
        case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
          setGameType(
            PlayerFactory.generate(player1Type, "X"), PlayerFactory.generate(player2Type, "O"))
      }
    }
  }

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

  def decidePlayAgain: Unit = {
    frame.contents = new BoxPanel(Orientation.Vertical) {
      contents += boardStructure(board)
      contents += new FlowPanel(FlowPanel.Alignment.Center) {
        border = BorderFactory.createLineBorder(SwingUi.transparent, 15)

        contents += new Label("Play Again") {
          SwingUi.addButtonStyles(this)
          SwingUi.addPlayAgainAction(this, () => fsm.PlayAgain())
        }
      }
    }
  }

  private def boardStructure(board: Board) = {
    new GridPanel(3, 3) {
      border = BorderFactory.createLineBorder(SwingUi.transparent, 10)

      (0 to 8).foreach { squareIndex =>
        contents += new Label(board.positions(squareIndex)) {
          val teamColor =
            if (board.positions(squareIndex) == "X")
              SwingUi.turquoise
            else
              SwingUi.orange

          SwingUi.addSquareStyles(this, teamColor, squareIndex)

          if (!board.over && isValidMove(board, squareIndex))
            reactions += SwingUi.squareReactions(teamColor)
          reactions += {
            case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
              pickIfValidMove(board, squareIndex)
          }
        }
      }
    }
  }
}