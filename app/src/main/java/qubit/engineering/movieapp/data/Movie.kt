package qubit.engineering.movielookup.data



import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
const val CURRENT_MOVIE_ID = 0;

data class Movie(



    @SerializedName("Actors")
    val actors: String="",
    @SerializedName("Awards")
    val awards: String="",
    @SerializedName("BoxOffice")
    val boxOffice: String="",
    @SerializedName("Country")
    val country: String="",
    @SerializedName("DVD")
    val dVD: String="",
    @SerializedName("Director")
    val director: String="",
    @SerializedName("Genre")
    val genre: String="",
    val imdbID: String="",
    val imdbRating: String="",
    val imdbVotes: String="",
    @SerializedName("Language")
    val language: String="",
    @SerializedName("Metascore")
    val metascore: String="",
    @SerializedName("Plot")
    val plot: String="",
    @SerializedName("Poster")
    val poster: String="",
    @SerializedName("Production")
    val production: String="",
    @SerializedName("Rated")
    val rated: String="",
    //@SerializedName("Ratings")
    //val ratings: List<Rating>,
    @SerializedName("Released")
    val released: String="",
    @SerializedName("Response")
    val response: String="",
    @SerializedName("Runtime")
    val runtime: String="",
    @SerializedName("Title")
    val title: String="",
    @SerializedName("Type")
    val type: String="",
    @SerializedName("Website")
    val website: String="",
    @SerializedName("Writer")
    val writer: String="",
    @SerializedName("Year")
    val year: String="",


    )