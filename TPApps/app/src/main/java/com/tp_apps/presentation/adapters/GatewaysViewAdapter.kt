package com.tp_apps.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tp_apps.databinding.ItemGatewaysBinding
import com.tp_apps.domain.models.Gateway

class GatewaysViewAdapter(var gateways : List<Gateway> = listOf()) :
    RecyclerView.Adapter<GatewaysViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemGatewaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gateway: Gateway) {
            binding.testView.text = "gateway.href"

        }
    }

    //==========================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGatewaysBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gateway = gateways[position]
        holder.bind(gateway)
    }

    override fun getItemCount()= gateways.size

}