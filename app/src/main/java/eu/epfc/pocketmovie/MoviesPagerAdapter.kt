package eu.epfc.pocketmovie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import eu.epfc.pocketmovie.data.Movie

// 1
class MoviesPagerAdapter(fragmentManager: FragmentManager, private val movies: ArrayList<Movie>) : FragmentStateAdapter(fragmentManager,) {
    private val MAX_VALUE = 2
    // 2
    override fun getItem(position: Int): Fragment {
        if(position == 0){
            return RecentMovieFragment.newInstance(movies)
        } else if(position == 1) {
            return RecentMovieFragment.newInstance(movies)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}