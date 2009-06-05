trait Player{
  val playerMark: String
  def move(board: Board): Int
}

object Player {
  def otherMark(mark: String): String ={
    if (mark == "X")
      "O"
    else
      "X"
  }
}
