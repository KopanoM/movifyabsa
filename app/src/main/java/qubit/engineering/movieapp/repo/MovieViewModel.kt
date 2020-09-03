package qubit.engineering.movieapp.repo

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import qubit.engineering.movieapp.network.CheckNetwork
import qubit.engineering.movielookup.data.Movie

class MovieViewModel(private val movieRepo: MovieRepo, name: String, year: String) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()


    val movieDets: LiveData<Movie> by lazy {
        movieRepo.getRequestedMovie(name, year, compositeDisposable)
    }

    val networkState: LiveData<CheckNetwork> by lazy {
        movieRepo.getState()
    }

    fun addUser(movie: Movie) {
        movieRepo.addMovie(movie)
    }

    fun getUsers(): List<Movie> {

        return movieRepo.movieList
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose() // avoid memory leaks
    }


}