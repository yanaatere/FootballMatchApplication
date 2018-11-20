package com.retere.assosiate.footbalmatchapplication.tabAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.retere.assosiate.footbalmatchapplication.favorite.fragment.FavoriteMatchFragment
import com.retere.assosiate.footbalmatchapplication.favorite.fragment.FavoriteTeamFragment

class FavoriteTabAdapter (fragmentManager: FragmentManager?):FragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        if (position == 0) {

            return FavoriteMatchFragment()
        } else {
            return FavoriteTeamFragment()
        }
    }

    override fun getCount(): Int {

        return 2
    }
}