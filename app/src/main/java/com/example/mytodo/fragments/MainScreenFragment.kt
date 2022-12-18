package com.example.mytodo.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.mytodo.R
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.databinding.FragmentMainScreenBinding
import com.example.mytodo.tasks.TaskAdapter

class MainScreenFragment : Fragment() {

    companion object {
        fun newInstance() = MainScreenFragment()
    }

    private lateinit var viewModel: MainScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMainScreenBinding>(inflater, R.layout.fragment_main_screen, container, false)
        setHasOptionsMenu(true)

        val application = requireNotNull(this.activity).application
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao

        val viewModelFactory = MainScreenViewModelFactory(dataSource, application)

        val mainScreenViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MainScreenViewModel::class.java)

        binding.setLifecycleOwner(this)
        val adapter = TaskAdapter()
        binding.tasksList.adapter = adapter


        mainScreenViewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        binding.mainScreenViewModel = mainScreenViewModel



        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO: action when item is tapped
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}