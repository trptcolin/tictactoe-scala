package trptcolin.tictactoescala.basegame

import players._

trait Player{
  val playerMark: String
  def move(board: Board): Int
}

object Player {
  def otherMark(mark: String): String ={
    if (mark == "X")
      "O"
    else
      "X"
  }

  def generate(playerType: String, mark: String): Player = {
    playerType match {
      case "Computer" =>
        new ComputerPlayer(mark)
      case "Human" =>
        new HumanPlayer(mark)
    }
  } 
}
