package com.example.ticktick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ticktick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        todoAdapter = TodoAdapter(mutableListOf() , binding.ivEmptyList)
        binding.rvTodoItems.adapter = todoAdapter


        binding.apply {
            btnAddTodo.setOnClickListener {
                val todoTitle = binding.etTodoTitle.text.toString()
                if (todoTitle.isNotEmpty()) {
                    val todo = Todo(todoTitle)
                    todoAdapter.addTodo(todo)
                    etTodoTitle.text.clear()
                }
            }
            btnCompletedTodo.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }

        }

    }
}