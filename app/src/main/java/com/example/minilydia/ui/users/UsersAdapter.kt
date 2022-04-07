package com.example.minilydia.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.example.minilydia.ui.common.interfaces.ActionsOnListUsers
import com.facebook.drawee.view.SimpleDraweeView
import timber.log.Timber

class UsersAdapter(private val actionsOnListUsers: ActionsOnListUsers) :
    PagedListAdapter<User, UsersAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        Timber.d("Creating view holder for UsersAdapter")
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(user, actionsOnListUsers) }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, actionsOnListUsers: ActionsOnListUsers) {
            itemView.findViewById<TextView>(R.id.user_name).text = user.getFullName()
            itemView.findViewById<TextView>(R.id.user_email).text = user.email
            itemView.findViewById<SimpleDraweeView>(R.id.user_image).setImageURI(user.smallPicture)

            itemView.setOnClickListener {
                actionsOnListUsers.navigateToUserDetail(user)
            }
        }
    }
}