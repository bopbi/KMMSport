package com.bobbyprabowo.kmmsport.shared.schema

import kotlinx.serialization.Serializable

@Serializable
data class TeamSearchResult(val teams: List<Team>)