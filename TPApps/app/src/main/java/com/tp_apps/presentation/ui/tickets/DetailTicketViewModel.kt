package com.tp_apps.presentation.ui.tickets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp_apps.data.repositories.TicketRepository
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.launch

class DetailTicketViewModel(private val href:String) : ViewModel() {

    private val ticketRepository = TicketRepository()

    private val _ticket = MutableLiveData<Resource<Ticket>>()
    val ticket: LiveData<Resource<Ticket>> get() = _ticket

    init {
        viewModelScope.launch {
           /* _ticket.value = ticketRepository.retrieveOne(href)*/
        }
    }

}