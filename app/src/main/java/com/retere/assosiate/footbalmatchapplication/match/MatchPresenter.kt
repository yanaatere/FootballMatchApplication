package com.retere.assosiate.footbalmatchapplication.match

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(
    private val view: MatchView,
    private val apiService: ApiService,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {


    fun getLastMatch(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getLastMatch(league)),
                    DetailEventMatchResponse::class.java
                )
            }
            println("get data match : $data")
            view.showListMatch(data.await().events)
            view.hideLoading()
        }
    }

    fun getNextMatch(league: String) {
        view.showLoading()
        async(UI) {
            val data = bg {
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getNextMatch(league)),
                    DetailEventMatchResponse::class.java
                )
            }
            view.showListMatch(data.await().events)
            view.hideLoading()
        }
    }
}