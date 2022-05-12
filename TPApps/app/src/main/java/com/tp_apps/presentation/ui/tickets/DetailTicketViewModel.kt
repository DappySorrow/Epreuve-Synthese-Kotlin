package com.tp_apps.presentation.ui.tickets

import androidx.lifecycle.*
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
            _ticket.value = ticketRepository.retrieveOne(href)
        }
    }

    class Factory(private val href:String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }

    }

}