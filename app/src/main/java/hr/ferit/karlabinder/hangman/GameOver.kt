package hr.ferit.karlabinder.hangman


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class GameOver : Fragment() {

    private lateinit var saveButton: Button
    private lateinit var finalScoreNumber: TextView
    private lateinit var tryAgainButton: Button
    private lateinit var usernameText: EditText

    private var db=Firebase.firestore

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_gameover, container, false)
        saveButton=view.findViewById(R.id.saveButton)
        finalScoreNumber=view.findViewById(R.id.finalScoreNumber)
        tryAgainButton=view.findViewById(R.id.tryAgain)
        usernameText=view.findViewById(R.id.username)

        finalScoreNumber.text=arguments?.getString("score").toString()
        saveButton.setOnClickListener{
            val username=usernameText.text.toString()
            val score=finalScoreNumber.text.toString()
            val user = hashMapOf(
                "name" to username,
                "score" to score
            )

            db.collection("HangmanUsers")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

        tryAgainButton.setOnClickListener{
            val gameplay=Gameplay()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, gameplay)
            fragmentTransaction?.commit()
        }
        return view
    }


}