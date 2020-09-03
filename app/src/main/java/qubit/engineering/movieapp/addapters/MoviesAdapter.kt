package qubit.engineering.movieapp.addapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_movie.view.*
import qubit.engineering.movieapp.R
import qubit.engineering.movielookup.data.Movie

class MovieAdapter(private val list: List<Movie>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Movie> = list


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MovieViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(movies: List<Movie>){
        items = movies
    }

    class MovieViewHolder constructor(
        itemView: View

    ):RecyclerView.ViewHolder(itemView){
        val thumbnail: ImageView = itemView.item_thumbnail
        val title : TextView = itemView.item_title
        val genre : TextView = itemView.item_genre
        val rating_tomatoes: TextView = itemView.item_tomatoes
        val rating_stars : TextView = itemView.item_stars
        val year:  TextView = itemView.item_year

        fun bind(movie: Movie){

            title.setText(movie.title)
            genre.setText(movie.genre)
            rating_tomatoes.setText(movie.imdbRating)
            rating_stars.setText(movie.metascore)
            year.setText(movie.year)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)


            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie.poster)
                .into(thumbnail)

        }

    }


}