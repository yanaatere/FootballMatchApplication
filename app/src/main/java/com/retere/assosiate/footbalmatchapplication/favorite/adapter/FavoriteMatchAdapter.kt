package com.retere.assosiate.footbalmatchapplication.favorite.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteMatchModel
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find


class FavoriteMatchAdapter(
    private val favoriteMatch: List<FavoriteMatchModel>,
    val listener: (FavoriteMatchModel) -> Unit
) :
    RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteMatchAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false))
    }

    override fun getItemCount(): Int = favoriteMatch.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(favoriteMatch[p1],listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateTeamFavorite = view.tv_match_date
        private val teamHomeFavorite = view.tv_home_team
        private val teamAwayFavorite= view.tv_away_team
        private val scoreHomeFavorite = view.tv_home_team_score
        private val scoreAwayFavorite = view.tv_away_team_score

        fun bindItem(favMatch: FavoriteMatchModel, listener: (FavoriteMatchModel) -> Unit) {
            dateTeamFavorite.text = favMatch.matchDate
            teamHomeFavorite.text = favMatch.strHomeName
            teamAwayFavorite.text = favMatch.strAwayName
            scoreHomeFavorite?.text = favMatch.strScoreHome
            scoreAwayFavorite?.text = favMatch.strScoreAway
        }

    }
}