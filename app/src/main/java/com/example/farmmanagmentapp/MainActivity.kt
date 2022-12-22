package com.example.farmmanagmentapp

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException







class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var config: RealmConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Startup backend service
        Realm.init(this)

        config = RealmConfiguration.Builder()
            .name("default-realm")
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            //TODO remove this when code writing finished as could delete user data
            .deleteRealmIfMigrationNeeded()
            .compactOnLaunch()
            .build()

        Realm.setDefaultConfiguration(config)

        //Delete all realm data
        /*var realm:Realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()*/


            drawerLayout = findViewById(R.id.drawerLayout)
        val navMenu = findViewById<NavigationView>(R.id.nav_menu)

        //Set up custom toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,findViewById(R.id.toolbar),R.string.nav_open,R.string.nav_close)

        actionBarDrawerToggle.isDrawerIndicatorEnabled = true

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navMenu.setNavigationItemSelectedListener(this)

        //setToolBarTitle("Home")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_main_menu ->{changeFragment(R.id.action_global_homeFragment); drawerLayout.closeDrawers()}
            R.id.nav_view_data ->{changeFragment(R.id.action_global_veiwDataHome); drawerLayout.closeDrawers()}
            R.id.nav_record_birth ->{changeFragment(R.id.action_global_birthMothersInfo); drawerLayout.closeDrawers()}
            R.id.nav_mating ->{changeFragment(R.id.action_global_matingHome); drawerLayout.closeDrawers()}
            R.id.nav_medicine ->{changeFragment(R.id.action_global_medicineSelectAnimals); drawerLayout.closeDrawers()}
            R.id.nav_mass_actions -> {changeFragment(R.id.action_global_massActionsHome); drawerLayout.closeDrawers()}
            R.id.nav_feedback -> {changeFragment(R.id.action_global_feedbackHome); drawerLayout.closeDrawers()}
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



