package trptcolin.tictactoescala.basegame

object Board{
  val winSets =
  List(
    List(0, 1, 2),
    List(3, 4, 5),
    List(6, 7, 8),
    List(0, 3, 6),
    List(1, 4, 7),
    List(2, 5, 8),
    List(0, 4, 8),
    List(2, 4, 6))

}

trait Board{
  def full(): Boolean ={
    return !positions.exists(_ == null)
  }

  def openPositions(): List[Int] ={
    if (full())
      return List()
    else{
      val positionsList =
        for {
          position <- (0 to 8).toList
          if positions(position) == null
        } yield position

      return positionsList
    }
  }

  def won(): Boolean ={
    return wonBy("X") || wonBy("O")
  }

  def wonBy(mark: String): Boolean ={
    return Board.winSets.exists(winSet => winSet.forall(positions(_) == mark))
  }

  def over(): Boolean = {
    return full() || won()
  }

  def positions: List[String]
  def clear(): Board

  def move(mark: String, position: Int): Board

  override def toString: String ={
    return positions.toString
  }

  def newPositions(mark: String, position: Int): List[String] ={
    val newPositions = new Array[String](9)
    positions.copyToArray(newPositions, 0)

    newPositions(position) = mark
    return newPositions.toList
  }

  def invalidMove(position: Int): Boolean ={
    return position < 0 || position > 8 || positions(position) != null
  }
}