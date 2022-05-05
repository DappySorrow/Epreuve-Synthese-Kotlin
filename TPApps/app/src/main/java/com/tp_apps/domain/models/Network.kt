package com.tp_apps.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Network(
    val nextReboot: String,
    val node: List<NetworkNode>,
    val updateDate: String
)
