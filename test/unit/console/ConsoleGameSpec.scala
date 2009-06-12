package trptcolin.tictactoescala.console

import basegame.{MockPlayer, MockBoard}
import java.io.{CharArrayReader, ByteArrayOutputStream}
import players._

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

class ConsoleGameSpec extends Spec with ShouldMatchers {
  describe("ConsoleGame") {
    it("should refresh board state") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new ConsoleGame(board, Array(player1, player2))

      val stdout = Console.out
      val out = new ByteArrayOutputStream()
      Console.setOut(out)
      game.refreshBoardState(board)
      Console.setOut(stdout)

      val expectedOut = ConsoleGame.stringify(board) + "\n"

      out.toString should equal(expectedOut)
    }

    it("should pick the game type with 2 computer players") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new ConsoleGame(board, Array(player1, player2))

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('1'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)
      game.start
      
      Console.setIn(realIn)
      Console.setOut(realOut)
      
      game.players(0).isInstanceOf[ComputerPlayer] should equal(true)
      game.players(1).isInstanceOf[ComputerPlayer] should equal(true)
    }

    it("should pick the game type with computer X and human O") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new ConsoleGame(board, Array(player1, player2))

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('2'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)
      game.start

      Console.setIn(realIn)
      Console.setOut(realOut)

      game.players(0).isInstanceOf[ComputerPlayer] should equal(true)
      game.players(1).isInstanceOf[HumanConsolePlayer] should equal(true)
    }

    it("should choose computer players, play again, and choose humans") {
      val board = new MockBoard()
      val player1 = new MockPlayer("X")
      val player2 = new MockPlayer("O")
      val game = new ConsoleGame(board, Array(player1, player2))

      val realIn = Console.in
      val realOut = Console.out
      val in = new CharArrayReader(Array('1', '\n', '\n', '4'))
      val out = new ByteArrayOutputStream()

      Console.setIn(in)
      Console.setOut(out)
      game.start

      Console.setIn(realIn)
      Console.setOut(realOut)
      
      game.players(0).isInstanceOf[HumanConsolePlayer] should equal(true)
      game.players(1).isInstanceOf[HumanConsolePlayer] should equal(true)
    }

    it("should display an empty board") {
      val display = ConsoleGame.stringify(new MockBoard())

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
      val display = ConsoleGame.stringify(board)

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