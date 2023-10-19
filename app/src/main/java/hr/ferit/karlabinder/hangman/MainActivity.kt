package hr.ferit.karlabinder.hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homepageFragment= Homepage()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fragmentContainerView,homepageFragment)
            commit()
        }


    }
}