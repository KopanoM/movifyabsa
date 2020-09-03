package qubit.engineering.movieapp.api

import io.reactivex.Single
import qubit.engineering.movielookup.data.Movie

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {
    @GET(".")
    fun getRequestedMovie(
        @Query("t") name: String,
        @Query("y") year: String
    ): Single<Movie>





}