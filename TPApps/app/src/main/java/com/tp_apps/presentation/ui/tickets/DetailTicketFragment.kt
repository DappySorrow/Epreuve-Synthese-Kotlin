package com.tp_apps.presentation.ui.tickets

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailTicketBinding
import com.tp_apps.databinding.FragmentTicketsBinding
import com.tp_apps.presentation.ui.capture.CaptureActivity



class DetailTicketFragment : Fragment(R.layout.fragment_detail_ticket){

    private val ticketHref = "https://api.andromia.science/tickets/62741244ae5754003a57f263"

    private val binding: FragmentDetailTicketBinding by viewBinding()
    private val viewModel: DetailTicketViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonInstall.setOnClickListener() {
            startActivity(CaptureActivity.newIntent(requireContext()))
        }

    }



}