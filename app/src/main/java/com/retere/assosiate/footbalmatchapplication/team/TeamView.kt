package com.retere.assosiate.footbalmatchapplication.team

import com.retere.assosiate.footbalmatchapplication.team.model.TeamsItem

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<TeamsItem>?)
}