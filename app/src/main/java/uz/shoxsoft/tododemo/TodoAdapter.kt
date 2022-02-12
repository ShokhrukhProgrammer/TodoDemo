package uz.shoxsoft.tododemo

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]

        holder.tvTodoTitle.text = curTodo.title
        holder.cbDone.isChecked = curTodo.isChecked

        toggleStrikeThrough(holder.tvTodoTitle, curTodo.isChecked)

        holder.cbDone.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.tvTodoTitle, isChecked)
            curTodo.isChecked = !curTodo.isChecked
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        else tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTodoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val cbDone: CheckBox = itemView.findViewById(R.id.cbDone)
    }
}