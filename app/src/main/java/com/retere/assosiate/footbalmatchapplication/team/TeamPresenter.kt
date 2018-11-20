package com.retere.assosiate.footbalmatchapplication.team

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.team.model.LeagueTeamsItemRespon
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(
    private val view: TeamView,
    private val apiService: ApiService,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamList(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getListTeam(league)),
                    LeagueTeamsItemRespon::class.java
                )
            }
            println("get data team : $data")
            view.showList(data.await().teams)
            view.hideLoading()
        }
    }
}