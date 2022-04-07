package com.example.minilydia.ui.users

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.example.minilydia.ui.common.interfaces.ActionsOnListUsers
import com.example.minilydia.ui.common.showLong
import com.example.minilydia.ui.common.utils.Resource
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
        observeUsers(view)
        usersViewModel.getUsers()
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.users_recycler_view)
        val emptyView = view.findViewById<ViewGroup>(R.id.users_empty_view)

        usersAdapter = UsersAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = usersAdapter
        }

        // TODO : Uncomment this line when using Paging 3
        /*usersAdapter.addLoadStateListener { loadState ->
            val isEmptyView = loadState.source.refresh is LoadState.NotLoading
                    && loadState.append.endOfPaginationReached
                    && usersAdapter.itemCount < 1
            recyclerView.visibility = if (isEmptyView) View.GONE else View.VISIBLE
            emptyView.visibility = if (isEmptyView) View.VISIBLE else View.GONE
        }*/
    }

    private fun observeUsers(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.users_recycler_view)
        val emptyView = view.findViewById<ViewGroup>(R.id.users_empty_view)
        val loadingView = view.findViewById<View>(R.id.loading_view)

        usersViewModel.users.observe(viewLifecycleOwner) { userPagedData ->
            Timber.i("Users value has changed. Submitting changes to adapter")

            when (userPagedData) {
                is Resource.Error -> {
                    showLong("Error getting data : ${userPagedData.error}")
                    loadingView.visibility = View.GONE
                }
                is Resource.Success -> {
                    val isEmptyView = userPagedData.data.size == 0
                    recyclerView.visibility = if (isEmptyView) View.GONE else View.VISIBLE
                    emptyView.visibility = if (isEmptyView) View.VISIBLE else View.GONE
                    loadingView.visibility = View.GONE
                    usersAdapter.submitList(userPagedData.data)

                    // TODO : Use this with Paging 3 :
                    //usersAdapter.submitData(lifecycle, userPagingData)
                }
                is Resource.Loading -> {
                    loadingView.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun navigateToUserDetail(user: User) {
        Timber.d("Click on user ${user.getFullName()}")
        val direction = UsersFragmentDirections.displayDetails(user)
        findNavController().navigate(direction)
    }

}