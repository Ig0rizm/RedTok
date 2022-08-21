package ru.project.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(text: CharSequence) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}