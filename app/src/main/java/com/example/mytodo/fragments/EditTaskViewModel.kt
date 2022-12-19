package com.example.mytodo.fragments

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.database.Task
import com.example.mytodo.database.TaskDatabaseDao
import kotlinx.coroutines.launch

class EditTaskViewModel (
    val database: TaskDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    private val TAG = "EditTaskViewModel"

    fun updateTask(taskTitle: String, taskDescription: String, taskDueDate: String, taskCategory: String) {
        Log.d(TAG, "received $taskTitle $taskDescription $taskDueDate $taskCategory ")
        viewModelScope.launch {
            val task = database.get(taskTitle)
            if (task != null) {
                task.taskTitle = taskTitle
                task.taskDescription = taskDescription
                task.dueDate = taskDueDate
                task.category = taskCategory
                update(task)
            }
        }
    }

    private suspend fun update(task: Task) {
        database.update(task)
    }

}