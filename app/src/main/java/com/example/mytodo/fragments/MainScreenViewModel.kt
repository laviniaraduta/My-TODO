package com.example.mytodo.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mytodo.database.TaskDatabase
import com.example.mytodo.database.TaskDatabaseDao

class MainScreenViewModel(
    val database: TaskDatabaseDao,
    application: Application) : AndroidViewModel(application) {

        val tasks = database.getAllTasks()


}