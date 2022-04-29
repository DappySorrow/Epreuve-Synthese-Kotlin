package com.tp_apps.ui.gateways

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.tp_apps.databinding.FragmentGatewaysBinding
import com.tp_apps.databinding.FragmentTicketsBinding
import com.tp_apps.ui.gateways.GatewaysViewModel

class GatewaysFragment : Fragment() {

    private val binding: FragmentGatewaysBinding by viewBinding()
    private val viewModel: GatewaysViewModel by viewModels()

}