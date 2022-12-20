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

    fun updateTask(initialTitle: String, taskTitle: String, taskDescription: String, taskDueDate: String, taskCategory: String) {
        Log.d(TAG, "received $taskTitle $taskDescription $taskDueDate $taskCategory ")
        viewModelScope.launch {
            val task = database.get(initialTitle)
            if (task != null) {
                Log.d(TAG, "updateTask: task exists $initialTitle")
                if (initialTitle.equals(taskTitle)) {
                    task.taskDescription = taskDescription
                    task.dueDate = taskDueDate
                    task.category = taskCategory
                    update(task)
                } else {
                    deleteTask(task)
                    val newTask = Task(taskTitle, taskDescription, taskDueDate, taskCategory)
                    insertTask(newTask)
                }

            }
        }
    }

    private suspend fun deleteTask(task: Task) {
        database.deleteTask(task)
    }

    private suspend fun insertTask(task: Task) {
        database.insert(task)
    }

    private suspend fun update(task: Task) {
        database.update(task)
    }

}