package com.jmzd.ghazal.onlinemafia.repository

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar

class SnackBarMaker {
    object SnackBar {
        fun setSnackBar(view: View, text: String) {
            val snack: Snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            val view: View = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            snack.show()
        }
    }
}