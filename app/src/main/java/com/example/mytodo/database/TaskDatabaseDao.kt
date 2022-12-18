package com.example.mytodo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDatabaseDao {
    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * from current_tasks_table WHERE taskId = :key")
    suspend fun get(key: Long): Task?

    @Query("DELETE FROM current_tasks_table")
    suspend fun clear()

    @Query("SELECT * FROM current_tasks_table ORDER BY taskId DESC")
    fun getAllTasks(): LiveData<List<Task>>
}