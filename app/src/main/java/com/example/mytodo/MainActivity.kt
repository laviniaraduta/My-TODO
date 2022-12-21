package com.example.mytodo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.mytodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawerLayout = binding.drawerLayout
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)

        val sharedPref = getSharedPreferences("preferences_theme", MODE_PRIVATE)
        val editor = sharedPref?.edit()

        val state = sharedPref?.getBoolean("switchValue", false)
        if (state == true) {
            Log.d(TAG, "Dark mode is activated ")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor?.putBoolean("switchValue", true)?.apply()
        } else {
            Log.d(TAG, "Light mode is activated ")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor?.putBoolean("switchValue", false)?.apply()
        }
        val navHeader= binding.navView.getHeaderView(0)
        val imageView = navHeader.findViewById<ImageView>(R.id.navHeaderImage)
        Glide.with(this)
            .load("https://lh3.googleusercontent.com/ogw/AOh-ky1XPZ9jP2HAjTWsEeyWgPZrkSkrRzxoN0coX4R3fg=s64-c-mo")
            .placeholder(R.drawable.app_logo_foreground)
            .fitCenter()
            .into(imageView)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}