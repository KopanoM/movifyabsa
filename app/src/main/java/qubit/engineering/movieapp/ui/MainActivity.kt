package qubit.engineering.movieapp.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import qubit.engineering.movieapp.ui.MovieDetails.AllMovies
import qubit.engineering.movieapp.ui.MovieDetails.MovieActivity
import qubit.engineering.movieapp.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (retrievePref())
            saveSharedPref()

        setOnClickListeners()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {

            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.queryHint = "Search for a movie"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                //Toast.makeText(this@MainActivity, newText, Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // val intent = Intent(this@MainActivity, SearchResultsActivity::class.java) // (1) (2)

                //startActivity(intent)
                //Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                // task HERE
                //on submit send entire query
                passDataOpenActivity(query)
                return false
            }

        })

        return true
    }

    fun passDataOpenActivity(data: String) {

        val intent = Intent(this, MovieActivity::class.java);
        intent.putExtra("moviename", data)

        startActivity(intent)


    }

    fun setOnClickListeners() {

        action.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Action")
            startActivity(intent)
        }
        comedy_cardview.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Comedy")
            startActivity(intent)
        }
        drama_cardview.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Drama")
            startActivity(intent)
        }
        scifi_cardview.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Sci-Fi")
            startActivity(intent)
        }
        romance_cardview.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Romance")
            startActivity(intent)
        }
        horror_cardview.setOnClickListener {
            val intent = Intent(this, AllMovies::class.java)
            intent.putExtra("genre", "Horror")
            startActivity(intent)
        }


    }

    fun saveSharedPref() {
        var uniqueID: String = UUID.randomUUID().toString()
        val preference =
            getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putString("UID", uniqueID)
        editor.commit()
    }

    fun retrievePref(): Boolean {
        val preference =
            getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val uID = preference.getString("UID", "NULL")
        if (uID == "NULL") {
            return true
        } else {
            return false
        }
    }
}