package qubit.engineering.movieapp.data

import qubit.engineering.movielookup.data.Movie

class DataSource {

    companion object{
        fun createDataset():ArrayList<Movie>{
            val list = ArrayList<Movie>()
            list.add(Movie("","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","https://m.media-amazon.com/images/M/MV5BMTY1OTA2MjI5OV5BMl5BanBnXkFtZTgwNzkxMjU4NjM@._V1_SX300.jpg","helllo","hello","hello","hello","hello","hello","hello","hello","hello","hello"))
            list.add(Movie("","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","https://m.media-amazon.com/images/M/MV5BMTY1OTA2MjI5OV5BMl5BanBnXkFtZTgwNzkxMjU4NjM@._V1_SX300.jpg","helllo","hello","hello","hello","hello","hello","hello","hello","hello","hello"))
            list.add(Movie("","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","https://m.media-amazon.com/images/M/MV5BMTY1OTA2MjI5OV5BMl5BanBnXkFtZTgwNzkxMjU4NjM@._V1_SX300.jpg","helllo","hello","hello","hello","hello","hello","hello","hello","hello","hello"))
            list.add(Movie("","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","hello","https://m.media-amazon.com/images/M/MV5BMTY1OTA2MjI5OV5BMl5BanBnXkFtZTgwNzkxMjU4NjM@._V1_SX300.jpg","helllo","hello","hello","hello","hello","hello","hello","hello","hello","hello"))

            return list

        }
    }
}