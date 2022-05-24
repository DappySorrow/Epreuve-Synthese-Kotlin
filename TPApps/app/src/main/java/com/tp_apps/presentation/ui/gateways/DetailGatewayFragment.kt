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

class DetailGatewayFragment : Fragment(R.layout.fragment_detail_gateway) {

    private val binding: FragmentDetailGatewayBinding by viewBinding()
    private val viewModel: DetailGatewayViewModel by viewModels{
        DetailGatewayViewModel.Factory(args.href)
    }

    private val args : DetailGatewayFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnReboot.setOnClickListener{
            viewModel.rebootAGateway()
        }
        binding.btnUpdate.setOnClickListener{
            viewModel.updateAGateway()
        }


    }



}