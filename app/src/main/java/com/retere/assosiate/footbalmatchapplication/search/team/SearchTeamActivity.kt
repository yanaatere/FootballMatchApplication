package com.retere.assosiate.footbalmatchapplication.search.team

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.google.gson.Gson
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.api.ApiService
import com.retere.assosiate.footbalmatchapplication.detail.team.DetailTeamActivity
import com.retere.assosiate.footbalmatchapplication.search.team.model.SearchTeamsItem
import com.retere.assosiate.footbalmatchapplication.utils.invisible
import com.retere.assosiate.footbalmatchapplication.utils.visible
import kotlinx.android.synthetic.main.activity_search_team.*
import kotlinx.android.synthetic.main.layout_content_search_team.*
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), SearchTeamView {


    private var teams: MutableList<SearchTeamsItem> = mutableListOf()
    private var hasil: String? = null
    private var present: SearchTeamPresenter? = null
    private var adapterList: SearchTeamAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        rv_list_team_search.layoutManager = LinearLayoutManager(this)

        val request = ApiService()
        val gson = Gson()

        present = SearchTeamPresenter(this, request, gson)
        id_search_team.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                id_progressbar_content_search_team.visible()
                hasil = query!!
                if (query.length > 4) {
                    present?.getTeamListMatch(hasil!!)
                    id_progressbar_content_search_team.invisible()
                    //ini salah parameter teamsnya
                    adapterList = SearchTeamAdapter(teams) {
                        startActivity<DetailTeamActivity>("idTeam" to "${it.idTeam}")
                    }
                    rv_list_team_search.adapter = adapterList

                }
                return true
            }
        })

    }

    override fun showLoading() {
        id_progressbar_content_search_team.visible()
    }

    override fun hideLoading() {
        id_progressbar_content_search_team.invisible()
    }

    override fun showList(data: List<SearchTeamsItem>) {
        teams.clear()
        if (true) {
            teams.addAll(data)
        }
        adapterList?.notifyDataSetChanged()
    }

}