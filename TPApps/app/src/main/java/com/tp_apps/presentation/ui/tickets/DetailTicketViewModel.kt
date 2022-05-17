package com.tp_apps.presentation.ui.tickets

import android.util.Log
import androidx.lifecycle.*
import com.tp_apps.data.repositories.GatewayRepository
import com.tp_apps.data.repositories.TicketRepository
import com.tp_apps.domain.models.Borne
import com.tp_apps.domain.models.Gateway
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailTicketViewModel(private val href:String) : ViewModel() {

    private val ticketRepository = TicketRepository()
    private val _ticket = MutableLiveData<Resource<Ticket>>()
    val ticket: LiveData<Resource<Ticket>> get() = _ticket

    private val gatewayRepository = GatewayRepository()

    private val _gateways = MutableLiveData<LoadingResource<List<Gateway>>>()
    val gateways: LiveData<LoadingResource<List<Gateway>>> get() = _gateways

    private val _gateway =  MutableLiveData<Resource<Gateway>>()
    val gateway: LiveData<Resource<Gateway>> get() = _gateway

//-------------------------------------------------------------------------------------------------

    fun addGateway(rawValue: String, id: String) {
        val gatewayInfos = rawValue.splitToSequence('"').toList()
        val serialNumber = gatewayInfos.elementAt(3)
        val revision = gatewayInfos.elementAt(7)
        val pin = gatewayInfos.elementAt(11)
        val hash = gatewayInfos.elementAt(15)

        val borne = Borne(serialNumber, revision, pin, hash)

        viewModelScope.launch {
            Log.e("post", "DetailTicketViewModel")
            _gateway.value = gatewayRepository.postOne(borne, id)
        }
    }

//-------------------------------------------------------------------------------------------------

    init {
        viewModelScope.launch {
            _ticket.value = ticketRepository.retrieveOne(href)
            gatewayRepository.retrieveAll().collect {
                _gateways.value = it
            }
        }
    }

    class Factory(private val href:String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(String::class.java).newInstance(href)
        }

    }

}