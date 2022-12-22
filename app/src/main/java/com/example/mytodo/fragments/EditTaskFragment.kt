package com.example.mytodo.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mytodo.MainActivity
import com.example.mytodo.R
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.databinding.FragmentEditTaskBinding
import com.example.mytodo.databinding.FragmentNewTaskBinding

class EditTaskFragment : Fragment() {
    private val TAG = "EditTaskFragment"
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
        Log.d(TAG, "onCreateView: received ${args.taskTitle}")
        completeExistingInfo(args)

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

        binding.saveButton.setOnClickListener { view: View ->
            onSaveEditedTask(args.taskTitle)
            view.findNavController().navigate(EditTaskFragmentDirections.actionEditTaskFragmentToMainScreenFragment())
        }
        (activity as MainActivity).supportActionBar?.title = "Edit Task"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun completeExistingInfo(args: EditTaskFragmentArgs) {
        binding.editTextTitle.setText(args.taskTitle)
        binding.editTextDescription.setText(args.taskDescription)
        val items = resources.getStringArray(R.array.categories)
        val pos = items.indexOf(args.taskCategory)
        val adapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item, items)
        binding.editTextCategory.adapter = adapter
        binding.editTextCategory.setSelection(pos)
        Log.d(TAG, "completeExistingInfo: $pos")
        binding.editTextDueDate.setText(args.taskDueDate)

    }

    private fun onSaveEditedTask(initialTitle: String) {
        Log.d(TAG, "onSaveEditedTask")
        val taskTitle: String = binding.editTextTitle.text.toString()
        val taskDescription: String = binding.editTextDescription.text.toString()
        val taskDueDate: String = binding.editTextDueDate.text.toString()
        val taskCategory: String = binding.editTextCategory.selectedItem.toString()


        viewModel.updateTask(initialTitle, taskTitle, taskDescription, taskDueDate, taskCategory)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).supportActionBar?.title = "Edit Task"
    }
}