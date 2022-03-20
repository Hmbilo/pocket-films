package eu.epfc.pocketmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import eu.epfc.pocketmovie.data.Movie
import eu.epfc.pocketmovie.data.MovieRepository

/**
 * A simple [Fragment] subclass.
 * Use the [RecentMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecentMovieFragment : Fragment(), MoviesAdapter.ListItemClickListener, MovieRepository.DataUIListener {
    private lateinit var movieList: List<Movie>
    private lateinit var recyclerView: RecyclerView
    private var firstStart = true
    private lateinit var moviesAdapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MovieRepository.instance.applicationContext = this.applica
        val recyclerView : RecyclerView = findViewById(R.id.recent_recycler_view)

        // set the adapter of the RecyclerView
        moviesAdapter = MoviesAdapter()
        recyclerView.adapter = moviesAdapter

        // set the layoutManager of the recyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    override fun onStart() {
        super.onStart()

        if (firstStart) {
            // if device is tablet
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                // send the first planet to the detail fragment

                val fragmentActivity = activity
                if (planetList.isNotEmpty() && fragmentActivity != null) {

                    val detailFragment =
                        fragmentActivity.supportFragmentManager.findFragmentById(R.id.detail_fragment) as DetailFragment?
                    detailFragment?.planet = planetList[0]
                }
            }
        }
        firstStart = false
    }

    override fun onListItemClick(clickedItemIndex: Int) {
        TODO("Not yet implemented")
    }

    override fun updateUI() {
        TODO("Not yet implemented")
    }
}