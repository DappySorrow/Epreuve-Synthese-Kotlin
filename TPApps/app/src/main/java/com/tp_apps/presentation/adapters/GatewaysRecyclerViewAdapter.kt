package com.tp_apps.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp_apps.databinding.ItemGatewaysBinding
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.ColorHelper

class GatewaysRecyclerViewAdapter(var gateways : List<Gateway> = listOf()) :
    RecyclerView.Adapter<GatewaysRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemGatewaysBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gateway: Gateway) {

            val status = gateway.connection.status

            if (status == "Online")
            {
                binding.chipStatus.chipBackgroundColor = ColorHelper.connectionStatusColor(binding.root.context,gateway.connection.status)
                binding.chipStatus.text = status
                binding.txvLatence.text = "${gateway.connection.ping.toString()} ns"
                binding.txvDownload.text = "${gateway.connection.download.toString()} Ebps"
                binding.txvUpload.text = "${gateway.connection.upload.toString()} Ebps"
                binding.txvTicketId.text = gateway.serialNumber
            }
            else{
                binding.chipStatus.chipBackgroundColor = ColorHelper.connectionStatusColor(binding.root.context,gateway.connection.status)
                binding.chipStatus.text = status
                binding.txvOffline.visibility = View.VISIBLE
                binding.txvLatence.visibility = View.INVISIBLE
                binding.txvDownload.visibility = View.INVISIBLE
                binding.txvUpload.visibility = View.INVISIBLE
                binding.imvDownload.visibility = View.INVISIBLE
                binding.imvLatence.visibility = View.INVISIBLE
                binding.imvUpload.visibility = View.INVISIBLE
                binding.txvTicketId.text = gateway.serialNumber
            }
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