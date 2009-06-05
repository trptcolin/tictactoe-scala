class MockDisplay extends Display {
  var timesRefreshed = 0
  
  def refreshBoardState(board: Board): Unit = {
    timesRefreshed += 1
  }
}