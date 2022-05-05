package com.tp_apps.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp_apps.R
import com.tp_apps.databinding.ItemTicketBinding
import com.tp_apps.domain.models.Ticket


class TicketsRecyclerViewAdapter(var tickets: List<Ticket>,
        private val onTicketItemClick: (Ticket) -> Unit
) : RecyclerView.Adapter<TicketsRecyclerViewAdapter.ViewHolder>() {

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ticket, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = tickets[position]
        holder.bind(ticket)
        holder.itemView.setOnClickListener{
            onTicketItemClick(ticket)
        }
    }

    override fun getItemCount() = tickets.size


    inner class ViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        private val binding = ItemTicketBinding.bind(view)

        fun bind(ticket: Ticket) {
            binding.txvTicketId.text = ticket.ticketNumber
            binding.chipBas.text = ticket.status
        }
    }




}
