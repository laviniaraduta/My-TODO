package com.example.mytodo.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentSettingsBinding
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import com.example.mytodo.MainActivity
import java.util.*

class SettingsFragment : Fragment() {
    private val TAG = "SettingsFragment"
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        val sharedPref = activity?.getSharedPreferences("preferences_theme", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        val state = sharedPref?.getBoolean("switchValue", false)
        Log.d(TAG, "onCreateView: switchul are valoarea $state")
        binding.switch1.isChecked = state?: false
        binding.switch1.setOnClickListener {
            if (binding.switch1.isChecked) {
                Log.d(TAG, "activate Night mode")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor?.putBoolean("switchValue", true)?.apply()
            } else {
                Log.d(TAG, "activate Light mode")
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor?.putBoolean("switchValue", false)?.apply()
            }
        }

        val languages = resources.getStringArray(R.array.languages)
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, languages)
        binding.spinner.adapter = adapter

//        binding.spinner.setOnItemSelectedListener { parent, view, position, id ->
//            val language = parent?.getItemAtPosition(position).toString()
//            val locale = when (language) {
//                "english" -> Locale.ENGLISH
//                "romana" -> Locale.Builder().setLanguage("ro").setRegion("RO").build()
//                else -> Locale.ENGLISH
//            }
//            setLocale(locale)
//            activity?.recreate()
//
//        }

        (activity as MainActivity).supportActionBar?.title = "Settings"
        return binding.root
    }

    private fun setLocale(locale: Locale) {
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Settings"
    }
}




