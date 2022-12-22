package com.example.mytodo.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mytodo.MainActivity
import com.example.mytodo.R
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.databinding.FragmentNewTaskBinding
import java.time.LocalDate

class NewTaskFragment: Fragment() {
    private lateinit var viewModel: NewTaskViewModel
    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_task, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao
        val newTaskViewModelFactory = NewTaskViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, newTaskViewModelFactory)[NewTaskViewModel::class.java]

        binding.lifecycleOwner = this
        binding.newTaskViewModel = viewModel

        (activity as MainActivity).supportActionBar?.title = "New Task"
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTextDueDate.setOnClickListener {

        val dateEdit = binding.editTextDueDate
            val calendar = java.util.Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // set the calendar to current date
            // get the selected date and put it in the text edit's text field
            val datePicker = DatePickerDialog(this.requireContext(),
                { _, y, m, d ->
                    val realMonth = m + 1
                    val monthString = realMonth.toString().padStart(2, '0')
                    val date = "$y-$monthString-$d"
                    dateEdit.setText(date)
                },
                year, month, day
            )
            datePicker.show()
        }

        val suggestions = resources.getStringArray(R.array.categories)
        val adapter = ArrayAdapter<String>(this.requireContext(), android.R.layout.simple_spinner_item, suggestions)
        binding.editTextCategory.adapter = adapter



        binding.saveButton.setOnClickListener { view: View ->
            onSaveTask()
            view.findNavController().navigate(NewTaskFragmentDirections.actionNewTaskFragmentToMainScreenFragment())
        }


    }

    fun onSaveTask() {
        val taskTitle: String = binding.editTextTitle.text.toString()
        val taskDescription: String = binding.editTextDescription.text.toString()
        val taskDueDate: String = binding.editTextDueDate.text.toString()
        val taskCategory: String = binding.editTextCategory.selectedItem.toString()


        viewModel.addNewTask(taskTitle, taskDescription, taskDueDate, taskCategory)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "New Task"
    }
}