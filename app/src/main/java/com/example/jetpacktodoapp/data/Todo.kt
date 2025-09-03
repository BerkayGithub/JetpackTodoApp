package com.example.jetpacktodoapp.data

import java.time.Instant
import java.util.Date

data class Todo(
    var id: Int,
    var title: String,
    var createdAt: Date
)

fun getFakeTodo() : List<Todo>{
    return listOf(
        Todo(1,"First to do",Date.from(Instant.now())),
        Todo(2,"Second to do",Date.from(Instant.now())),
        Todo(3,"This id my third to do",Date.from(Instant.now())),
        Todo(4,"This will be my fourth to do",Date.from(Instant.now())),
    )
}