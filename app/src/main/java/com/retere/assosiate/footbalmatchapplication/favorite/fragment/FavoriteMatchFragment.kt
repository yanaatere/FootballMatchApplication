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
import com.retere.assosiate.footbalmatchapplication.detail.match.DetailMatchActivity
import com.retere.assosiate.footbalmatchapplication.favorite.adapter.FavoriteMatchAdapter
import com.retere.assosiate.footbalmatchapplication.favorite.model.FavoriteMatchModel
import kotlinx.android.synthetic.main.fragment_favorite_team.view.*
import kotlinx.android.synthetic.main.fragment_layout_favorite.view.*
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchFragment : Fragment() {
    private var matchFavorite: MutableList<FavoriteMatchModel> = mutableListOf()
    private lateinit var favoriteMatchAdapter: FavoriteMatchAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_layout_favorite, container, false)
        recyclerView = view.rv_favorite_match
        swipeRefreshLayout = view.id_swipe_team_layout
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteMatchAdapter = FavoriteMatchAdapter(matchFavorite) {
            startActivity<DetailMatchActivity>("idTeam" to "${it.matchId}")
        }

        recyclerView.adapter = favoriteMatchAdapter
        showFavorite()

        swipeRefreshLayout.onRefresh {
            matchFavorite.clear()
            showFavorite()
        }
    }

    private fun showFavorite() {
        swipeRefreshLayout.isRefreshing = false
        activity?.database?.use {
            val result = select(FavoriteMatchModel.TABLE_FAVORITE_MATCH)
            val favorite =
                result.parseList(parser = rowParser { id: Long?, matchId: String?, matchDate: String?, homeName: String?, awayName: String?, scoreHome: String?, scoreAway: String? ->
                    FavoriteMatchModel(
                        id,
                        matchId,
                        matchDate,
                        homeName,
                        awayName,
                        scoreHome,
                        scoreAway
                    )

                })
        }
    }
}