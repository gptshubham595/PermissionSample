package com.shubham.permissions.presentation.permission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shubham.permissions.core.data.models.PermissionItem
import com.shubham.permissions.databinding.ItemPermissionBinding

class PermissionAdapter(
    private var permissions: List<PermissionItem>
) : RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder>() {

    inner class PermissionViewHolder(private val itemBinding: ItemPermissionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: PermissionItem) {
            itemBinding.permissionName.text = item.name
            itemBinding.permissionStatus.text = if (item.isGranted) "Granted" else "Denied"
            itemBinding.permissionStatus.setTextColor(
                if (item.isGranted) {
                    itemView.context.getColor(android.R.color.holo_green_dark)
                } else {
                    itemView.context.getColor(android.R.color.holo_red_dark)
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermissionViewHolder {
        val dataBinding = ItemPermissionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PermissionViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = permissions.size

    override fun onBindViewHolder(holder: PermissionViewHolder, position: Int) {
        holder.bind(permissions[position])
    }

    fun update(newList: List<PermissionItem>) {
        permissions = newList
        notifyDataSetChanged()
    }
}