package com.example.flicker.adapters

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.flicker.ActivitySummary
import com.example.flicker.R
import com.example.flicker.models.Movie

class RecycledListAdapter(val mContext:Context,val mMovies : ArrayList<Movie>) :
    RecyclerView.Adapter<RecycledListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater:LayoutInflater = LayoutInflater.from(parent.context)
        val movieView: View =
            inflater.inflate(R.layout.movie_list_item,parent,false)
        val viewHolder:MovieViewHolder = MovieViewHolder(movieView)
        return viewHolder
    }

    override fun onBindViewHolder(holder:MovieViewHolder, position: Int) {
        //prepare a movie item of the list
        val movie:Movie = mMovies.get(position)
        holder.mIvMovieThumbnail.setImageResource(0)
        val orientation:Int = mContext.resources.configuration.orientation
        var imageUri:String = movie.posterPath
        if (orientation == Configuration.ORIENTATION_LANDSCAPE)
            imageUri = movie.backdropPath

        holder.mTvTitle.setText(movie.originalTitle)
        holder.mTvOverView.setText(movie.overview)
        Glide.with(mContext)
            .load(imageUri)
            .override(mContext.resources.getDimension(R.dimen.image_width).toInt(),
                      mContext.resources.getDimension(R.dimen.image_height).toInt())
            .placeholder(R.drawable.ic_launcher_foreground)
            .transform(RoundedCorners(30))
            .into(holder.mIvMovieThumbnail)
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }


    inner class MovieViewHolder(val mItemView: View) : RecyclerView.ViewHolder(mItemView), View.OnClickListener{
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        val mIvMovieThumbnail: ImageView = mItemView.findViewById(R.id.ivMovie)
        val mTvTitle: TextView = mItemView.findViewById(R.id.tvMovieTitle)
        val mTvOverView : TextView = mItemView.findViewById(R.id.tvMovieDescription)
        init{
            mItemView.setOnClickListener(this)
        }


        override fun onClick(v: View?) {
            val position :Int = adapterPosition
            if(position!=RecyclerView.NO_POSITION){
                val selectedMovie :Movie = mMovies.get(position)
                val intent :Intent= Intent(mContext,ActivitySummary::class.java)
                intent.putExtra("selectedMovie",selectedMovie)
                mContext.startActivity(intent)
            }
        }
    }

/*
init{
            mItemView.setOnClickListener { View.OnClickListener {
                fun onClick(v:View){
                    val position :Int = adapterPosition
                    if(position!=RecyclerView.NO_POSITION)
                        onClickListener.onItemClick(mItemView,position)
                }
            } }
        }
 */
}