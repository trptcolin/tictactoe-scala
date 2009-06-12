package trptcolin.tictactoescala.basegame

class BoardImpl(val positions: List[String]) extends Board
{
  def this() =
  {
    this (List[String](
      null, null, null,
      null, null, null,
      null, null, null))
  }

  def move(mark: String, position: Int): Board =
  {
    return new BoardImpl(newPositions(mark, position))
  }

  def clear(): Board =
  {
    new BoardImpl()
  }
}