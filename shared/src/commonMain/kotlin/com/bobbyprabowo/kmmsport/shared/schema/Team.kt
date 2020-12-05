package com.bobbyprabowo.kmmsport.shared.schema

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val idTeam: String,
    val strTeam: String
)