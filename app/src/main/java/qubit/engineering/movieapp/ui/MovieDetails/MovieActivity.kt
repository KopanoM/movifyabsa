package qubit.engineering.movieapp.ui.MovieDetails

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_movie.*
import qubit.engineering.movieapp.R
import qubit.engineering.movieapp.api.APIClient
import qubit.engineering.movieapp.api.MovieInterface
import qubit.engineering.movieapp.network.CheckNetwork
import qubit.engineering.movieapp.repo.MovieRepo
import qubit.engineering.movieapp.repo.MovieViewModel
import qubit.engineering.movielookup.data.Movie

@Suppress("UNCHECKED_CAST")
class MovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieRepo: MovieRepo
    private var mov: List<Movie> = arrayListOf()
    private val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var uniqueID : String = "path"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        initVariables()
        viewModel.movieDets.observe(this, Observer {
            initUI(it)

        })
        viewModel.networkState.observe(this, Observer {

            if(it == CheckNetwork.LOADING){
                progress_bar1.visibility = View.VISIBLE
                txt_error.visibility = View.VISIBLE
            }else if(it == CheckNetwork.LOADED){

                if(it == CheckNetwork.LOADED)
                    progress_bar1.visibility = View.GONE

                txt_error.visibility = View.GONE

            }else{

                progress_bar1.visibility = View.GONE
                txt_error.visibility = View.VISIBLE
            }


        })


    }
    private fun initVariables(){
        uniqueID = retrievePref()
        val name = intent.getStringExtra("moviename")
        //showHideUi(false)

        val api : MovieInterface = APIClient.getClient()
        movieRepo = MovieRepo(api)
        viewModel = getMyModel(name.toString(),"")

    }


    private fun showHideUi(state:Boolean){

        if(state){
            progress_bar1.visibility = View.GONE

        }else{
            progress_bar1.visibility = View.VISIBLE
        }



    }

    fun retrievePref(): String{
        val preference =
            getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val uID = preference.getString("UID", "NULL").toString()
        return uID

    }


    private fun getMyModel(name: String,year: String): MovieViewModel{
        return ViewModelProviders.of(this,object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieViewModel(movieRepo,name,year) as T
            }
        })[MovieViewModel::class.java]

    }

    fun getUID():String{
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.READ_PHONE_STATE)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            return ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_PHONE_STATE), 1).toString()
        }else{
            return "path"
        }
    }
    fun seperateCategories(categories:String){
        val genreOne: String
        val genreTwo: String
        val list: List<String> = categories.split(",").map{it -> it.trim()}
        if(list.size==1){
            movie_genre_one.text = list[0]
        }else if(list.size>=2) {
            movie_genre_one.text = list[0]
            movie_genre_two.text = list[1]
        }


    }

    fun initUI(movie: Movie){
        Glide.with(this)
            .load(movie.poster)
            .into(movie_thumbnail)
        //movie_genre_one.text = movie.genre
        movie_title.text = movie.title
        movie_year.text = movie.year
        movie_time.text = movie.runtime
        movie_summary.text = movie.plot
        movie_language.text = movie.language
        movie_production.text = movie.production
        movie_rotten_tomatoes.text = movie.imdbRating
        movie_actors.text = movie.actors
        movie_allowed_rating.text = movie.rated
        movie_rating.text = movie.metascore
        movie_heart.setOnClickListener{
            viewModel.addUser(movie)
            showSnack(it,"Added to favorites")
            Toast.makeText(applicationContext,"Added To Favorites",Toast.LENGTH_SHORT).show()
            mov = viewModel.getUsers()
            loadDatabase(mDatabaseReference)
        }
        seperateCategories(movie.genre)


    }
    fun loadDatabase(firebaseData: DatabaseReference) {
        mov.forEach {
            val key = firebaseData.child("users").push().key
            //it.imdbID = key
            firebaseData.child(uniqueID).child(it.imdbID).setValue(it)
        }
    }

    fun showSnack(view: View,text:String){
        //Snackbar(view)
        val snackbar = Snackbar.make(view, text,
            Snackbar.LENGTH_LONG).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        //snackbarView.setBackgroundColor(Color.)
        //val textView =
          //  snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        //textView.setTextColor(Color.BLUE)
        //textView.textSize = 28f
        snackbar.show()
    }
}