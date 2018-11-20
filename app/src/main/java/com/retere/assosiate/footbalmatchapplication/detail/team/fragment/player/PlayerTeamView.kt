package com.retere.assosiate.footbalmatchapplication.detail.team.fragment.player

import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItem

interface PlayerTeamView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<PlayersItem>?)
}