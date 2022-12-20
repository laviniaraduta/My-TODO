package com.example.mytodo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDatabaseDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * from current_tasks_table WHERE taskTitle = :key")
    suspend fun get(key: String): Task?

    @Query("DELETE FROM current_tasks_table")
    suspend fun clear()

    @Query("SELECT * FROM current_tasks_table ORDER BY taskTitle DESC")
    fun getAllTasks(): LiveData<MutableList<Task>>
}