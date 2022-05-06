package com.tp_apps.presentation.ui.network

import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tp_apps.R
import com.tp_apps.databinding.FragmentNetworkBinding

class NetworkFragment : Fragment(R.layout.fragment_network) {

    private val binding: FragmentNetworkBinding by viewBinding()
    private val viewModel: NetworkViewModel by viewModels()

}