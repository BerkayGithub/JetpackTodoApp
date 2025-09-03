package com.example.jetpacktodoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpacktodoapp.data.Todo
import com.example.jetpacktodoapp.ui.theme.JetpackTodoAppTheme
import com.example.jetpacktodoapp.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(todoViewModel: TodoViewModel){

    val todoList by todoViewModel.todoList.observeAsState()
    var todoItemToAdd by remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxHeight().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = todoItemToAdd, onValueChange = { newValue ->
                todoItemToAdd = newValue
            }, maxLines = 1)
            Button(onClick = {
                todoViewModel.addTodoItem(todoItemToAdd)
                todoItemToAdd = ""
            }) {
                Text("Add")
            }
        }

        todoList?.let {
            LazyColumn(content = {
                    itemsIndexed(it){ index: Int, item: Todo ->
                        TodoListItem(item) { todoViewModel.deleteTodoItem(item) }
                    }
                }
            )
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            text = "No items on the list",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        
    }
}

@Composable
private fun TodoListItem(item: Todo, onDelete : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                SimpleDateFormat("HH:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt),
                modifier = Modifier.padding(horizontal = 4.dp),
                color = Color.White,
                fontSize = 12.sp
            )
            Text(
                item.title,
                modifier = Modifier.padding(horizontal = 4.dp),
                color = Color.White,
                fontSize = 20.sp
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPagePreview() {
    JetpackTodoAppTheme {
        //TodoListPage()
    }
}