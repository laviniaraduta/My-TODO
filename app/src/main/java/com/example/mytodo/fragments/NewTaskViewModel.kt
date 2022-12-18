package com.example.mytodo.fragments

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


class NewTaskViewModel(
    val database: TaskDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    private lateinit var _taskTitle: MutableLiveData<String>
    private lateinit var _taskDescription: MutableLiveData<String>

    val taskTitle: LiveData<String>
        get() = _taskTitle
    val taskdescription: LiveData<String>
        get() = _taskDescription


    fun addNewTask(taskTitle: String, taskDescription: String, taskDueDate: String) {
        Log.d("NewTaskViewModel", "addNewTask: $taskTitle $taskDescription $taskDueDate")
        val id = 0L
        viewModelScope.launch {
            val newTask = Task(id, taskTitle, taskDescription)
            insert(newTask)
        }

    }

    private suspend fun insert(task: Task) {
        database.insert(task)
    }
}