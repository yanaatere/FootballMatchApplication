package com.retere.assosiate.footbalmatchapplication.detail.match

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchResponse
import com.retere.assosiate.footbalmatchapplication.model.ImageTeamsModelResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PresenterDetailMatch(
    private val viewMatch: DetailMatchView,
    private val apiRepository: ApiService,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailTeam(leagueId: String) {
        viewMatch.showLoading()
        async(context.main) {
            val dataList = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getImageTeam(leagueId)),
                    DetailEventMatchResponse::class.java
                )
            }
            viewMatch.showList(dataList.await().events)
            viewMatch.hideLoading()
        }
    }

    fun getImgHomeTeam(leagueId: String) {
        viewMatch.showLoading()
        async(UI) {
            val dataList = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getImageTeam(leagueId)),
                    ImageTeamsModelResponse::class.java
                )
            }
            viewMatch.homeImg(dataList.await().teams)
            viewMatch.hideLoading()
        }
    }

    fun getImgAwayTeam(leagueId: String) {
        viewMatch.showLoading()
        async(UI) {
            val dataList = bg {
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getImageTeam(leagueId)),
                    ImageTeamsModelResponse::class.java
                )
            }
            viewMatch.awayImg(dataList.await().teams)
            viewMatch.hideLoading()
        }
    }
}