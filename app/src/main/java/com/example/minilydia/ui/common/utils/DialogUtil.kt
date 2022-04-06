package com.example.minilydia.ui.common.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface

class DialogUtil {
    companion object {
        fun showSimpleDialog(
            activity: Activity,
            title: String?,
            message: String,
            okButtonTitle: String?,
            okButtonAction: (dialog: DialogInterface) -> Unit,
            cancelButtonTitle: String?,
            cancelButtonAction: (dialog: DialogInterface) -> Unit
        ): AlertDialog {

            val builder: AlertDialog.Builder = activity.let {
                AlertDialog.Builder(it)
            }

            builder.setTitle(title)
                .setMessage(message)

            builder.setPositiveButton(okButtonTitle) { dialog,_ -> okButtonAction(dialog) }
            builder.setNegativeButton(cancelButtonTitle) { dialog,_ -> cancelButtonAction(dialog) }

            val dialog = builder.create()
            dialog.show()
            return dialog
        }
    }
}
