package com.bobbyprabowo.kmmsport.shared.datasource.impl

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.single.Single
import com.bobbyprabowo.kmmsport.shared.datasource.SportsAPI
import com.bobbyprabowo.kmmsport.shared.schema.Team
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class SportsAPIImpl : SportsAPI {

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }
    }

    override fun searchTeam(keyword: String): Single<TeamSearchResult> {
        return singleFromCoroutine {
            httpClient.get("https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=${keyword}")
        }
    }
}