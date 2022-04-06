package com.example.minilydia.ui.users

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.example.minilydia.ui.common.interfaces.ActionsOnListUsers
import com.facebook.drawee.view.SimpleDraweeView
import timber.log.Timber

class UsersAdapter(private val actionsOnListUsers: ActionsOnListUsers) :
    PagingDataAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        Timber.d("Binding view holder at position $position")
        val user = getItem(position)
        user?.let { holder.bind(user, actionsOnListUsers) }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, actionsOnListUsers: ActionsOnListUsers) {
            itemView.findViewById<TextView>(R.id.user_name).text = user.getFullName()
            itemView.findViewById<TextView>(R.id.user_email).text = user.email
            itemView.findViewById<SimpleDraweeView>(R.id.user_image).setImageURI(user.smallPicture)

            itemView.setOnClickListener {
                Timber.i("User ${user.id} clicked in position $bindingAdapterPosition")
                // TODO
            }
        }
    }
}