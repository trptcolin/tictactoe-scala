class ConsoleDisplay extends Display {
  // TODO: test me
  def refreshBoardState(board: Board): Unit = {
    Console.println(ConsoleDisplay.stringify(board))
  }
  // TODO: test me
  def refreshBoardState(board: Board, str: String): Unit = {
    Console.println(str)
    refreshBoardState(board)
  }
}

object ConsoleDisplay {
  
  def stringify(board: Board): String = {
    def stringForPosition(position: Int): String = {
      val mark = board.positions(position)
      if(mark == null)
        return " "
      else
        return mark
    }

    def addHorizontalLine(boardString: String): String = {
      return boardString + "\n-----------\n"
    }

    def addRowOfSquares(boardString: String, row: Int): String = {
      return boardString +  " " +
                            stringForPosition(3 * row) +
                            " | " +
                            stringForPosition(3 * row + 1) +
                            " | " +
                            stringForPosition(3 * row + 2) +
                            " "
    }


    var boardString = ""
    for(row <- 0 to 2) {
      boardString = addRowOfSquares(boardString, row)
      if(row < 2)
        boardString = addHorizontalLine(boardString)
    }
    return boardString + "\n"
  }

}