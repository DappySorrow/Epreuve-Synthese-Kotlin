package com.tp_apps.presentation.ui.tickets

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tp_apps.MainActivity
import com.tp_apps.R
import com.tp_apps.databinding.FragmentDetailTicketBinding
import com.tp_apps.databinding.FragmentTicketsBinding
import com.tp_apps.helpers.ColorHelper.ticketPriorityColor
import com.tp_apps.helpers.ColorHelper.ticketStatusColor
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.DateHelper
import com.tp_apps.helpers.Resource
import com.tp_apps.presentation.ui.capture.CaptureActivity



class DetailTicketFragment : Fragment(R.layout.fragment_detail_ticket){

    //private val ticketHref = "https://api.andromia.science/tickets/6261f1a4ae5754003a57ecf6"

    private val binding: FragmentDetailTicketBinding by viewBinding()
    private val viewModel: DetailTicketViewModel by viewModels {
        DetailTicketViewModel.Factory(args.href)
    }


    /* a utilisé plus tard quand le ticket sera set   */
    private val args : DetailTicketFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ticket.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    /* TODO : Changer le message */
                    Toast.makeText(requireContext(),it.throwable.message,Toast.LENGTH_SHORT).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is Resource.Success -> {

                    val ticket = it.data!!

                    binding.txvTicketId.text = ticket.ticketNumber
                    binding.txvTicketDate.text = DateHelper.formatISODate(ticket.createdDate)

                    binding.chipHaut.chipBackgroundColor = ticketPriorityColor(requireContext(),ticket.priority)
                    binding.chipBas.chipBackgroundColor = ticketStatusColor(requireContext(),ticket.status)
                    binding.chipHaut.text = ticket.priority
                    binding.chipBas.text = ticket.status



                    binding.txvNomPrenomTicket.text = "${ticket.customer.firstName} ${ticket.customer.lastName}"
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
            startActivity(CaptureActivity.newIntent(requireContext()))
        }

    }



}