package com.example.mytodo.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mytodo.MainActivity
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentAboutUsBinding
import com.example.mytodo.databinding.FragmentEditTaskBinding

class AboutUsFragment : Fragment() {
    private val TAG = "AboutUsFragment"
    private lateinit var binding: FragmentAboutUsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false)
        (activity as MainActivity).supportActionBar?.title = "About us"
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "About us"
    }
}

