package com.example.minilydia.ui.userdetails

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.minilydia.R
import com.example.minilydia.domain.model.User
import com.facebook.drawee.view.SimpleDraweeView
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class UserDetailsFragment : Fragment(R.layout.fragment_user_detail) {

    private val args: UserDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = args.user
        bindUserData(view, user)
    }

    private fun bindUserData(view: View, user: User) {
        view.findViewById<SimpleDraweeView>(R.id.detail_user_image).setImageURI(user.largePicture)
        view.findViewById<TextView>(R.id.detail_user_firstname).text = user.firstName
        view.findViewById<TextView>(R.id.detail_user_surname).text = user.surname
        view.findViewById<TextView>(R.id.detail_user_email).text = user.email
        view.findViewById<TextView>(R.id.detail_user_phone).text = user.phone
        view.findViewById<TextView>(R.id.detail_user_city).text = user.city

        val registeredDate = Date(user.registered * 1000)
        var prettyDate = ""
        try {
            prettyDate = SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).format(registeredDate)
        } catch (e: Exception) {
            Timber.e(e)
        }
        view.findViewById<TextView>(R.id.detail_user_registered).text = prettyDate

    }
}
