package com.tp_apps.data.repositories

import com.tp_apps.data.datasources.TicketDataSource
import com.tp_apps.domain.models.Ticket
import com.tp_apps.helpers.LoadingResource

class TicketRepository {

    private val ticketDataSource = TicketDataSource()

    suspend fun retrieveAll(): LoadingResource<List<Ticket>> {

        return try {
            LoadingResource.Loading()
            LoadingResource.Success(ticketDataSource.retrieveALL())
        } catch (ex: Exception) {
            LoadingResource.Error(ex)
        }
    }
}