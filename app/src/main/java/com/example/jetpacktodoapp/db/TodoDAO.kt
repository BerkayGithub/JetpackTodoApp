package com.example.jetpacktodoapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpacktodoapp.data.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM TODO")
    fun getTodoList() : LiveData<List<Todo>>

    @Insert
    fun addTodoItem(item : Todo)

    @Query("DELETE FROM TODO WHERE id = :id")
    fun deleteTodoItem(id: Int)

}