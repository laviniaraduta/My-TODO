package com.example.mytodo.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mytodo.database.TaskDatabaseDao

class EditTaskViewModel (
    val database: TaskDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}