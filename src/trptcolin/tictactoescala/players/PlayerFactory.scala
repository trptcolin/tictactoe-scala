package trptcolin.tictactoescala.players

import basegame.Player

object PlayerFactory {
  def generate(playerType: String, mark: String): Player = {
    playerType match {
      case "Computer" =>
        new ComputerPlayer(mark)
      case "Human" =>
        new HumanSwingPlayer(mark)
      case "HumanConsole" =>
        new HumanConsolePlayer(mark)
    }
  }
}