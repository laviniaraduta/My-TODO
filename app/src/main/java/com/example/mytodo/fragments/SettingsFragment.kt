package com.example.mytodo.fragments

import android.os.Bundle
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
        binding.switch1.setOnClickListener {
            val state = binding.switch1.isActivated
            if (state) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                activity?.setTheme(R.style.DarkTheme)
//                activity?.recreate()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                activity?.setTheme(R.style.LightTheme)
//                activity?.recreate()
            }
        }
        return binding.root
    }

}