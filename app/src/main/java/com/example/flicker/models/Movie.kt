package com.example.flicker.models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.collections.ArrayList

class Movie(movieObject: JSONObject) {

    val posterPath: String = "https://image.tmdb.org/t/p/w500${movieObject.getString("poster_path")}"

    val originalTitle: String = movieObject.getString("original_title")
    val overview: String = movieObject.getString("overview")
    val backdropPath: String =
        String.format(
            "https://image.tmdb.org/t/p/w500%s",
            movieObject.getString("backdrop_path")
        )
    val rating: String = movieObject.getString("vote_average")

//static function
    companion object{
        // ArrayList<Movie>? "?" means returned item can be null
        fun fromJSONArray(array: JSONArray): ArrayList<Movie>? {
            var movieArrayList: ArrayList<Movie> =
                ArrayList<Movie>(array.length())
           try {
               for (i in 0 until array.length()) {
                   val result: Movie =
                       Movie(array.getJSONObject(i))
                   movieArrayList.add(result)
               }
           } catch(e: JSONException){
               e.printStackTrace()
           }
        return movieArrayList
        }
    }
}
/*
package function
use it as
    import com.example.flicker.models.MovieKt;
    MovieKt.fromJSONArray(arraylistOfMovies)
 */
fun fromJSONArray1(array: JSONArray): ArrayList<Movie>? {
    val movieList: ArrayList<Movie> =
        ArrayList<Movie>(array.length())
    try {
        for (i in 0 until array.length()) {
            val result: Movie =
                Movie(array.getJSONObject(i))
            movieList.add(result)
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return movieList
}