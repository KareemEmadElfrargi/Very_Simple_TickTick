package com.example.ticktick

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(private val todoList: MutableList<Todo> , private val emptyIconView:View) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoTitle = itemView.findViewById<TextView>(R.id.tvTodoTitle)
        val cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo, parent, false
            )
        )
    }
    fun addTodo(todo:Todo){
        todoList.add(todo)
        notifyItemInserted(todoList.size-1)
        checkEmptyListVisibility()
    }

    fun deleteDoneTodos(){
        todoList.removeAll { todo ->
            todo.isChecked // if the item is check so remove it
        }
        notifyDataSetChanged()
        checkEmptyListVisibility()
    }
    private fun toggleStrikeThrough(tvTodoTitle: TextView,isCheckBox: Boolean){
        if (isCheckBox){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todoList[position]
        holder.apply {
            todoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(todoTitle,currentTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _ , isChecked ->
                toggleStrikeThrough(todoTitle,isChecked)
                currentTodo.isChecked = !currentTodo.isChecked
            }

        }

    }
    private fun checkEmptyListVisibility() {
        emptyIconView as TextView
        if (todoList.size ==0) {
            emptyIconView.visibility = View.VISIBLE
        } else {
            emptyIconView.visibility = View.GONE
        }
    }
    override fun getItemCount() = todoList.size
}