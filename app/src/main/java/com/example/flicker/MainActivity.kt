package com.example.flicker

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flicker.adapters.RecycledListAdapter
import com.example.flicker.models.Movie
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //https://api.themoviedb.org/3/movie/now_playing?api_key=69694a19e98df7f5c79b13285d536102
    val urlTrending = "https://api.themoviedb.org/3/movie/now_playing"
    val apiKey = "69694a19e98df7f5c79b13285d536102"

    var movies :ArrayList<Movie> = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client: AsyncHttpClient =AsyncHttpClient()
        val params: RequestParams = RequestParams("api_key", apiKey)

        val rvItems : RecyclerView = findViewById(R.id.rvMoviesList)
        //add recycledlistadapter now
        val recycledMovieListAdapter : RecycledListAdapter = RecycledListAdapter(this,movies)
        rvItems.adapter= recycledMovieListAdapter
        rvItems.layoutManager = LinearLayoutManager(this)

        val movieResponseHandler: JsonHttpResponseHandler = object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONObject) {
                super.onSuccess(statusCode, headers, response)
                println(response.toString())
                var movieJsonResults: JSONArray
                try {
                    movieJsonResults = response.getJSONArray("results")
                    Movie.fromJSONArray(movieJsonResults)?.let {movies.addAll(it)}
                    recycledMovieListAdapter.notifyDataSetChanged()
                    println(movies.toString())
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                responseString: String,
                throwable: Throwable
            ) {
                super.onFailure(statusCode, headers, responseString, throwable)
                Toast.makeText(baseContext, responseString, Toast.LENGTH_LONG).show()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<Header>,
                throwable: Throwable,
                errorResponse: JSONObject
            ) {
                super.onFailure(statusCode, headers, throwable, errorResponse)
                println(errorResponse.toString())
            }
        }
        client.get(urlTrending, params, movieResponseHandler)

    }
}