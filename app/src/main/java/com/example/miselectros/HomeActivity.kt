package com.example.miselectros

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_documents -> {
                startActivity(Intent(this, DocumentsActivity::class.java))
            }
            R.id.nav_modify_data -> {
                Toast.makeText(this, R.string.nav_modify_data, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_my_plan -> {
                Toast.makeText(this, R.string.nav_my_plan, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_settings -> {
                Toast.makeText(this, R.string.nav_settings, Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
