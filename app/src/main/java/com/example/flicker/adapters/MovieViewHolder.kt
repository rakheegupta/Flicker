package com.example.flicker.adapters

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.flicker.R
import com.example.flicker.models.Movie

// Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
class MovieViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView){

    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    val mIvMovieThumbnail: ImageView = itemView.findViewById(R.id.ivMovie)
    val mTvTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
    val mTvOverView : TextView = itemView.findViewById(R.id.tvMovieDescription)

}

//or this way

class MovieViewHolder1 : RecyclerView.ViewHolder {

    val mIvMovieThumbnail: ImageView
    val mTvTitle: TextView
    val mTvOverView: TextView

    constructor(itemView: View) :super(itemView) {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        mIvMovieThumbnail = itemView.findViewById(R.id.ivMovie)
        mTvTitle = itemView.findViewById(R.id.tvMovieTitle)
        mTvOverView = itemView.findViewById(R.id.tvMovieDescription)
    }
}
