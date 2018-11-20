package com.retere.assosiate.footbalmatchapplication.tabAdapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.retere.assosiate.footbalmatchapplication.match.Fragment.LastMatchFragment
import com.retere.assosiate.footbalmatchapplication.match.Fragment.NextMatchFragment

class MatchTabAdapter(fragment: FragmentManager?) : FragmentStatePagerAdapter(fragment) {
    override fun getItem(p0: Int): Fragment {
        if(p0 == 0){
            return  LastMatchFragment()
        } else{
            return NextMatchFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}