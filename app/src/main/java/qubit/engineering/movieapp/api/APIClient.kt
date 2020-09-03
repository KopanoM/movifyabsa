package qubit.engineering.movieapp.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val API_KEY = "bcb1c45" // this is our api key from omdbapi
const val BASE_URL = "https://www.omdbapi.com/"//this is the base url that stays constant //http://www.omdbapi.com/?t=glass&y=2019&apikey=bcb1c45

object APIClient {

    fun getClient(): MovieInterface{
        val requestInterceptor = Interceptor{
                chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)

        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieInterface::class.java)




    }



}