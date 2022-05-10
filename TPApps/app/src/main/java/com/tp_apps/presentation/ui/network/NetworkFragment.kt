package com.tp_apps.presentation.ui.network

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp_apps.R
import com.tp_apps.databinding.FragmentNetworkBinding
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.presentation.adapters.NetworksRecyclerViewAdapter

class NetworkFragment : Fragment(R.layout.fragment_network) {

    private val binding: FragmentNetworkBinding by viewBinding()
    private val viewModel: NetworkViewModel by viewModels()

    private lateinit var networksRecyclerViewAdapter : NetworksRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        networksRecyclerViewAdapter = NetworksRecyclerViewAdapter(listOf())
        binding.rcvReseau.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = networksRecyclerViewAdapter
        }

        viewModel.networks.observe(viewLifecycleOwner){

            when(it){
                is LoadingResource.Error -> {
                    Toast.makeText(requireContext(), "Erreur", Toast.LENGTH_LONG).show()
                }
                is LoadingResource.Loading -> {

                }
                is LoadingResource.Success -> {
                   // networksRecyclerViewAdapter.networkNodes = it.data!!
                }
            }
        }



    }


}