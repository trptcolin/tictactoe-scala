package trptcolin.tictactoescala.console
import basegame._

import java.io.EOFException
import players.PlayerFactory

class ConsoleGame(startingBoard: Board, val players: Array[Player]) extends Game(startingBoard, players)
{
  def refreshBoardState(board: Board): Unit =
  {
    Console.println(ConsoleGame.stringify(board))
  }

  def decidePlayAgain: Unit =
  {
    Console.println("Press any key to play again.")
    Console.readLine()
    
    fsm.PlayAgain()
  }

  def decideGameType(): Unit =
  {
    Console.println("What type of game would you like to play?")
    Console.println("  1 - Computer (X) vs. Computer (O)")
    Console.println("  2 - Computer (X) vs. Human (O)")
    Console.println("  3 - Human (X) vs. Computer (O)")
    Console.println("  4 - Human (X) vs. Human (O)")

    try
    {
      val attemptedGameType = Console.readInt()

      if (attemptedGameType > 4 || attemptedGameType < 1)
      {
        throw new NumberFormatException()
      }
      else
      {
        attemptedGameType match
        {
          case 1 =>
            setGameType(PlayerFactory.generate("Computer", "X"), PlayerFactory.generate("Computer", "O"))
          case 2 =>
            setGameType(PlayerFactory.generate("Computer", "X"), PlayerFactory.generate("HumanConsole", "O"))
          case 3 =>
            setGameType(PlayerFactory.generate("HumanConsole", "X"), PlayerFactory.generate("Computer", "O"))
          case 4 =>
            setGameType(PlayerFactory.generate("HumanConsole", "X"), PlayerFactory.generate("HumanConsole", "O"))
        }
      }
    }
    catch
    {
      case ex: NumberFormatException =>
        Console.println("There was a problem with your input! Digits only!")
        decideGameType()
      case ex: EOFException =>
        Console.println("Goodbye")
        return -1
    }
  }
}

object ConsoleGame
{

  def stringify(board: Board): String =
  {
    def stringForPosition(position: Int): String =
    {
      val mark = board.positions(position)
      if(mark == null)
        return " "
      else
        return mark
    }

    def addHorizontalLine(boardString: String): String =
    {
      return boardString + "\n-----------\n"
    }

    def addRowOfSquares(boardString: String, row: Int): String =
    {
      return boardString +  " " +
                            stringForPosition(3 * row) +
                            " | " +
                            stringForPosition(3 * row + 1) +
                            " | " +
                            stringForPosition(3 * row + 2) +
                            " "
    }


    var boardString = ""
    for(row <- 0 to 2)
    {
      boardString = addRowOfSquares(boardString, row)
      if(row < 2)
        boardString = addHorizontalLine(boardString)
    }
    return boardString + "\n"
  }
}