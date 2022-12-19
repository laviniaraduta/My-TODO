package com.example.mytodo.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.R
import com.example.mytodo.database.Task

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.taskTitle.text = item.taskTitle
        holder.taskDescription.text = item.taskDescription
        holder.dueDate.text = item.dueDate
        holder.category.text = item.category

        holder.itemView.setOnClickListener {
            // Edit the text in the TextView when the CardView is clicked
//            holder.taskTitle.text = "New task name"
            holder.itemView.findNavController().navigate(R.id.action_mainScreenFragment_to_editTaskFragment)
        }

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
    }
}