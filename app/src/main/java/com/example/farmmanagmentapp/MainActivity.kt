package com.example.farmmanagmentapp

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import io.realm.Realm

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Startup backend servicex
        Realm.init(this)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navMenu = findViewById<com.google.android.material.navigation.NavigationView>(R.id.nav_menu)

        //Set up custom toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,findViewById(R.id.toolbar),R.string.nav_open,R.string.nav_close)

        actionBarDrawerToggle.isDrawerIndicatorEnabled = true

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navMenu.setNavigationItemSelectedListener(this)

        //DataFormater(this)



        //setToolBarTitle("Home")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_main_menu ->{changeFragment(R.id.action_global_homeFragment)}
            R.id.nav_view_data ->{changeFragment(R.id.action_global_veiwDataHome)}
            R.id.nav_record_birth ->{changeFragment(R.id.action_global_birthMothersInfo)}
            R.id.nav_mating ->{changeFragment(R.id.action_global_matingHome)}
            R.id.nav_medicine ->{}
            R.id.nav_mass_actions -> {changeFragment(R.id.action_global_massActionsHome)}
            R.id.nav_feedback -> (changeFragment(R.id.action_global_feedbackHome))
        }

        return true
    }

    fun setToolBarTitle(title: String){
        supportActionBar?.title = title
    }

    fun changeFragment(fragment: Int){
        Navigation.findNavController(this,R.id.nav_host_fragment).navigate(fragment)
    }
}



