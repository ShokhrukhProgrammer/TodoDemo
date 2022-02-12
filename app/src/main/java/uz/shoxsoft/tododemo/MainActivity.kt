package uz.shoxsoft.tododemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import uz.shoxsoft.tododemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        todoAdapter = TodoAdapter(mutableListOf())
        val rvTodoItems = binding.rvTodoItems
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }
        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}