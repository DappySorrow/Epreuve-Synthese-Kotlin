package com.tp_apps.data.datasources

import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.tp_apps.domain.models.Config
import com.tp_apps.domain.models.Connection
import com.tp_apps.domain.models.Customer
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.Constants
import com.tp_apps.helpers.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONObject

class GatewayDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieveAll() : List<Gateway> {

        return withContext(Dispatchers.IO) {
            val (_, _, result) = Constants.BaseURL.GATEWAYS.httpGet().responseJson()
            when(result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString(result.value.content)

                }
                is Result.Failure -> {
                    throw result.error.exception

                }
            }
        }
    }

    suspend fun postOne(gateway: Gateway): Resource<Gateway> {
        return withContext(Dispatchers.IO) {
            val body = Json.encodeToString(gateway)
            val (_, _, result) = Constants.BaseURL.CUSTOMERS.httpPost().jsonBody(body).responseJson()

            when (result) {
                is Result.Success -> {
                    return@withContext Resource.Success(Json.decodeFromString<Gateway>(result.value.content))
                }
                is Result.Failure -> {
                    return@withContext Resource.Error(result.error.exception)
                }
            }
        }
    }
}