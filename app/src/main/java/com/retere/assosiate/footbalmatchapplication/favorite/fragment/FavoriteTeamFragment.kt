package com.retere.assosiate.footbalmatchapplication.favorite.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.database.database
import com.retere.assosiate.footbalmatchapplication.detail.team.DetailTeamActivity
import com.retere.assosiate.footbalmatchapplication.favorite.adapter.FavoriteTeamAdapter
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteTeamModel
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeamFragment : Fragment() {
    private var teamFavorite: MutableList<FavoriteTeamModel> = mutableListOf()
    private lateinit var favoriteTeamAdapter: FavoriteTeamAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorite_team, container, false)
        recyclerView = view.rv_favorite_team_in_fragment
        swipeRefreshLayout = view.id_swipe_team_layout
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteTeamAdapter = FavoriteTeamAdapter(teamFavorite) {
            startActivity<DetailTeamActivity>("idTeam" to "${it.idTeam}")
        }

        recyclerView.adapter = favoriteTeamAdapter
        showFavorite()

        swipeRefreshLayout.onRefresh {
            teamFavorite.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        swipeRefreshLayout.isRefreshing = false
        activity?.database?.use {
            val result = select(FavoriteTeamModel.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(parser = rowParser { id: Long?, teamId: String?, teamName: String?,
                                                                 teamImage: String?
                ->
                FavoriteTeamModel(id, teamId, teamName, teamName)
            })
            teamFavorite.addAll(favorite)
            favoriteTeamAdapter.notifyDataSetChanged()
        }
    }
}