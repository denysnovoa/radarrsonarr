package com.denysnovoa.nzbmanager.radarr.movies.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.denysnovoa.nzbmanager.R
import com.denysnovoa.nzbmanager.common.framework.inflate
import com.denysnovoa.nzbmanager.common.framework.loadUrl
import com.denysnovoa.nzbmanager.radarr.movies.view.model.MovieViewModel
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieItemAdapter(val items: List<MovieViewModel>) : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
        val item = items[position]
        with(item) {
            movie_title.text = title
            movie_id.text = id.toString()

            // val newUrl = URL(imagePoster)
            //val mIcon_val = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            //movie_image.setImageBitmap(mIcon_val)

            movie_image.loadUrl(imagePoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.movie_item))
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}