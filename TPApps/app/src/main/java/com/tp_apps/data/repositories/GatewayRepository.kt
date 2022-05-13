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
import com.tp_apps.helpers.LoadingResource
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GatewayRepository {

    private val gatewayDataSource = GatewayDataSource()

    fun retrieveAll(): Flow<LoadingResource<List<Gateway>>> {
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

    //qrResult.content.rawValue

    // {"serialNumber":"d93ccd92442c0929",
    // "revision":"UA-500947-71",
    // "pin":"ae98e05b",
    // "hash":"a7c0067add454e877e448de8d1696afc631b7323b53cc7b16781274dab055f63"}
    // customer
    // connection
    // config
    // href ???
    //TODO

    fun postOne(gatewayInfosJSON : String) : Flow<Resource<Gateway>>{

        val gatewayInfos = gatewayInfosJSON.splitToSequence('"').toList()

        val serialNumber = gatewayInfos.elementAt(3)
        val revision = gatewayInfos.elementAt(7)
        val pin = gatewayInfos.elementAt(11)
        val hash = gatewayInfos.elementAt(15)

        val gateway = Gateway("",serialNumber, revision, pin, hash)

        return flow {
            while (true) {
                try {
                    emit(gatewayDataSource.postOne(gateway))
                } catch (ex: Exception) {
                    emit(Resource.Error(ex, ex.message))
                }
            }
        }

    }

}