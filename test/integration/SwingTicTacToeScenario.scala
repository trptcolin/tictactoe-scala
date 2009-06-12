import org.netbeans.jemmy.operators._
import org.netbeans.jemmy.{TimeoutExpiredException, ClassReference, JemmyProperties}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{BeforeAndAfter, TestFailedException, Spec}
import swing.Frame

class SwingTicTacToeScenario extends Spec with ShouldMatchers with BeforeAndAfter {

  var mainWindow: JFrameOperator = _

  override def beforeEach() {
    new ClassReference("SwingTicTacToe").startApplication()
    mainWindow = new JFrameOperator("Tic-Tac-Toe")
    JemmyProperties.setCurrentTimeout("ComponentOperator.WaitComponentTimeout", 1000)
  }
  override def afterAll() {
    Thread.sleep(200)
    mainWindow.close()
  }

  describe("SwingTicTacToe") {
    it("should play a game: computer vs. computer") {
      val computerComputerButton = new JLabelOperator(mainWindow, "Computer (X) vs. Computer (O)")
      computerComputerButton should not be(null)

      computerComputerButton.clickMouse()

      val playAgainButton = new JLabelOperator(mainWindow, "Play Again")
      playAgainButton should not be(null)

      playAgainButton.clickMouse()
    }

    it("should play a game: human vs. human") {
      val humanHumanButton = new JLabelOperator(mainWindow, "Human (X) vs. Human (O)")
      humanHumanButton should not be(null)

      humanHumanButton.clickMouse()

      var square: JLabelOperator = null

      square = new JLabelOperator(mainWindow, 0)
      square.clickMouse()

      square = new JLabelOperator(mainWindow, 4)
      square.clickMouse()

      square = new JLabelOperator(mainWindow, 2)
      square.clickMouse()

      square = new JLabelOperator(mainWindow, 1)
      square.clickMouse()

      square = new JLabelOperator(mainWindow, 3)
      square.clickMouse()

      var playAgainButton: JLabelOperator = null

      intercept[TimeoutExpiredException] {
        playAgainButton = new JLabelOperator(mainWindow, "Play Again")
        playAgainButton should be(null)
      }

      square = new JLabelOperator(mainWindow, 7)
      square.clickMouse()

      playAgainButton = new JLabelOperator(mainWindow, "Play Again")
      playAgainButton should not be(null)

      playAgainButton.clickMouse()
    }
  }
}