package eu.epfc.pocketmovie

import android.content.Context
import android.os.Handler
import eu.epfc.pocketmovie.data.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class HttpRequestTask(
    private val url : String,
    private val applicationContext : Context): Runnable {

    /**
     * This method will run on a background thread.
     */
    override fun run() {

        // Create http client
        val okHttpClient = OkHttpClient()
        // build a request
        val request = Request.Builder().url(url).build()
        // send the request
        try {
            val response = okHttpClient.newCall(request).execute()
            // extract data from the response
            val responseString : String? = response.body?.string()

            if (responseString != null) {
                // switch to main thread
                val mainHandler = Handler(applicationContext.getMainLooper())
                mainHandler.post {
                    // run on main thread
                    MovieRepository.instance.onReceiveHttpResponse(responseString, true)
                }
            }
        }
        catch (exception : IOException){
            // switch to main thread
            val mainHandler = Handler(applicationContext.getMainLooper())
            mainHandler.post {
                // run on main thread
                MovieRepository.instance.onReceiveHttpResponse(null, false)
            }
        }
    }
}