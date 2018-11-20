package com.retere.assosiate.footbalmatchapplication.search.team

import com.retere.assosiate.footbalmatchapplication.search.team.model.SearchTeamsItem


interface SearchTeamView {
    fun showLoading()
    fun hideLoading()
    fun showList(data:List<SearchTeamsItem>)
}