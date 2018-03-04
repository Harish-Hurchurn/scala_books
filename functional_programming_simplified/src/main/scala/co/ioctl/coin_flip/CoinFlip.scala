package co.ioctl.coin_flip

import CoinFlipUtils._
import scala.annotation.tailrec
import scala.util.Random

case class GameHistory(history: List[GameState])

case class GameState(numFlips: Int, numCorrect: Int)

object CoinFlip extends App {

  val h = GameHistory(List.empty)
  val r = Random
  val s = GameState(0, 0)
  mainLoop(h, s, r)

  @tailrec
  def mainLoop(gameHistory: GameHistory, gameState: GameState, random: Random) {

    showPrompt()
    val userInput = getUserInput()

    // handle the result
    userInput match {
      case "H" | "T" =>
        val coinTossResult = tossCoin(random)
        val newNumFlips = gameState.numFlips + 1
        if (userInput == coinTossResult) {

          val newNumCorrect = gameState.numCorrect + 1
          val newGameState = gameState.copy(numFlips = newNumFlips, numCorrect = newNumCorrect)

          val oldHistory = gameState :: gameHistory.history
          val updatedGameHistory = gameHistory.copy(history = oldHistory)

          printGameState(printableFlipResult(coinTossResult), newGameState)

          mainLoop(updatedGameHistory, newGameState, random)
        } else {
          val newGameState = gameState.copy(numFlips = newNumFlips)
          printGameState(printableFlipResult(coinTossResult), newGameState)

          val oldHistory = gameState :: gameHistory.history
          val updatedGameHistory = gameHistory.copy(history = oldHistory)

          mainLoop(updatedGameHistory, newGameState, random)
        }

      case "N" =>

        val history = gameHistory.copy(history = List.empty)
        val state = gameState.copy(numFlips = 0, numCorrect = 0)
        mainLoop(history, state, random)

      case _ =>
        printGameOver()
        printGameState(gameState)
        printGameHistory(gameHistory)
    }
  }
}
