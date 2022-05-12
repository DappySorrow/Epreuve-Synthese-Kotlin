package com.tp_apps.presentation.ui.capture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp_apps.data.repositories.TicketRepository
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.launch

class CaptureViewModel : ViewModel(){

    private val ticketRepository = TicketRepository()

    private val _tickets = MutableLiveData<Resource<Ticket>>()
    val tickets: LiveData<Resource<Ticket>> get() = _tickets

    fun addCheckIn(href: String) {
        viewModelScope.launch {
            _tickets.value = ticketRepository.retrieveOne(href)
        }
    }
}