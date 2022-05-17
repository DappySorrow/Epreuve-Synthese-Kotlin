package com.tp_apps.data.repositories

import android.util.Log
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.FuelJson
import com.github.kittinunf.fuel.json.jsonDeserializer
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.success
import com.tp_apps.data.datasources.GatewayDataSource
import com.tp_apps.domain.models.Borne
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GatewayRepository {

    private val json = Json { ignoreUnknownKeys = true }

    private val gatewayDataSource = GatewayDataSource()

    suspend fun retrieveAll(): Flow<LoadingResource<List<Gateway>>> {
        return flow {
            while (true) {
                try {
                    emit(LoadingResource.Loading())
                    delay(Constants.GATEWAY_LOADING)
                    emit(LoadingResource.Success(gatewayDataSource.retrieveAll()))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.REFRESH_GATEWAY_DELAY)
            }
        }
    }

    suspend fun postOne(borne: Borne, id : String): Resource<Gateway> {
        return withContext(Dispatchers.IO) {
            val body = json.encodeToString(borne)
            val (_, _, result) = "${Constants.BaseURL.CUSTOMERS}/${id}/gateways".httpPost().jsonBody(body).responseJson()
            when (result) {
                is Result.Success -> {
                    return@withContext Resource.Success(json.decodeFromString<Gateway>(result.value.content))

                }
                is Result.Failure -> {
                    return@withContext Resource.Error(result.error.exception)
                }
            }
        }
    }

    suspend fun retrieveCustomerGateways(href: String) : Flow<LoadingResource<List<Gateway>>>{
        return flow {
            while (true) {
                try {
                    emit(LoadingResource.Loading())
                    delay(Constants.GATEWAY_LOADING)
                    emit(LoadingResource.Success(gatewayDataSource.retrieveCustomerGateways(href)))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.REFRESH_GATEWAY_DELAY)
            }
        }
    }


}