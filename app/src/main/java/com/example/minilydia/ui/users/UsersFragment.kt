package com.example.minilydia.ui.users

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.example.minilydia.ui.common.interfaces.ActionsOnListUsers
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UsersFragment : Fragment(R.layout.fragment_users), ActionsOnListUsers {

    private val usersViewModel by viewModel<UsersViewModel>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("HomeFragment has been created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        observeUsers()
        usersViewModel.getUsers()
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.users_recycler_view)
        val emptyView = view.findViewById<ViewGroup>(R.id.users_empty_view)

        usersAdapter = UsersAdapter(this)
        recyclerView.apply {
            val lm = LinearLayoutManager(context)
            lm.orientation = LinearLayoutManager.VERTICAL
            layoutManager = lm
            // TODO : uncomment line below
            //layoutManager = LinearLayoutManager(context).apply { orientation = LinearLayoutManager.VERTICAL }
            adapter = usersAdapter
        }

        usersAdapter.addLoadStateListener { loadState ->
            val isEmptyView = loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && usersAdapter.itemCount < 1
            recyclerView.visibility = if (isEmptyView) View.GONE else View.VISIBLE
            emptyView.visibility = if (isEmptyView) View.VISIBLE else View.GONE
        }
    }

    private fun observeUsers() {
        usersViewModel.users.observe(viewLifecycleOwner) { userPagingData ->
            Timber.i("Users value has changed. Submitting changes to adapter")
            usersAdapter.submitData(lifecycle, userPagingData)
        }
    }

    override fun navigateToUserDetail(user: User, profilePicture: ImageView, position: Int) {
        Timber.d("Click on user ${user.getFullName()}")
        // TODO
    }

}