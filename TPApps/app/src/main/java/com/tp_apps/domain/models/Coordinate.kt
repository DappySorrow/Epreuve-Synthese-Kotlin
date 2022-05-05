package com.tp_apps.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Coordinate(
    val latitude: Float,
    val longitude: Float
)
