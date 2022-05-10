package com.tp_apps.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp_apps.databinding.ItemNetworkBinding
import com.tp_apps.domain.models.NetworkNode
import com.tp_apps.helpers.ColorHelper

class NetworksRecyclerViewAdapter(var networkNodes: List<NetworkNode> = listOf())
    : RecyclerView.Adapter<NetworksRecyclerViewAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : NetworksRecyclerViewAdapter.ViewHolder {
        return ViewHolder(ItemNetworkBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: NetworksRecyclerViewAdapter.ViewHolder, position: Int) {
        val network = networkNodes[position]
        holder.bind(network)

    }

    override fun getItemCount() = networkNodes.count()


    inner class ViewHolder(private val binding: ItemNetworkBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(networkNodes : NetworkNode){
            binding.txvNomNoeud.text = networkNodes.name
            binding.txvAdresseIP.text = networkNodes.connectionNode.ip
            binding.txvDownload.text = networkNodes.connectionNode.download.toString()
            binding.txvLatence.text = networkNodes.connectionNode.download.toString()
            binding.txvLatence.text = networkNodes.connectionNode.ping.toString()
            binding.txvUpload.text = networkNodes.connectionNode.upload.toString()
            binding.txvQualiteSignal.text = networkNodes.connectionNode.signal.toString()


            binding.chipHaut.text = networkNodes.connectionNode.status
            binding.chipHaut.chipBackgroundColor = ColorHelper.connectionStatusColor(binding.root.context,networkNodes.connectionNode.status)



        }

    }

}