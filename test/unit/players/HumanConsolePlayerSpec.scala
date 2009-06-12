package trptcolin.tictactoescala.players

import basegame.MockBoard
import console.ConsoleRedirection
import java.io.{OutputStream, ByteArrayOutputStream, CharArrayReader}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class HumanConsolePlayerSpec extends Spec with ShouldMatchers with ConsoleRedirection
{

  describe("HumanConsolePlayer")
  {
    it("should make a move")
    {
      val player = new HumanConsolePlayer("X")

      var moveMade = -1
      redirectingConsole(new CharArrayReader(Array('1')), {
        () => moveMade = player.move(new MockBoard())
      })

      moveMade should equal (1)
    }
    
    it("should handle bad input")
    {
      val player = new HumanConsolePlayer("X")

      var moveMade = 100
      val out = redirectingConsole(new CharArrayReader(Array('F')), {
        () => moveMade = player.move(new MockBoard())
      })

      out.toString.indexOf("problem") should not equal (-1)
      moveMade should equal (-1)
    }
    
    it("should handle integers outside the required range")
    {
      val player = new HumanConsolePlayer("X")
      var moveMade = 100

      val out = redirectingConsole(new CharArrayReader(Array('1','0')), {
        () => moveMade = player.move(new MockBoard())
      })

      out.toString.indexOf("problem") should not equal (-1)
      moveMade should equal (-1)
    }
  }
}