package qubit.engineering.movieapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import qubit.engineering.movieapp.api.MovieInterface
import qubit.engineering.movieapp.network.CheckNetwork
import qubit.engineering.movielookup.data.Movie
import java.lang.Exception

class MovieDataSource(
    private val api: MovieInterface,
    private val compositeDisposable: CompositeDisposable
) {


    private val _networkState = MutableLiveData<CheckNetwork>()
    val networkState: LiveData<CheckNetwork>
        get() = _networkState
    private val _downloadedMovieResponse = MutableLiveData<Movie>()

    val downloadedMovieResponse: LiveData<Movie>
        get() = _downloadedMovieResponse

    fun getMovieDetails(name: String, year: String) {

        _networkState.postValue(CheckNetwork.LOADING)

        try {
            compositeDisposable.add(
                api.getRequestedMovie(name, year)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedMovieResponse.postValue(it)
                            _networkState.postValue(CheckNetwork.LOADED)

                        },
                        {
                            _networkState.postValue(CheckNetwork.ERROR)

                        }
                    )


            )


        } catch (e: Exception) {
            Log.e("Error", e.toString())

        }

    }


}