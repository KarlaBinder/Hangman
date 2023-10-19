package hr.ferit.karlabinder.hangman

import kotlin.random.Random


class GameManager {

    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private var curTry = 0
    private val maxTries = 7
    private var picture: Int = R.drawable.hangmanphoto0


    fun generateNextWord(): GameState {
        curTry = 0
        picture = R.drawable.hangmanphoto7
        val randomWord = Random.nextInt(0, GameWords.words.size)
        wordToGuess = GameWords.words[randomWord]
        generateUnderscores(wordToGuess)
        return getGameState()
    }


    private fun generateUnderscores(word: String) {
        val sb = StringBuilder()
        word.forEach { char ->
            if (char == ' ') {
                sb.append(' ')
            } else {
                sb.append("_")
            }
        }
        underscoreWord = sb.toString()
    }

    fun play(letter: Char): GameState {
        val index = mutableListOf<Int>()

        wordToGuess.forEachIndexed{i,char->
            if(char.equals(letter,true)){
                index.add(i)
            }
        }

        var finalUnderscoreWord=""+underscoreWord
        index.forEach{i->
            val sb=StringBuilder(finalUnderscoreWord).also{it.setCharAt(i,letter)}
            finalUnderscoreWord=sb.toString()
        }
        if(index.isEmpty()){
            curTry++
        }
        underscoreWord=finalUnderscoreWord
        return getGameState()
    }


    private fun getPicture(): Int {
        return when (curTry) {
            0 -> R.drawable.hangmanphoto0
            1 -> R.drawable.hangmanphoto1
            2 -> R.drawable.hangmanphoto2
            3 -> R.drawable.hangmanphoto3
            4 -> R.drawable.hangmanphoto4
            5 -> R.drawable.hangmanphoto5
            6 -> R.drawable.hangmanphoto6
            7 -> R.drawable.hangmanphoto7
            else -> R.drawable.hangmanphoto7
        }
    }

    private fun getGameState(): GameState {
        if (underscoreWord.equals(wordToGuess, true)) {
            return GameState.Won(wordToGuess)
        }
        if (curTry == maxTries) {
            return GameState.Lost()
        }
        picture = getPicture()
        return GameState.Running(underscoreWord, picture)

    }
}