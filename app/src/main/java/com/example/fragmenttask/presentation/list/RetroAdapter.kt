package com.example.fragmenttask.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.fragmenttask.R
import com.example.fragmenttask.base.BaseListAdapter
import com.example.fragmenttask.databinding.ItemLayoutBinding
import com.example.fragmenttask.data.models.Devices


class RetroAdapter(
    private val dataBindingComponent: DataBindingComponent,
    val onItemClick: (device: Devices) -> Unit
) : BaseListAdapter<Devices, ItemLayoutBinding>(diffCallback = diffCallback) {
    override fun createBinding(parent: ViewGroup, viewType: Int): ItemLayoutBinding {

        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_layout,
            parent,
            false, dataBindingComponent
        )

    }

    override fun bind(binding: ItemLayoutBinding, item: Devices, position: Int) {
        binding.result = item
        binding.root.setOnClickListener {
            onItemClick(item)
        }

    }
}

private val diffCallback: DiffUtil.ItemCallback<Devices> =
    object : DiffUtil.ItemCallback<Devices>() {
        override fun areItemsTheSame(oldItem: Devices, newItem: Devices): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Devices, newItem: Devices): Boolean {
            return oldItem.name == newItem.name
        }
    }

//    private var dataset: ArrayList<Devices> = ArrayList()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.txtBrand.text = dataset[position].brand
//        holder.txtName.text = dataset[position].name
//
//        val requestOption = RequestOptions()
//            .placeholder(R.drawable.ic_launcher_background)
//            .error(R.drawable.ic_launcher_background)
//        Glide.with(holder.itemView).applyDefaultRequestOptions(requestOption)
//            .load(dataset[position].imageUrl).into(holder.imgView)
//
//        holder.itemView.setOnClickListener {
//            onItemClick(dataset[position])
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataset.size
//    }
//
//    fun submit(retroResponse: ArrayList<Devices>) {
//        dataset = retroResponse
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val imgView: ImageView = itemView.image_view
//        val txtBrand: TextView = itemView.txt_brand
//        val txtName: TextView = itemView.txt_name
//    }