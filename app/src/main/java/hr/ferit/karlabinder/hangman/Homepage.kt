package hr.ferit.karlabinder.hangman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import kotlin.system.exitProcess


class Homepage : Fragment(R.layout.fragment_homepage) {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)
        val playButton = view.findViewById<Button>(R.id.play_btn)
        val standingsButton = view.findViewById<Button>(R.id.standings_btn)
        val quitButton = view.findViewById<Button>(R.id.quit_btn)

        playButton.setOnClickListener {
            val gameplay=Gameplay()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, gameplay)
            fragmentTransaction?.commit()
        }

        standingsButton.setOnClickListener {
            val standings = Standings()

            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, standings)
            fragmentTransaction?.commit()
        }

        quitButton.setOnClickListener{
            activity?.finish()
            exitProcess(0)}

        return view

    }
}