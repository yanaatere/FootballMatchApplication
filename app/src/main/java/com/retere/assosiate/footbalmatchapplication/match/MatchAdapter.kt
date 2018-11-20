package com.retere.assosiate.footbalmatchapplication.match

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.model.DetailEventMatchModel
import kotlinx.android.synthetic.main.list_item.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat

class MatchAdapter(
    private val events: List<DetailEventMatchModel>,
    private val listener: (DetailEventMatchModel) -> Unit
) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MatchAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        return p0.bindItem(events[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateMatch: TextView = view.tv_match_date
        private val homeTeam: TextView = view.tv_home_team
        private val scoreHome: TextView? = view.tv_home_team_score
        private val awayTeam: TextView = view.tv_away_team
        private val scoreAway: TextView? = view.tv_away_team_score


        fun bindItem(event: DetailEventMatchModel, listener: (DetailEventMatchModel) -> Unit) {
            Log.e("Tag", "Adaptermatch :${event.strHomeTeam}")

            var dateFormat = SimpleDateFormat("yyyy-MM-dd")
            var date = dateFormat.parse(event.strDate)
            dateFormat = SimpleDateFormat("EEE, dd-MMMM-yyyy")
            dateMatch.text = dateFormat.format(date)
            homeTeam.text = event.strHomeTeam
            awayTeam.text = event.strAwayTeam
            scoreHome?.text = event.intHomeScore
            scoreAway?.text = event.intAwayScore
            itemView.onClick{ listener(event) }

        }
    }
}
