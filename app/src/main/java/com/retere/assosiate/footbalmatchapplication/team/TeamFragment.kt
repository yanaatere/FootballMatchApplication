package com.retere.assosiate.footbalmatchapplication.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.R.array.array_league
import com.retere.assosiate.footbalmatchapplication.team.model.TeamsItem
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.team.DetailTeamActivity
import com.retere.assosiate.footbalmatchapplication.search.team.SearchTeamActivity
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.layout_content_teams_league.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView {

    private var adapterList: TeamAdapter? = null
    private lateinit var progressBar: ProgressBar
    var teams: MutableList<TeamsItem> = mutableListOf()
    lateinit var spinnerLeague: Spinner
    lateinit var leagueName: String
    lateinit var imgSearchMatch: ImageView
    var presenter: TeamPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_teams_league, container, false)
        spinnerLeague = v.find(R.id.id_league_teams_spinner)
        imgSearchMatch = v.find(R.id.img_search_league_home)
        progressBar = v.id_progressBar_league_teams

        val spinnerItem = resources.getStringArray(array_league)
        val adapterSpinner = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        spinnerLeague.adapter = adapterSpinner

        imgSearchMatch.setOnClickListener { startActivity<SearchTeamActivity>() }


        val rvTeams: RecyclerView = v.findViewById(R.id.rv_league_teams)
        rvTeams.layoutManager = LinearLayoutManager(activity)
        val request = ApiService()
        val gson = Gson()

        presenter = TeamPresenter(this, request, gson)
        presenter?.getTeamList("English Premier League")
        adapterList = TeamAdapter(teams) {
            startActivity<DetailTeamActivity>("idTeam" to "${it.idTeam}")
        }

        rvTeams.adapter = adapterList


        spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                leagueName = spinnerLeague.selectedItem.toString()

                presenter?.getTeamList(leagueName)
                rvTeams.adapter = adapterList

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        return v
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showList(data: List<TeamsItem>?) {
        teams.clear()
        if (data != null) {
            teams.addAll(data)

        }
        adapterList?.notifyDataSetChanged()
    }

}