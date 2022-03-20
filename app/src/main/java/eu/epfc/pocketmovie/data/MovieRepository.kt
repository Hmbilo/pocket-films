package eu.epfc.pocketmovie.data

import android.content.Context
import android.util.Log
import eu.epfc.pocketmovie.HttpRequestTask
import org.json.JSONException
import org.json.JSONObject
import java.lang.ref.WeakReference

class MovieRepository {
    var applicationContext : Context ?= null
    private val urlPopularMovies = "https://api.themoviedb.org/3/movie/popular?page=1&api_key=ea2dcee690e0af8bb04f37aa35b75075"
    private var httpReceiverInitialized = false
    var movies : List<Movie>? = null
    private set

    private var dataUiListener: WeakReference<DataUIListener>? = null

    interface DataUIListener {
        fun updateUI()
    }

    fun onReceiveHttpResponse(response : String?, completed : Boolean){

        val myApplicationContext = this.applicationContext

        if (completed && response != null) {
            // parse Movies from JSON
            movies = MovieRepository.instance.parseToMoviesResponse(response)
        }
        else {
                //TODO : connexion to database
        }

        // update the UI
        val myDataUIListener = dataUiListener
        if (myDataUIListener != null) {
            myDataUIListener.get()?.updateUI()
        }
    }

    fun observe(dataUIListener: DataUIListener) {
        this.dataUiListener = WeakReference(dataUIListener)
    }

    fun fetchArticles() {
        val myApplicationContext = this.applicationContext
        if (movies == null && myApplicationContext != null) {
            // get new articles from the server
            val requestTask = HttpRequestTask(urlPopularMovies, myApplicationContext)
            val requestThread = Thread(requestTask)
            requestThread.start()

        } else {
            val myDataUIListener = dataUiListener
            if (myDataUIListener != null){
                myDataUIListener.get()?.updateUI()
            }
        }
    }

    /**
     *
    val title: String,
    val ReleaseDate: Date,
    val vote: String,
    val backDropsPath: String,
    val posterpath: String,
    val overview: String,
    val checkBox: Boolean
     */
    private fun parseToMoviesResponse(jsonString: String): ArrayList<Movie> {
        val newMovies = ArrayList<Movie>()

        try {
            val jsonObject = JSONObject(jsonString)
            val jsonMovies = jsonObject.getJSONArray("results")

            for (i in 0 until jsonMovies.length()) {
                val jsonMovies = jsonMovies.getJSONObject(i)

                val title = jsonMovies.getString("title")
                val releaseDate = jsonMovies.getString("release_date")
                val vote = jsonMovies.getString("vote_average")
                val posterPath = jsonMovies.getString("poster_path")
                val backdropPath = jsonMovies.getString("backdrop_path")

                val newMovie= Movie(title, releaseDate,vote,backdropPath,posterPath, "")

                newMovies.add(newMovie)
            }

        } catch (e: JSONException) {
            Log.e("MovieRepository", "can't parse json string correctly")
            e.printStackTrace()
        }

        return newMovies
    }

    companion object {

        /**
         * return the unique static instance of the singleton
         * @return an ArticleManager instance
         */
        val instance = MovieRepository()
    }
}