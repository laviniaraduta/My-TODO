package com.example.mytodo.fragments

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentNewTaskBinding

class NewTaskFragment: Fragment() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentNewTaskBinding>(inflater, R.layout.fragment_new_task, container, false)

        val dateEdit = binding.editTextDueDate
        binding.editTextDueDate.setOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this.requireContext(),
                { _, y, m, d ->
                    val dat = "$d/{$m + 1}/$y"
                    dateEdit.setText(dat)
                },
                year, month, day
            )
            datePicker.show()
        }



        return binding.root
    }
}