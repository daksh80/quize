// Define the package where this class is located
package com.example.quiz_json

// Import necessary classes and packages
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.quiz_json.Controllers.RangeStudent
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.Data.UserScoreViewModel
import com.example.quiz_json.databinding.ActivityMainBinding

// Define the class MainActivity, which extends the AppCompatActivity class
class MainActivity : AppCompatActivity() {

    // Declare some properties for this class
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var UserScoreViewModel1: UserScoreViewModel

    // This method is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Replace the default fragment with the SplashScreen fragment
        replaceFragment(SplashScreen())

        // Hide the action bar
        supportActionBar?.hide()

        // Set up the navigation drawer
        binding.apply {

            // Create an instance of ActionBarDrawerToggle and attach it to the drawer layout
            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, androidx.navigation.ui.R.string.nav_app_bar_open_drawer_description, com.google.android.material.R.string.mtrl_chip_close_icon_content_description)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            // Enable the "up" button on the action bar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            // Set up the item click listener for the navigation drawer
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.action_settings -> replaceFragment(Setting())
                    R.id.action_history -> replaceFragment(startQuize2())
                }
                true
            }
        }
    }

    // This method replaces the fragment in the fragment container with a new fragment
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }

    // This method is called when an options menu item is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}
