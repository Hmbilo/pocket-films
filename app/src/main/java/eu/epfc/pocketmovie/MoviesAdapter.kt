package eu.epfc.pocketmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.epfc.pocketmovie.data.Movie

class MoviesAdapter(listItemClickListener: ListItemClickListener) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    /**
     * Interface definition
     */
    interface ListItemClickListener {
        fun onListItemClick(clickedItemIndex: Int)
    }

    // listener that will be called when an item is clicked
    val listItemClickListener : ListItemClickListener = listItemClickListener

    var movieData : List<Movie>? = null
        set(movieList) {
            field = movieList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        // get a layoutInflater from the context attached to the parent view
        val layoutInflater = LayoutInflater.from(parent.context)

        // inflate the layout item_planet in a view
        val planetView = layoutInflater.inflate(R.layout.item_movie, parent, false)

        // create a new ViewHolder with the view planetView
        return MovieViewHolder(planetView)
    }

    override fun getItemCount(): Int {

        val myPlanetData = movieData
        if (myPlanetData != null) {
            return myPlanetData.size
        }
        else
        {
            return 0
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val myMovieData = movieData
        if (myMovieData != null) {

            // get the corresponding planet data (based on the parameter position)
            val article = myMovieData[position]

            // get the TextView of the item
            val itemViewGroup = holder.itemView as ViewGroup
        /*
            val titleTextView : TextView = itemViewGroup.findViewById(R.id.text_item_title)
            titleTextView.text = article.title

            val abstractTextView : TextView = itemViewGroup.findViewById(R.id.text_item_abstract)
            abstractTextView.text = article.articleAbstract

            val imageView : ImageView = itemViewGroup.findViewById(R.id.article_image)
        */
            /****Add Picasso***/
            //Picasso.get().load(article.imageUrl).into(imageView)
        }
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        init{
            // define the object to call when the view will be clicked
            itemView.setOnClickListener(this)
        }

        /**
         * Callback method called when the view is clicked
         */
        override fun onClick(view: View?) {

            val clickedPosition = this.adapterPosition
            this@MoviesAdapter.listItemClickListener.onListItemClick(clickedPosition)
        }

    }
}