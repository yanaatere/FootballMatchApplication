package com.retere.assosiate.footbalmatchapplication.detail.team

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.detail.team.model.TeamItemResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenterTeam(
    private val view: DetailTeamView,
    private val apiService: ApiService,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeam(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getListTeam(league)),
                    TeamItemResponse::class.java
                )
            }
            println("get data team : $data")
            view.showList(data.await().teams)
            view.hideLoading()
        }
    }
}