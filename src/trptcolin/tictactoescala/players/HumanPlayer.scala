package trptcolin.tictactoescala.players
import basegame.{Board, Player}

class HumanPlayer(val playerMark: String) extends Player {
  def move(board: Board): Int = {
    return -1
  }
}