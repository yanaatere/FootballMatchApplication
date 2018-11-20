package com.retere.assosiate.footbalmatchapplication.search.match

import com.retere.assosiate.footbalmatchapplication.search.match.model.EventItem

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<EventItem>?)
}