package com.retere.assosiate.footbalmatchapplication.match

import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showListMatch(data: List<DetailEventMatchModel>?)
}