class MockBoard(val positions: List[String], var timesMoved: Int) extends Board {
  var fullStub = false
  var wonStub = false
  var moveCount = 0

  def this(timesMoved: Int) = {
    this(List[String] (null,null,null,
                       null,null,null,
                       null,null,null),
         timesMoved)
  }

  def this(positions: List[String]) = {
    this(positions, 0)
  }

  def this() = {
    this(0)
  }

  override def won: Boolean = {
    return wonStub || super.won()
  }

  override def full: Boolean = {
    return fullStub || super.full()
  }

  def setWon(isWon: Boolean) = {
    wonStub = isWon
  }

  def setFull(isFull: Boolean) = {
    fullStub = isFull
  }

  def move (mark: String, position: Int): Board = {
    val newBoard = new MockBoard (newPositions(mark, position))
    moveCount += 1
    return newBoard
  }
}