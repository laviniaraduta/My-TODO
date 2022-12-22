package com.example.mytodo.fragments

import android.app.Activity
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mytodo.MainActivity
import com.example.mytodo.R
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.databinding.FragmentMainScreenBinding
import com.example.mytodo.tasks.TaskAdapter


class MainScreenFragment : Fragment() {
    private lateinit var binding: FragmentMainScreenBinding
    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_screen, container, false)
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = MainScreenViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainScreenViewModel::class.java]

        binding.lifecycleOwner = this
        val adapter = TaskAdapter(dataSource)
        binding.tasksList.adapter = adapter


        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        binding.mainScreenViewModel = viewModel


        binding.addTaskButton.setOnClickListener {
            view: View -> view.findNavController().navigate(MainScreenFragmentDirections.actionMainScreenFragmentToNewTaskFragment())
        }

        (activity as MainActivity).supportActionBar?.title = "Tasks"
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO: action when item is tapped
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Tasks"
    }
}