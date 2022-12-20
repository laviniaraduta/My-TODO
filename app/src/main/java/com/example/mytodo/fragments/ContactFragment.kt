package com.example.mytodo.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentAboutUsBinding
import com.example.mytodo.databinding.FragmentContactBinding

class ContactFragment : Fragment() {
    private val TAG = "ContactFragment"
    private lateinit var binding: FragmentContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact, container, false)
        binding.github.movementMethod = LinkMovementMethod.getInstance()
        binding.linkedin.movementMethod = LinkMovementMethod.getInstance()
        return binding.root
    }
}