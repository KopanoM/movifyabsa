package qubit.engineering.movieapp.repo

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import qubit.engineering.movieapp.api.MovieInterface
import qubit.engineering.movieapp.network.CheckNetwork
import qubit.engineering.movielookup.data.Movie

class MovieRepo(private val api: MovieInterface) {

    lateinit var movieDataSource: MovieDataSource
    lateinit var _myMovies: MutableList<Movie>
    val movieList: MutableList<Movie>
        get() = _myMovies
    //lateinit var moviesDao: MoviesDao

    fun getRequestedMovie(
        name: String,
        year: String,
        compositeDisposable: CompositeDisposable
    ): LiveData<Movie> {
        _myMovies = arrayListOf()
        movieDataSource = MovieDataSource(api, compositeDisposable)
        movieDataSource.getMovieDetails(name, year)
        //moviesDao =
        return movieDataSource.downloadedMovieResponse


    }

    fun addMovie(movie: Movie) {
        _myMovies.add(movie)

    }

    fun getState(): LiveData<CheckNetwork> {
        return movieDataSource.networkState
    }


}