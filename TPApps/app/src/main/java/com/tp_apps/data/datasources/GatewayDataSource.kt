package com.tp_apps.data.datasources

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.tp_apps.domain.models.Gateway
import com.tp_apps.helpers.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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

    suspend fun retrieve(href: String) : Gateway {
        return withContext(Dispatchers.IO) {
            val (_, _, result) = href.httpGet().responseJson()
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
}