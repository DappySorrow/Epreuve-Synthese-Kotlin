package com.tp_apps.presentation.ui.tickets

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tp_apps.R
import com.tp_apps.databinding.FragmentTicketsBinding

class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private val binding: FragmentTicketsBinding by viewBinding()
    private val viewModel: TicketsViewModel by viewModels()



}