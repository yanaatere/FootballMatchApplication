package com.retere.assosiate.footbalmatchapplication.tabAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.retere.assosiate.footbalmatchapplication.detail.team.fragment.OverviewTeamFragment
import com.retere.assosiate.footbalmatchapplication.detail.team.fragment.player.PlayerTeamFragment

class TeamTabAdapter(fragmentManager: FragmentManager?,val idTeam:String):FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            println("load overview")
            return OverviewTeamFragment.newInstance(idTeam,"")
        } else {
            return PlayerTeamFragment.newInstance(idTeam,"")
        }
    }

    override fun getCount(): Int {

        return 2
    }
}