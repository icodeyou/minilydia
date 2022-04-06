package com.example.minilydia.ui.users

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.example.minilydia.ui.common.interfaces.ActionsOnListUsers
import kotlinx.coroutines.launch
import timber.log.Timber

class UsersFragment : Fragment(R.layout.fragment_users), ActionsOnListUsers {

    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("HomeFragment has been created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        adapter = UsersAdapter(this)
        view.findViewById<RecyclerView>(R.id.users_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }

    }

    override fun navigateToUserDetail(user: User, profilePicture: ImageView, position: Int) {
        Timber.d("Click on user ${user.getFullName()}")
        // TODO
    }

}