package com.retere.assosiate.footbalmatchapplication.api

import android.net.Uri
import com.retere.assosiate.footbalmatchapplication.BuildConfig

object TheSportDBApi {

    //Untuk Ngambil Image
    fun getImageTeam(league:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id",league)
            .build().toString()
    }

    //For Get Detail Team
    fun getEventDetail(event:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id",event)
            .build().toString()
    }

    //For Get Detail Next Match
    fun getNextMatch(idLeague:String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id",idLeague)
            .build().toString()
    }

    //For Get Last Next Match
    fun getLastMatch(idLeague: String?) : String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id",idLeague)
            .build().toString()
    }

    //Search Event By Name ex Arsenal VS Chelsea
    fun getMatchEvent(team:String?):String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e",team)
            .build().toString()
    }

    //Search all Team by League
    fun getListTeamByLeague(league:String?):String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l",league)
            .build().toString()
    }

    //Search Team
    fun getListTeam(league:String?):String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id",league)
            .build().toString()
    }

    //For Get all Player in a Team
    fun getListPlayer(team:String?):String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookup_all_players.php")
            .appendQueryParameter("id",team)
            .build().toString()
    }

    //For Get Team Player
    fun getPlayer(player:String?):String{
        return Uri.parse(BuildConfig.BASE_URL). buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupplayer.php")
            .appendQueryParameter("id",player)
            .build().toString()
    }

    //For Search for team by name
    fun getListTeamSearch(team: String?) : String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchteams.php")
            .appendQueryParameter("t",team)
            .build().toString()
    }

}