package com.example.jetpacktodoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpacktodoapp.TodoManager
import com.example.jetpacktodoapp.data.Todo

class TodoViewModel : ViewModel() {
    
    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList : LiveData<List<Todo>> = _todoList

    fun getTodoList(){
        _todoList.value = TodoManager.getTodoList().reversed()
    }

    fun addTodoItem(title: String){
        TodoManager.addTodoItem(title)
        getTodoList()
    }

    fun deleteTodoItem(item : Todo){
        TodoManager.deleteTodoItem(item)
        getTodoList()
    }

}