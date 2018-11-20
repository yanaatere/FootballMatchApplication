package com.retere.assosiate.footbalmatchapplication.search.team

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.search.team.model.SearchTeamsItem
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchTeamAdapter(
    private val teams:List<SearchTeamsItem> ,
    private val listener: (SearchTeamsItem) -> Unit
) : RecyclerView.Adapter<SearchTeamAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchTeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item_team, p0, false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(p0: SearchTeamAdapter.ViewHolder, p1: Int) {
        p0.bindItem(teams[p1],listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(R.id.img_item_list_team)
        private val teamName: TextView = view.find(R.id.tv_name_item_list_team)

        fun bindItem(teams: SearchTeamsItem, listener: (SearchTeamsItem) -> Unit) {
            Picasso.get().load(teams.strTeamBadge).into(teamBadge)
            teamName.text = teams.strTeam
            itemView.onClick { listener(teams) }

        }
    }
}