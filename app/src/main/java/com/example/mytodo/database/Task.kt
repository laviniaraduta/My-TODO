package com.example.mytodo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "current_tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,
    @ColumnInfo(name = "task_title")
    var taskTitle: String,
    @ColumnInfo(name = "task_description")
    var taskDescription:String,
    @ColumnInfo(name = "due_date")
    var dueDate: String
)
