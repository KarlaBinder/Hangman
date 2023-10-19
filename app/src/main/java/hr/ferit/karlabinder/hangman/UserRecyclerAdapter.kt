package hr.ferit.karlabinder.hangman

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



class UserRecyclerAdapter(
    private val items: ArrayList<User>
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("ResourceType")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content,
                parent, false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> {
                holder.bind(position, items[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

}

