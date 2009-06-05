import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class ConsoleDisplaySpec extends Spec with ShouldMatchers {
  describe("ConsoleDisplay") {
    it("should display an empty board") {
      val display = ConsoleDisplay.toString(new MockBoard())

      val expectedDisplay = """ |   |   |   
                                |-----------
                                |   |   |   
                                |-----------
                                |   |   |   
                                |""".stripMargin

      display should equal (expectedDisplay)
    }

    it("should display a full board") {
      val board = new MockBoard().move("X", 0).
                                  move("O", 1).
                                  move("X", 2).
                                  move("O", 3).
                                  move("X", 4).
                                  move("O", 5).
                                  move("X", 6).
                                  move("O", 7).
                                  move("X", 8)
      val display = ConsoleDisplay.toString(board)

      val expectedDisplay = """ | X | O | X 
                                |-----------
                                | O | X | O 
                                |-----------
                                | X | O | X 
                                |""".stripMargin

      display should equal (expectedDisplay)
    }
  }
}