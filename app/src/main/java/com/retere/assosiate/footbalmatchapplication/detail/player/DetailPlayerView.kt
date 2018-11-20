package com.retere.assosiate.footbalmatchapplication.detail.player

import com.retere.assosiate.footbalmatchapplication.detail.team.model.PlayersItemDetail

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<PlayersItemDetail>?)
}