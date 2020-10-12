package com.example.fragmenttask.presentation.noti

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmenttask.R
import com.example.fragmenttask.base.BaseListAdapter
import com.example.fragmenttask.data.models.Devices
import com.example.fragmenttask.database.DateFormatter
import com.example.fragmenttask.database.Notification
import com.example.fragmenttask.databinding.ItemLayoutBinding
import com.example.fragmenttask.databinding.LogLayoutBinding
import com.example.fragmenttask.databinding.LogLayoutBindingImpl
import kotlinx.android.synthetic.main.log_layout.view.*

class NotificationAdapter(
    private val dataset: List<Notification>,
    private val dateFormatter: DateFormatter,
    private val dataBindingComponent: DataBindingComponent,
) : BaseListAdapter<Notification, LogLayoutBinding>(diffCallback = diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): LogLayoutBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false, dataBindingComponent
        )

    }

    override fun bind(binding: LogLayoutBinding, item: Notification, position: Int) {
        binding.result = item
    }

   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.log_layout, parent, false)
        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.txtTitle.text = dataset[position].title
        holder.txtTime.text = dateFormatter.formatDate(dataset[position].time)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }*/

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.txt_title!!
        val txtTime = itemView.txt_time!!
    }



}
private val diffCallback: DiffUtil.ItemCallback<Notification> =
    object : DiffUtil.ItemCallback<Notification>() {

        override fun areItemsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notification, newItem: Notification): Boolean {
            return oldItem.title == newItem.title
        }
    }
