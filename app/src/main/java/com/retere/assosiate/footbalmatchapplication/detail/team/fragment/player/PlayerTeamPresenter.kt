package com.retere.assosiate.footbalmatchapplication.detail.team.fragment.player

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItemResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerTeamPresenter(
    private val view: PlayerTeamView,
    private val apiService: ApiService,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getListPlayer(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiService.doRequest(TheSportDBApi.getListPlayer(league)),
                    PlayersItemResponse::class.java)
            }
            view.showList(data.await().players )
            view.hideLoading()
        }
    }
}