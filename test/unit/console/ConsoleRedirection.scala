package trptcolin.tictactoescala.console

import java.io.{Reader, OutputStream, ByteArrayOutputStream}

trait ConsoleRedirection
{
  def redirectingOut(action: () => Unit): OutputStream =
  {
    val stdout = Console.out
    val out = new ByteArrayOutputStream()
    Console.setOut(out)

    action()

    Console.setOut(stdout)
    return out
  }

  def redirectingConsole(in: Reader, action: () => Unit): OutputStream =
  {
    val realIn = Console.in
    val realOut = Console.out
    val out = new ByteArrayOutputStream()

    Console.setIn(in)
    Console.setOut(out)

    action()

    Console.setIn(realIn)
    Console.setOut(realOut)

    return out
  }
}