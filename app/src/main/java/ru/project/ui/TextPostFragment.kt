package ru.project.ui

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.project.R
import ru.project.databinding.FragmantPostTextBinding

class TextPostFragment : Fragment(R.layout.fragmant_post_text) {

    private val binding by viewBinding(FragmantPostTextBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getString("data")

        binding.data.text = Html.fromHtml(data)
    }
}