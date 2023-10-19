package hr.ferit.karlabinder.hangman

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Standings : Fragment(R.layout.fragment_standings)  {
    private val db = Firebase.firestore
    private lateinit var recyclerAdapter: UserRecyclerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_standings, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.userList)
        val homeIcon2=view.findViewById<ImageButton>(R.id.home_icon2)

        homeIcon2.setOnClickListener {
            val homePage = Homepage()
            val fragmentTransaction: FragmentTransaction? =
                activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, homePage)
            fragmentTransaction?.commit()
        }

        db.collection("HangmanUsers")
            .get()
            .addOnSuccessListener { result ->
                val userList = ArrayList<User>()
                for (data in result.documents) {
                    val user = data.toObject(User::class.java)
                    if (user != null) {
                        user.id = data.id
                        userList.add(user)
                    }
                }
                recyclerAdapter = UserRecyclerAdapter(userList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this.context)
                    adapter = recyclerAdapter
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Standings", "Error getting documents.",
                    exception)
            }


        return view
    }
}


