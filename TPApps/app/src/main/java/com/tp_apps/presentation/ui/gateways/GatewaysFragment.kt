package com.tp_apps.presentation.ui.gateways

import android.os.Bundle
import android.util.Log
import android.util.Log.DEBUG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp_apps.R
import com.tp_apps.databinding.FragmentGatewaysBinding
import com.tp_apps.databinding.FragmentTicketsBinding
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.notifyAllItemChanged
import com.tp_apps.presentation.adapters.GatewaysViewAdapter

class GatewaysFragment : Fragment(R.layout.fragment_gateways) {

    private val binding: FragmentGatewaysBinding by viewBinding()
    private val viewModel: GatewaysViewModel by viewModels()

    private lateinit var gatewaysViewAdapter: GatewaysViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gatewaysViewAdapter = GatewaysViewAdapter(listOf())
        binding.rcvGateways.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = gatewaysViewAdapter
        }

        viewModel.gateways.observe(viewLifecycleOwner) {

            when (it) {
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    Log.e("test", it.message!!)
                }
                is LoadingResource.Success -> {
                    binding.pgbLoading.hide()
                    gatewaysViewAdapter.gateways = it.data!!
                    gatewaysViewAdapter.notifyAllItemChanged()
                    binding.rcvGateways.visibility = View.VISIBLE
                }
                is LoadingResource.Loading -> {
                    binding.pgbLoading.show()
                    binding.rcvGateways.visibility = View.INVISIBLE
                }
            }

        }


    }

}