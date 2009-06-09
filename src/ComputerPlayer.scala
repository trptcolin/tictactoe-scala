import collection.immutable.HashMap

class ComputerPlayer(val playerMark: String) extends Player {
  val searchDepth = 9
  val highScore = 1000
  
  var scoringHash: Map[(String, String), Int] = new HashMap[(String, String), Int]()

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

    val bestPosition = bestPositionWithScore(0)
    return bestPosition
  }

  private def scoreFor(board: Board): Int ={

    def scoreForDepth(board: Board, mark: String, depth: Int): Int ={

      def cachingValue(value: Int): Int = {
        if(!scoringHash.contains((board.positions.toString, mark)))
          scoringHash += ((board.positions.toString, mark) -> value)

        return value
      }

      def calculateScore(): Int = {
        val otherMark = Player.otherMark(mark)
        
        if (board.wonBy(mark))
          return highScore - depth
        else if (board.won)
          return -highScore + depth
        else if (board.full || depth > searchDepth)
          return 0 + depth
        else{
          val possibleResultBoards = board.openPositions.map(board.move(otherMark, _))
          val possibleScores = possibleResultBoards.map{
            scoreForDepth(_, otherMark, depth + 1)
          }
          val bestScoreForOpponent = possibleScores.reduceLeft{ _ max _ }

          return -bestScoreForOpponent
        }
      }


      if(scoringHash.contains((board.positions.toString, mark))) {
        return scoringHash((board.positions.toString, mark))
      }
      else {
        return cachingValue(calculateScore())
      }
    }

    scoreForDepth(board, playerMark, 0)
  }

  private def printMoveDecisions(mark: String, board: Board, bestMove: Int) ={
    println("scores for player " + mark + ": " + board.openPositions.map {
      (pos: Int) => scoreFor(board.move(mark, pos)) 
    })

    println("best move: " + bestMove)
  }

}