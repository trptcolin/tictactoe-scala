class ComputerPlayer(val playerMark: String) extends Player {
  val searchDepth = 9
  val highScore = 1000

  def move(board: Board): Int ={
    val positionsWithScores = board.openPositions.map(
      pos =>
              List(pos, scoreFor(board.move(playerMark, pos))))

    val bestPositionWithScore = positionsWithScores.reduceLeft[List[Int]](
      (a: List[Int], b: List[Int]) =>
              if (a(1) < b(1))
                b
              else
                a)

    return bestPositionWithScore(0)
  }

  private def scoreFor(board: Board): Int ={
    def scoreForDepth(board: Board, mark: String, depth: Int): Int ={
      if (board.wonBy(mark))
        return highScore - depth
      else if (board.won)
        return -highScore + depth
      else if (board.full || depth > searchDepth)
        return 0 + depth
      else{
        val otherMark = Player.otherMark(mark)
        val possibleResults = board.openPositions.map(board.move(otherMark, _))
        val possibleScores = possibleResults.map{
          scoreForDepth(_, otherMark, depth + 1)
        }
        val bestScoreForOpponent = possibleScores.reduceLeft{
          (a, b) =>
                  if (a < b) b
                  else a
        }

        return -bestScoreForOpponent
      }
    }

    scoreForDepth(board, playerMark, 0)
  }

  private def printMoveDecisions(mark: String, board: Board, bestMove: Int) ={
    println("scores for player " + mark + ": " + board.openPositions.map(
      (pos: Int) =>
              scoreFor(board.move(mark, pos)))
      )

    println("best move: " + bestMove)
  }

}