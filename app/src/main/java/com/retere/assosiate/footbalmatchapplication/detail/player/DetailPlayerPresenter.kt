package com.retere.assosiate.footbalmatchapplication.detail.player

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItemDetailResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter(
    private val viewMatch: DetailPlayerView,
    private val apiRepository: ApiService,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailPlayer(leagueId: String) {
        viewMatch.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getPlayer(leagueId)),
                    PlayersItemDetailResponse::class.java
                )
            }
            viewMatch.showList(data.await().players)
            viewMatch.hideLoading()
        }
    }
}