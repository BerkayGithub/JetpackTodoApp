package com.example.jetpacktodoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Todo(
    @PrimaryKey(true)
    var id: Int = 0,
    var title: String,
    var createdAt: Date
)