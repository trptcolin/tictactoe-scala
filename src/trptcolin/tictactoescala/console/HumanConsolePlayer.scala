package trptcolin.tictactoescala.console
import basegame.{Player, Board}


import java.io.EOFException
class HumanConsolePlayer(val playerMark: String) extends Player {
  def move(board: Board): Int ={
    Console.println("Make your move (0-8): ")
    try{
      val attemptedMove = Console.readInt()

      if (board.invalidMove(attemptedMove))
        throw new IllegalArgumentException()
      else
        return attemptedMove
    }
    catch{
      case ex: NumberFormatException =>
        Console.println("There was a problem with your input! Digits only!")
        move(board)
      case ex: IllegalArgumentException =>
        Console.println("There was a problem with your input! Make sure the move is valid!")
        move(board)
      case ex: EOFException =>
        Console.println("Goodbye")
        return -1
    }
  }
}