package com.example.mytodo.tasks

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.database.Task
import com.example.mytodo.database.TaskDatabaseDao
import com.example.mytodo.fragments.MainScreenFragmentDirections
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TaskAdapter(private val dao: TaskDatabaseDao): RecyclerView.Adapter<TaskAdapter.ViewHolder>(), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    var data = mutableListOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun removeTask(position: Int) {
        val item = data[position]
        data.removeAt(position)
        launch {
            deleteTask(item)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }

    }

    private suspend fun deleteTask(task: Task) {
        dao.deleteTask(task)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.taskTitle.text = item.taskTitle
        holder.taskDescription.text = item.taskDescription
        holder.dueDate.text = item.dueDate
        holder.category.text = item.category
        holder.bind(item)

        holder.itemView.setOnClickListener {
            // Edit the text in the TextView when the CardView is clicked
            holder
                .itemView
                .findNavController()
                .navigate(
                    MainScreenFragmentDirections
                    .actionMainScreenFragmentToEditTaskFragment(item.taskTitle, item.taskDescription, item.dueDate, item.category))
        }



        holder.checkBox.setOnClickListener {
            val title = item.taskTitle
            item.isChecked = true
            removeTask(position)
            val toast = Toast.makeText(it.context, "Done $title", Toast.LENGTH_SHORT)
            toast.show()
        }

    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.unbind()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // parent is always the RecyclerView
        // viewType is used when there are different types of views in the list
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val taskTitle: TextView = itemView.findViewById(R.id.task_title)
        val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        val dueDate: TextView = itemView.findViewById(R.id.due_date)
        val category: TextView = itemView.findViewById(R.id.category)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)


        fun bind(item: Task) {
            checkBox.isChecked = item.isChecked
            checkBox.setOnClickListener {
                item.isChecked = !item.isChecked
            }
        }

        fun unbind() {
            checkBox.setOnClickListener(null)
        }
    }
}