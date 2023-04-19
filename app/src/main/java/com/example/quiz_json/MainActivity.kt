package com.example.quiz_json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.example.quiz_json.Controllers.RangeStudent
import com.example.quiz_json.Controllers.StartQuizController
import com.example.quiz_json.Data.UserScoreViewModel
import com.example.quiz_json.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var UserScoreViewModel1: UserScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(SplashScreen())

        binding.apply {
            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, androidx.navigation.ui.R.string.nav_app_bar_open_drawer_description, com.google.android.material.R.string.mtrl_chip_close_icon_content_description)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.action_settings -> replaceFragment(Setting())
                    R.id.action_history -> replaceFragment(startQuize2())

                }
                true
            }
        }

    }

    private fun replaceFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container3,fragment).addToBackStack("null").commit()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}



