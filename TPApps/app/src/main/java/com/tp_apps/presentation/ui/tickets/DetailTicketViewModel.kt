package com.tp_apps.presentation.ui.tickets

import androidx.lifecycle.*
import com.tp_apps.data.repositories.GatewayRepository
import com.tp_apps.data.repositories.TicketRepository
import com.tp_apps.domain.models.Gateway
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailTicketViewModel(private val href:String) : ViewModel() {

    private val ticketRepository = TicketRepository()
    private val _ticket = MutableLiveData<Resource<Ticket>>()
    val ticket: LiveData<Resource<Ticket>> get() = _ticket

    private val gatewayRepository = GatewayRepository()
    private val _gateway = MutableLiveData<LoadingResource<List<Gateway>>>()
    val gateway: LiveData<LoadingResource<List<Gateway>>> get() = _gateway



    init {
        viewModelScope.launch {
            _ticket.value = ticketRepository.retrieveOne(href)
            gatewayRepository.retrieveAll().collect {
                _gateway.value = it
            }
        }


    }

    class Factory(private val href:String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }

    }

}