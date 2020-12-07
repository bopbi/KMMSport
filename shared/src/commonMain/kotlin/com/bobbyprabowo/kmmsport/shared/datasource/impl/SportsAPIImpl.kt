package com.bobbyprabowo.kmmsport.shared.datasource.impl

import com.badoo.reaktive.coroutinesinterop.singleFromCoroutine
import com.badoo.reaktive.scheduler.ioScheduler
import com.badoo.reaktive.scheduler.mainScheduler
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.SingleWrapper
import com.badoo.reaktive.single.observeOn
import com.badoo.reaktive.single.subscribeOn
import com.badoo.reaktive.single.wrap
import com.bobbyprabowo.kmmsport.shared.datasource.SportsAPI
import com.bobbyprabowo.kmmsport.shared.schema.TeamSearchResult
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class SportsAPIImpl : SportsAPI {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override fun searchTeam(keyword: String): Single<TeamSearchResult> {
        return singleFromCoroutine {
            httpClient.get("https://www.thesportsdb.com/api/v1/json/1/searchteams.php?t=${keyword}")
        }
    }

    override fun searchTeamWrap(keyword: String): SingleWrapper<TeamSearchResult> {
        return searchTeam(keyword)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
				.wrap()
    }
}