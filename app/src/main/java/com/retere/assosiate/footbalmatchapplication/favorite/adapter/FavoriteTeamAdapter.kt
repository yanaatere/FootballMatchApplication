package com.retere.assosiate.footbalmatchapplication.favorite.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteTeamModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_team.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamAdapter(
    private val favoriteTeam: List<FavoriteTeamModel>,
    val listener: (FavoriteTeamModel) -> Unit
) : RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_team, p0, false))
    }

    override fun getItemCount(): Int = favoriteTeam.size

    override fun onBindViewHolder(p0: FavoriteTeamAdapter.ViewHolder, p1: Int) {
        p0.bindItem(favoriteTeam[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge = view.img_item_list_team
        private val teamName = view.tv_name_item_list_team

        fun bindItem(favTeam: FavoriteTeamModel, listener: (FavoriteTeamModel) -> Unit) {
            Picasso.get().load(favTeam.teamImage).into(teamBadge)
            teamName.text = favTeam.teamName
            itemView.onClick { listener(favTeam) }
        }
    }
}