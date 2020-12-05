package com.bobbyprabowo.kmmsport.shared.domain

import com.bobbyprabowo.kmmsport.shared.schema.Team
import kotlinx.coroutines.flow.Flow

interface SearchTeam {

    fun execute(keyword: String): Flow<Team>

    fun execute(keyword: String, success: () -> Team, failed: () -> Throwable)
}