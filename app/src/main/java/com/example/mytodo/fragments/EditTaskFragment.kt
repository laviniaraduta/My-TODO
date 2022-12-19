package com.example.mytodo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mytodo.R
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.databinding.FragmentEditTaskBinding
import com.example.mytodo.databinding.FragmentNewTaskBinding

class EditTaskFragment : Fragment() {
    private lateinit var viewModel: EditTaskViewModel
    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_task, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao
        val editTaskViewModelFactory = EditTaskViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, editTaskViewModelFactory)[EditTaskViewModel::class.java]

        binding.lifecycleOwner = this
        binding.editTaskViewModel = viewModel
        val args = EditTaskFragmentArgs.fromBundle(requireArguments())
        Log.d("EditTaskFragment", "onCreateView: received ${args.taskTitle}")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}