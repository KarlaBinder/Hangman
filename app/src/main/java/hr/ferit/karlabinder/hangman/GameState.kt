package hr.ferit.karlabinder.hangman

sealed class GameState {
    class Running(val underscoreWord:String,
                   val picture:Int ):GameState()

    class Lost() : GameState()
    class Won(val guessWord: String):GameState()
}