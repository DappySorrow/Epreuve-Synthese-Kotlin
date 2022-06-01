package com.tp_apps.presentation.ui.gateways

import android.graphics.Color
import android.os.Bundle
import android.provider.Contacts
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailGatewayBinding
import com.tp_apps.helpers.ColorHelper
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.Resource
import com.tp_apps.helpers.loadFromResource

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
                is Resource.Error -> {

                }
                is Resource.Success -> {
                    val gateway = it.data!!
                    binding.chipsStatus!!.text = gateway!!.connection.status

                    when (Constants.ConnectionStatus.valueOf(gateway.connection.status)) {
                        Constants.ConnectionStatus.Online -> {
                            with(binding) {
                                chipsStatus!!.text = getString(R.string.Online)
                                chipsStatus.chipBackgroundColor = ColorHelper.connectionStatusColor(binding.root.context,gateway.connection.status)
                                txvIP!!.text = gateway.connection.ip
                                txvSerialNumber!!.text = gateway.serialNumber
                                txvMac!!.text = gateway.config.mac
                                txvSSID!!.text = "SSID: ${gateway.config.SSID}"
                                txvPin!!.text = "PIN: ${gateway.pin}"
                                txvPing!!.text = "${gateway.connection.ping} ns"
                                txvDownload!!.text = "${String.format("%.3f", gateway.connection.download)} Ebps"
                                txvUpload!!.text = "${String.format("%.3f", gateway.connection.upload)} Ebps"
                                txvSignal!!.text = "${gateway.connection.signal} dBm "
                                imgElem1!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[0].lowercase()}")
                                imgElem2!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[1].lowercase()}")
                                imgElem3!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[2].lowercase()}")
                                imgElem4!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[3].lowercase()}")
                                imgElem5!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[4].lowercase()}")
                                txvKernelVersion!!.text = "Kernel revision ${gateway.revision} Version ${gateway.config.version}"


                                //TODO faire le putain de hash code en couleur

                                /*for (item in gateway.hash) {
                                    item.
                                }*/
                                /*for (item in gateway.hash) {
                                    if (item < gateway.hash[2]) {
                                        binding.txvHash0!!.text = gateway.hash
                                    }
                                    if (item >= gateway.hash[2] && item < gateway.hash[9]) {
                                        var x = gateway.hash[2].toString().rangeTo(gateway.hash[9].toString())
                                        var i : Int = Integer.parseInt(x)
                                        binding.txvHash1!!.setBackgroundColor("#${i}")
                                    }
                                }*/




                                btnReboot!!.visibility = View.VISIBLE
                                btnUpdate!!.visibility = View.VISIBLE
                            }
                        }
                        Constants.ConnectionStatus.Offline -> {
                            with(binding){
                                chipsStatus!!.text = getString(R.string.Offline)
                                chipsStatus.text = getString(R.string.Offline)
                                chipsStatus.chipBackgroundColor = ColorHelper.connectionStatusColor(binding.root.context,gateway.connection.status)
                                txvIP!!.text = gateway.connection.ip
                                txvSerialNumber!!.text = gateway.serialNumber
                                txvMac!!.text = gateway.config.mac
                                txvSSID!!.text = "SSID: ${gateway.config.SSID}"
                                txvPin!!.text = "PIN: ${gateway.pin}"
                                txvPing!!.visibility = View.INVISIBLE
                                txvDownload!!.visibility = View.INVISIBLE
                                txvUpload!!.visibility = View.INVISIBLE
                                txvSignal!!.visibility = View.INVISIBLE
                                txvNA!!.visibility = View.VISIBLE
                                txvNA!!.text = "N/A"
                                imgElem1!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[0].lowercase()}")
                                imgElem2!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[1].lowercase()}")
                                imgElem3!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[2].lowercase()}")
                                imgElem4!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[3].lowercase()}")
                                imgElem5!!.loadFromResource(binding.root.context, "element_${gateway.config.kernel[4].lowercase()}")
                                txvKernelVersion!!.text = "Kernel revision ${gateway.revision} Version ${gateway.config.version}"
                                btnReboot!!.visibility = View.VISIBLE
                                btnUpdate!!.visibility = View.VISIBLE
                                binding.btnReboot!!.visibility = View.INVISIBLE
                                binding.btnUpdate!!.visibility = View.INVISIBLE
                            }
                        }
                    }


                }
            }
        }




        binding.btnReboot!!.setOnClickListener{
            viewModel.rebootAGateway()
        }
        binding.btnUpdate!!.setOnClickListener{
            viewModel.updateAGateway()
        }
    }



}