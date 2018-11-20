package com.retere.assosiate.footbalmatchapplication.search.match

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.search.match.model.EventItem
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchMatchAdapter(
    private val event: List<EventItem>,
    private val listener: (EventItem) -> Unit
) : RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchMatchAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false))
    }

    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(p0: SearchMatchAdapter.ViewHolder, p1: Int) {
        p0.bindItem(event[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateLiga: TextView = view.find(R.id.tv_match_date)
        private val scoreHome: TextView? = view.find(R.id.tv_home_team_score)
        private val scoreAway: TextView? = view.find(R.id.tv_away_team_score)
        private val teamHomeLiga: TextView = view.find(R.id.tv_home_team)
        private val teamAwayLiga: TextView = view.find(R.id.tv_away_team)
        fun bindItem(event: EventItem, listener: (EventItem) -> Unit) {

            dateLiga.text = event.strDate
            teamHomeLiga.text = event.strHomeTeam
            teamAwayLiga.text = event.strAwayTeam
            scoreHome?.text = event.intHomeScore.toString()
            scoreAway?.text = event.intAwayScore.toString()
            itemView.onClick { listener(event) }

        }
    }
}
