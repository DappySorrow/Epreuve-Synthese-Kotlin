package com.tp_apps.presentation.ui.gateways

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailGatewayBinding
import com.tp_apps.helpers.Constants
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
                    val gateway = it.data
                    binding.chipsStatus.text = gateway!!.connection.status

                // TODO BEN : Tu dois faire refresh le detail gateway
                // tu peux t'inspirer du code de JS dans le DetailTicketFragment
                // et dans la DetailTicketViewModel
                    when (Constants.ConnectionStatus.valueOf(gateway.connection.status)) {
                        Constants.ConnectionStatus.Online -> {
                            with(binding) {
                                chipsStatus.text = getString(R.string.Online)
                                btnReboot.visibility = View.VISIBLE
                                btnUpdate.visibility = View.VISIBLE
                            }
                        }
                        Constants.ConnectionStatus.Offline -> {
                            with(binding){
                                chipsStatus.text = getString(R.string.Offline)
                                binding.btnReboot.visibility = View.INVISIBLE
                                binding.btnUpdate.visibility = View.INVISIBLE
                            }
                        }
                    }


                }
            }
        }




        binding.btnReboot.setOnClickListener{
            viewModel.rebootAGateway()
        }
        binding.btnUpdate.setOnClickListener{
            viewModel.updateAGateway()
        }
    }



}