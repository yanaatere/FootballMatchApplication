package com.retere.assosiate.footbalmatchapplication

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.retere.assosiate.footbalmatchapplication.favorite.fragment.FavoriteFragment
import com.retere.assosiate.footbalmatchapplication.match.Fragment.LastMatchFragment
import com.retere.assosiate.footbalmatchapplication.match.Fragment.MatchFragment
import com.retere.assosiate.footbalmatchapplication.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showFragment(MatchFragment())

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.match -> {
                    showFragment(LastMatchFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.team -> {
                    showFragment(TeamFragment())
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                   showFragment(FavoriteFragment())
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })

    }

    private fun showFragment(fragment: Fragment?) {
        val mgr = supportFragmentManager
        mgr.beginTransaction().replace(R.id.container, fragment!!).commit()

    }

}
