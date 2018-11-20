package com.retere.assosiate.footbalmatchapplication.detail.match

import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchModel
import com.retere.assosiate.footbalmatchapplication.model.ImageTeamsModel

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<DetailEventMatchModel>?)
    fun awayImg(eachTeam: List<ImageTeamsModel>?)
    fun homeImg(eachTeam: List<ImageTeamsModel>?)
}