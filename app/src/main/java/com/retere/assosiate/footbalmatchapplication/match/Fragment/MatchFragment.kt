package com.retere.assosiate.footbalmatchapplication.match.Fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.retere.assosiate.footbalmatchapplication.R
import com.retere.assosiate.footbalmatchapplication.search.match.SearchMatchActivity
import com.retere.assosiate.footbalmatchapplication.tabAdapter.MatchTabAdapter
import kotlinx.android.synthetic.main.fragment_home_team_layout.view.*
import kotlinx.android.synthetic.main.toolbar_home_match.view.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment : Fragment() {

    private lateinit var idTabsMatch: TabLayout
    private lateinit var idPagerMatch: ViewPager
    private lateinit var idImgSearchMatch: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_home_team_layout, container, false)
        idTabsMatch = v.id_tabs_home_match
        idPagerMatch = v.id_pager_home_teams
        idImgSearchMatch = v.search_match
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        idImgSearchMatch.setOnClickListener {
            startActivity<SearchMatchActivity>()
        }
        idTabsMatch.addTab(idTabsMatch.newTab().setText("Last Match"))
        idTabsMatch.addTab(idTabsMatch.newTab().setText("Next Match"))
        val tabAdapter = MatchTabAdapter(activity?.supportFragmentManager)
        idPagerMatch.adapter = tabAdapter
        idPagerMatch.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(idTabsMatch))
        idTabsMatch.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab?) {

                if (tab != null) {
                    idPagerMatch.currentItem = tab.position
                }
            }
        })
    }
}