package hr.ferit.karlabinder.hangman

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserViewHolder (val view: View): RecyclerView.ViewHolder(view) {

    private val userName =
        view.findViewById<TextView>(R.id.userName)
    private val userScore =
        view.findViewById<TextView>(R.id.userScore)

    fun bind(
        index: Int,
        user: User
    ) {
        userName.text = user.name
        userScore.text = user.score

    }

}
