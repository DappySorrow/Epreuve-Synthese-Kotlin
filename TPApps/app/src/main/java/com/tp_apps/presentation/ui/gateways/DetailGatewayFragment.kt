package com.tp_apps.presentation.ui.gateways

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailGatewayBinding
import com.tp_apps.helpers.Resource

class DetailGatewayFragment : Fragment(R.layout.fragment_detail_gateway) {

    private val binding: FragmentDetailGatewayBinding by viewBinding()
    private val viewModel: DetailGatewayViewModel by viewModels{
        DetailGatewayViewModel.Factory(args.href)
    }

    private val args : DetailGatewayFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.gateway.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> TODO()
                is Resource.Success -> {
                    val gateway = it!!.data
                    binding.chipsStatus.text = gateway!!.connection.status

                }
            }
        }

       /* if(binding.chipsStatus.text == getString(R.string.online_gateway)){
            binding.btnReboot.visibility = View.VISIBLE
            binding.btnUpdate.visibility = View.VISIBLE
        }else{
            binding.btnReboot.visibility = View.INVISIBLE
            binding.btnUpdate.visibility = View.INVISIBLE
        }*/


        binding.btnReboot.setOnClickListener{
            viewModel.rebootAGateway()
        }
        binding.btnUpdate.setOnClickListener{
            viewModel.updateAGateway()
        }
    }



}