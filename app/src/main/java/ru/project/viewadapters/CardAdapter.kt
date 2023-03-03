package ru.project.viewadapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.recyclerview.widget.RecyclerView
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import ru.project.app.GlideApp
import ru.project.data.models.Post
import ru.project.databinding.FragmentLoadingBinding
import ru.project.databinding.FragmentPostBinding

class CardAdapter : RecyclerView.Adapter<CardAdapter.CustomViewHolder>() {
    var postList: List<Post?> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = postList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == 200) {
            val binding = FragmentPostBinding.inflate(inflater, parent, false)
            return ContentViewHolder(binding)
        }
        else {
            val binding = FragmentLoadingBinding.inflate(inflater, parent, false)
            return LoadingViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        if (holder is ContentViewHolder) {
            val data = postList[position]

            if (data != null)
            with(holder.binding) {
                title.text = data.title
                subreddit.text = data.subreddit
                GlideApp.with(icon.context).load(data.iconUrl).into(icon)

                if (URLUtil.isValidUrl(data.data) and ((data.data.takeLast(4) == ".jpg") or (data.data.takeLast(4) == ".png"))) {
                    GlideApp.with(image.context).load(data.data).into(image)
                    image.visibility = View.VISIBLE
                    textData.text = ""
                }
                else {
                    textData.text = convertHtmlToText(data.data)
                    image.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (postList[position] != null) {
            return 200
        }
        else {
            return 404
        }
    }

    private fun convertHtmlToText(html: String): String {
        val doc: Document = Jsoup.parse(html)
        return Jsoup.parse(doc.text()).text()
    }

    open class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    class ContentViewHolder(val binding: FragmentPostBinding): CustomViewHolder(binding.root)

    class LoadingViewHolder(binding: FragmentLoadingBinding): CustomViewHolder(binding.root)
}