package com.tp_apps.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val mac: String = "0",
    val SSID: String = "0",
    val version: String = "0",
    val kernel: List<String> = listOf(),
    val kernelRevision: String = "0"
)
