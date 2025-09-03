package com.example.jetpacktodoapp

import com.example.jetpacktodoapp.data.Todo
import java.sql.Date
import java.time.Instant

object TodoManager {
    private val todoList = mutableListOf<Todo>()

    fun getTodoList() : List<Todo>{
        return todoList
    }

    fun addTodoItem(title : String){
        todoList.add(Todo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now())))
    }

    fun deleteTodoItem(item: Todo){
        todoList.removeIf {
            it.id == item.id
        }
    }
}