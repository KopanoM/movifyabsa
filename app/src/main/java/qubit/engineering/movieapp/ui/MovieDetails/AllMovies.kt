package qubit.engineering.movieapp.ui.MovieDetails

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_movies.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie.*
import qubit.engineering.movieapp.R
import qubit.engineering.movieapp.addapters.MovieAdapter
import qubit.engineering.movieapp.ui.SavedMoviesViewModel
import qubit.engineering.movielookup.data.Movie

class AllMovies : AppCompatActivity() {
    private lateinit var viewModel: SavedMoviesViewModel
    private val mDatabaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var mov: MutableList<Movie> = arrayListOf()
    private var genList: MutableList<Movie> = arrayListOf()
    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movies)
        //setSupportActionBar(findViewById(R.id.my_toolbar))
        txt_no_movies.visibility = View.GONE
        name = intent.getStringExtra("genre").toString()
        //setSupportActionBar(toolbar)

        supportActionBar?.apply {
            // Set toolbar title/app title
            title = name


            // Display the app icon in action bar/toolbar
            //setDisplayShowHomeEnabled(true)
            setDisplayUseLogoEnabled(true)
        }
        hideProgress(true)
        viewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel::class.java)
        initMovies()
        initRecyclerView()
    }
    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        moviesAdapter = MovieAdapter(genList)
        recycler_view.adapter = moviesAdapter
/*
        recycler_view.apply {

            layoutManager = LinearLayoutManager(context)
            adapter = MovieAdapter(mov)
        }

 */

    }
    private fun hideProgress(state:Boolean){
        if(state){
            progress_bar_content.visibility = View.VISIBLE
        }else{
            progress_bar_content.visibility = View.GONE
        }

    }

    private fun initMovies() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                hideProgress(false)
                dataSnapshot.children.mapNotNullTo(mov) {
                    it.getValue<Movie>(Movie::class.java)

                }
                filterList()

                //moviesAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        mDatabaseReference.child(retrievePref()).addListenerForSingleValueEvent(menuListener)
    }

    private fun filterList(){
        if(mov.size>0){

            for(mo in mov){
                if(mo.genre.contains(name)){
                    genList.add(mo)
                }
            }

        }
        if(genList.size>0){
            txt_no_movies.visibility = View.GONE
        }else{
            txt_no_movies.visibility = View.VISIBLE

        }


        moviesAdapter.notifyDataSetChanged()

    }

    fun retrievePref(): String{
        val preference =
            getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val uID = preference.getString("UID", "NULL").toString()
        return uID

    }
}