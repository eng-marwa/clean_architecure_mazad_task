package com.marwa.mazadattask.util

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(message: String?) {
    activity?.let {
        Toast.makeText(it, message, Toast.LENGTH_LONG).show()
    }
}