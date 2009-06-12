package trptcolin.tictactoescala.players

import basegame.MockBoard
import java.io.{OutputStream, ByteArrayOutputStream, CharArrayReader}
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class HumanConsolePlayerSpec extends Spec with ShouldMatchers {

  describe("HumanConsolePlayer") {
    it("should make a move") {
      val player = new HumanConsolePlayer("X")

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('1'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)
      val moveMade = player.move(new MockBoard())
      Console.setIn(realIn)
      Console.setOut(realOut)

      moveMade should equal (1)
    }
    
    it("should handle bad input") {
      val player = new HumanConsolePlayer("X")

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('F'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)
      
      val moveMade = player.move(new MockBoard())

      Console.setIn(realIn)
      Console.setOut(realOut)

      out.toString.indexOf("problem") should not equal (-1)
      moveMade should equal (-1)
    }
    
    it("should handle integers outside the required range") {
      val player = new HumanConsolePlayer("X")

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('1','0'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)

      val moveMade = player.move(new MockBoard())

      Console.setIn(realIn)
      Console.setOut(realOut)

      out.toString.indexOf("problem") should not equal (-1)
      moveMade should equal (-1)
    }
  }
}