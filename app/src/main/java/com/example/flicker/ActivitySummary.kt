package com.example.flicker

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.flicker.models.Movie

class ActivitySummary : AppCompatActivity(R.layout.activity_summary) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedMovie:Movie=intent.getSerializableExtra("selectedMovie") as Movie
        val ivMovie: ImageView = findViewById(R.id.ivMovieSummary)
        val tvTitle: TextView = findViewById(R.id.tvMovieName)
        val tvReleaseDate = findViewById<TextView>(R.id.tvReleaseDate)
        val tvMovieSummary = findViewById<TextView>(R.id.tvMovieSummaryDescription)
        val rbStars = findViewById<RatingBar>(R.id.rbStars)

        Glide.with(this)
            .load(selectedMovie.backdropPath)
            .transform(RoundedCorners(30))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(ivMovie)
        tvTitle.text = selectedMovie.originalTitle
        tvMovieSummary.text=selectedMovie.overview
        rbStars.numStars=selectedMovie.rating.toInt()
        tvReleaseDate.text="Release date: ${selectedMovie.releaseDate}"
    }
}
