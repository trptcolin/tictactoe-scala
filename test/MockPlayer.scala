class MockPlayer(val playerMark: String) extends Player {
  var moveCalled = false

  def move(board: Board): Int = {
    moveCalled = true
    val openPosition = board.openPositions.first

    return openPosition
  }
}