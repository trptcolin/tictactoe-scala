package trptcolin.tictactoescala.gui

import java.awt.{Color, Font, Cursor}
import javax.swing.BorderFactory
import swing._
import swing.event._

object SwingUi {
  val lime = new Color(190, 219, 57)
  val orange = new Color(253, 64, 0)
  val turquoise = new Color(31, 138, 112)
  val yellow = new Color(255, 225, 26)
  val blue = new Color(0, 67, 88)

  val transparent = new Color(0, 0, 0, 0)

  val buttonBorder = BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(orange, 2),
    BorderFactory.createLineBorder(turquoise, 10)
    )

  val hoverButtonBorder = BorderFactory.createCompoundBorder(
    BorderFactory.createLineBorder(orange, 2),
    BorderFactory.createLineBorder(lime, 10)
    )

  def addButtonStyles(btn: Component): Unit = {
    btn.xLayoutAlignment = 0.5

    btn.opaque = true
    btn.border = buttonBorder
    btn.background = turquoise
    btn.foreground = lime
    btn.font = new Font("SansSerif", Font.BOLD, 14)

    btn.reactions += buttonReactions
    btn.listenTo(btn.Mouse.clicks, btn.Mouse.moves)
  }

  def addSquareStyles(square: Component, teamColor: Color, squareIndex: Int): Unit = {
    square.opaque = true

    val borderWidth = 3
    val borderBottom = if ((squareIndex / 3) == 2) 0 else borderWidth
    val borderRight = if ((squareIndex % 3) == 2) 0 else borderWidth
    square.border = BorderFactory.createMatteBorder(0, 0, borderBottom, borderRight, SwingUi.lime)

    square.font = new Font("SansSerif", Font.PLAIN, 100)
    square.foreground = teamColor

    square.listenTo(square.Mouse.clicks, square.Mouse.moves)
  }

  val buttonReactions: PartialFunction[Event, Unit] = {
    case MouseEntered(source, point, modifiers) =>
      source.background = lime
      source.foreground = blue
      source.border = hoverButtonBorder
      source.cursor = new Cursor(Cursor.HAND_CURSOR)
    case MouseExited(source, point, modifiers) =>
      source.background = turquoise
      source.foreground = lime
      source.border = buttonBorder
      source.cursor = new Cursor(Cursor.DEFAULT_CURSOR)
  }



  def setMainStyles(frame: Frame): Unit = {
    frame.title = "Tic-Tac-Toe"
    frame.preferredSize = (500, 500)
    frame.background = blue
    frame.foreground = lime
  }

  def addPlayAgainAction(square: Component, action: () => Unit): Unit = {
    square.reactions += {
      case MousePressed(source, point, modifiers, clicks, triggersPopup) =>
        action()
    }
  }

  def squareReactions(color: Color): PartialFunction[Event, Unit] = {
    case MouseEntered(source, point, modifiers) =>
      source.background = turquoise
      source.foreground = blue
      source.cursor = new Cursor(Cursor.HAND_CURSOR)
    case MouseExited(source, point, modifiers) =>
      source.background = blue
      source.foreground = color
      source.cursor = new Cursor(Cursor.DEFAULT_CURSOR)
  }

}