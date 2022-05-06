package com.tp_apps.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp_apps.databinding.ItemNetworkBinding
import com.tp_apps.domain.models.Network

class NetworksRecyclerViewAdapter(var networks: List<Network> = listOf())
    : RecyclerView.Adapter<NetworksRecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : NetworksRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemNetworkBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NetworksRecyclerViewAdapter.ViewHolder, position: Int) {
        val network = networks[position]
        holder.bind(network)

    }

    override fun getItemCount() = networks.count()


    inner class ViewHolder(private val binding: ItemNetworkBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(network : Network){
           // binding.txvNomNoeud.text = network.nodes.

        }

    }

}