class MockPlayer(val playerMark: String) extends Player {
  var timesMoveCalled = 0

  def move(board: Board): Int = {
    timesMoveCalled += 1

    val openPosition = board.positions.findIndexOf(_ == null)

    return openPosition
//    if (openPosition == -1)
//      return null
//    else
//      return board.move(mark, openPosition)
  }
}