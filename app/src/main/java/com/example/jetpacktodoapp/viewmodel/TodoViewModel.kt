package com.example.jetpacktodoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacktodoapp.MainApplication
import com.example.jetpacktodoapp.data.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.Instant

class TodoViewModel : ViewModel() {
    
    val todoDAO = MainApplication.todoDatabase.getTodoDAO()
    val todoList : LiveData<List<Todo>> = todoDAO.getTodoList()

    fun addTodoItem(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.addTodoItem(Todo(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteTodoItem(item : Todo){
        viewModelScope.launch(Dispatchers.IO) {
            todoDAO.deleteTodoItem(item.id)
        }
    }

}