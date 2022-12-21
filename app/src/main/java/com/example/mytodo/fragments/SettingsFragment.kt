package com.example.mytodo.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private val TAG = "SettingsFragment"
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
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
        return binding.root
    }
}