package hr.ferit.karlabinder.hangman

import android.annotation.SuppressLint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.FragmentTransaction
import hr.ferit.karlabinder.hangman.R.id.hangmanImage



class Gameplay : Fragment(R.layout.fragment_gameplay) {

    private val gameManager=GameManager()

    private lateinit var homeIcon: ImageButton
    private lateinit var scoreNumber:TextView
    private lateinit var word: TextView
    private lateinit var photo: ImageView
    private lateinit var nextButton: Button
    private lateinit var lettersLayout: ConstraintLayout

    private var score:Int=0

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gameplay, container, false)
        homeIcon = view.findViewById(R.id.homeIcon)
        word = view.findViewById(R.id.underscoreWord)
        photo = view.findViewById(hangmanImage)
        nextButton = view.findViewById(R.id.next_btn)
        lettersLayout = view.findViewById(R.id.lettersLayout)
        scoreNumber=view.findViewById(R.id.scoreNumber)

        homeIcon.setOnClickListener {
            val homepage=Homepage()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, homepage)
            fragmentTransaction?.commit()
        }

        nextButton.setOnClickListener {
            generateNextWord()
        }

        val gameState = gameManager.generateNextWord()
        updateUI(gameState)

        lettersLayout.children.forEach { letterView ->
            if (letterView is TextView) {
                letterView.setOnClickListener {
                    val gamestate = gameManager.play((letterView).text[0])
                    updateUI(gamestate)
                    letterView.visibility = View.GONE
                }
            }
        }
        return view

    }
    private fun generateNextWord(){
        val gameState=gameManager.generateNextWord()
        lettersLayout.visibility=View.VISIBLE
        lettersLayout.children.forEach{letterView->letterView.visibility=View.VISIBLE}
        updateUI(gameState)
    }

    private fun updateUI(gameState: GameState){
        when(gameState){
            is GameState.Lost->showGameLost()
            is GameState.Running->{
                word.text=gameState.underscoreWord

                photo.setImageDrawable(this.context?.let { ContextCompat.getDrawable(it,gameState.picture) })
            }
            is GameState.Won->showGameWon(gameState.guessWord)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showGameLost(){
        val gameover=GameOver()
        val bundle=Bundle()
        bundle.putString("score",scoreNumber.text.toString())
        gameover.arguments=bundle

        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, gameover)
        fragmentTransaction?.commit()


    }

    @SuppressLint("SetTextI18n")
    private fun showGameWon(guessWord:String){
        word.text=guessWord
        scoreNumber.text=scoreTracker().toString()
        lettersLayout.visibility=View.GONE
    }


    private fun scoreTracker():Int{
            score++
        return score
    }


}


