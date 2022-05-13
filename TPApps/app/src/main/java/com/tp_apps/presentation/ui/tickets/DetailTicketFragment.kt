package com.tp_apps.presentation.ui.tickets

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailTicketBinding
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.ColorHelper.ticketPriorityColor
import com.tp_apps.helpers.ColorHelper.ticketStatusColor
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.DateHelper
import com.tp_apps.helpers.Resource
import com.tp_apps.helpers.notifyAllItemChanged
import com.tp_apps.presentation.adapters.GatewaysRecyclerViewAdapter
import com.tp_apps.presentation.ui.gateways.GatewaysFragmentDirections
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.ScanQRCode


class DetailTicketFragment : Fragment(R.layout.fragment_detail_ticket) {

    private val binding: FragmentDetailTicketBinding by viewBinding()
    private val viewModel: DetailTicketViewModel by viewModels {
        DetailTicketViewModel.Factory(args.href)
    }

    private lateinit var gatewaysRecyclerViewAdapter: GatewaysRecyclerViewAdapter

    private val args: DetailTicketFragmentArgs by navArgs()

    //----------------------------------------------------------------------------------------------------------

    private val quickieActivityLauncher =
        registerForActivityResult(ScanQRCode(), ::handleQuickieQRResult)

    //----------------------------------------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gatewaysRecyclerViewAdapter =
            GatewaysRecyclerViewAdapter(listOf(), ::onRecyclerViewClick)

        binding.rcvGatewaysTicket.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gatewaysRecyclerViewAdapter
        }


        viewModel.ticket.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    /* TODO : Changer le message */
                    Toast.makeText(requireContext(), it.throwable.message, Toast.LENGTH_SHORT)
                        .show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is Resource.Success -> {

                    val ticket = it.data!!

                    gatewaysRecyclerViewAdapter.gateways = it.data.customer.listGateway
                    gatewaysRecyclerViewAdapter.notifyAllItemChanged()

                    binding.txvTicketId.text = ticket.ticketNumber
                    binding.txvTicketDate.text = DateHelper.formatISODate(ticket.createdDate)

                    binding.chipHaut.chipBackgroundColor =
                        ticketPriorityColor(requireContext(), ticket.priority)
                    binding.chipBas.chipBackgroundColor =
                        ticketStatusColor(requireContext(), ticket.status)
                    binding.chipHaut.text = ticket.priority
                    binding.chipBas.text = ticket.status



                    binding.txvNomPrenomTicket.text =
                        "${ticket.customer.firstName} ${ticket.customer.lastName}"
                    binding.txvAddressTicket.text = ticket.customer.address
                    binding.txvVilleTicket.text = ticket.customer.city

                    Glide.with(binding.root.context)
                        .load(Constants.FLAG_API_URL.format(ticket.customer.country).lowercase())
                        .into(binding.imvPaysTicket)


                }
            }
        }


        /* Button pour ouvrir le scan du codeQR*/
        binding.buttonInstall.setOnClickListener() {
            quickieActivityLauncher.launch(null)
        }

    }

    //----------------------------------------------------------------------------------------------------------

    private fun handleQuickieQRResult(qrResult: QRResult) {
        when (qrResult) {
            is QRResult.QRSuccess -> {
                Toast.makeText(context, qrResult.content.rawValue, Toast.LENGTH_SHORT).show()
            }
            is QRResult.QRUserCanceled -> {
                Toast.makeText(context, "Tentative de scan annulée", Toast.LENGTH_SHORT).show()
            }
            is QRResult.QRMissingPermission -> {
                Toast.makeText(context, "Manque la permission de camera", Toast.LENGTH_SHORT).show()
            }
            is QRResult.QRError -> {
                Toast.makeText(context, qrResult.exception.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //----------------------------------------------------------------------------------------------------------

    private fun onRecyclerViewClick(gateway: Gateway) {
        Toast.makeText(requireContext(), gateway.serialNumber, Toast.LENGTH_LONG).show()
        val direction =
            GatewaysFragmentDirections.actionNavigationGatewaysToDetailGatewayFragment(gateway.href)
        findNavController().navigate(direction)
    }

}