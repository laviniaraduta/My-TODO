package com.example.mytodo.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mytodo.database.Task
import com.example.mytodo.database.TaskDatabaseDao
import kotlinx.coroutines.launch
import java.time.LocalDate


class NewTaskViewModel(
    val database: TaskDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    private lateinit var _taskTitle: MutableLiveData<String>
    private lateinit var _taskDescription: MutableLiveData<String>

    val taskTitle: LiveData<String>
        get() = _taskTitle
    val taskdescription: LiveData<String>
        get() = _taskDescription


    @SuppressLint("NewApi")
    fun addNewTask(taskTitle: String, taskDescription: String, taskDueDate: String) {
        Log.d("NewTaskViewModel", "addNewTask: $taskTitle $taskDescription $taskDueDate")
        // I receive the date in format yyyy-MM-dd
        // in this format I can also sort them

        viewModelScope.launch {
            val id = getTaskNumber()
            Log.d("NewTaskViewModel add", "addNewTask: id = $id")
            val newTask = Task(0L, taskTitle, taskDescription, taskDueDate)
            insert(newTask)
        }

    }

    private suspend fun getTaskNumber(): Int {
        val listOfTasks = database.getAllTasks()
        return listOfTasks.value?.size ?: 0
    }
    private suspend fun insert(task: Task) {
        database.insert(task)
    }
}