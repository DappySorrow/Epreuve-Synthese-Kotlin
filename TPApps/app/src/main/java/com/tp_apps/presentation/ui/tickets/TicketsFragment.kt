package com.tp_apps.presentation.ui.tickets

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tp_apps.R
import com.tp_apps.databinding.FragmentTicketsBinding
/*import com.tp_apps.presentation.adapters.TicketsRecyclerViewAdapter*/

class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val binding: FragmentTicketsBinding by viewBinding()
    private val viewModel: TicketsViewModel by viewModels()

    /*private val ticketsRecyclerViewAdapter = TicketsRecyclerViewAdapter(listOf(), ::on)*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.rcvTickets.apply {
            adapter =
        }*/


    }

}