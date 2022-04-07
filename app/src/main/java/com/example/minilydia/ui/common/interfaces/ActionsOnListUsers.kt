package com.example.minilydia.ui.common.interfaces

import android.widget.ImageView
import com.example.minilydia.domain.model.User

interface ActionsOnListUsers {
    fun navigateToUserDetail(user: User)
}