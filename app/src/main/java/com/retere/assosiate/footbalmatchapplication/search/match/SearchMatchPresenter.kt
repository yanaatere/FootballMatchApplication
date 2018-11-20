package com.retere.assosiate.footbalmatchapplication.search.match

import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.api.TheSportDBApi
import com.retere.assosiate.footbalmatchapplication.search.match.model.EventItemResponse
import com.retere.assosiate.footbalmatchapplication.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view: SearchMatchView,
                           private val apiService: ApiService,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamListMatch(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(
                    apiService.doRequest(TheSportDBApi.getMatchEvent(league)),
                    EventItemResponse::class.java
                )
            }
            // view.showList(data.await().event)
            view.showList(data.await().event)
            view.hideLoading()
        }
    }
}