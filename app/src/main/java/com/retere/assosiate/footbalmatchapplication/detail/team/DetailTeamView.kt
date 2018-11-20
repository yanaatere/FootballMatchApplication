package com.retere.assosiate.footbalmatchapplication.detail.team

import com.retere.assosiate.footbalmatchapplication.detail.team.model.TeamsItem

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showList(data:List<TeamsItem>)
}