package com.tp_apps.data.repositories

import com.tp_apps.data.datasources.TicketDataSource
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.LoadingResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicketRepository {

    private val ticketDataSource = TicketDataSource()

    suspend fun retrieveAll(): Flow<LoadingResource<List<Ticket>>> {
        return flow {
            while(true) {
                try {
                    emit(LoadingResource.Loading())
                    //delay(1500)
                    emit(LoadingResource.Success(ticketDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.REFRESH_TICKET_DELAY)
            }
        }
    }

    /*
    return try {
            LoadingResource.Loading()
            LoadingResource.Success(ticketDataSource.retrieveALL())
        } catch (ex: Exception) {
            LoadingResource.Error(ex)
        }
    * */

}