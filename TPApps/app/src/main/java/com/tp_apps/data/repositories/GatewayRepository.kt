package com.tp_apps.data.repositories

import com.github.kittinunf.result.Result
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.success
import com.tp_apps.data.datasources.GatewayDataSource
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GatewayRepository {

    private val gatewayDataSource = GatewayDataSource()

    suspend fun retrieveAll(): Flow<LoadingResource<List<Gateway>>> {
        return flow {
            while (true) {
                try {
                    emit(LoadingResource.Loading())
                    emit(LoadingResource.Success(gatewayDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(60000)
            }
        }
    }
}