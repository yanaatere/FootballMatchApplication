package com.retere.assosiate.footbalmatchapplication.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*


class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
         // Here you create tables
        /* db.createTable(Favorite.TABLE_NAME, true,
             Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
             Favorite.MATCH_ID to TEXT + UNIQUE,
             Favorite.DATE to TEXT,
             Favorite.HOME_TEAM to TEXT,
             Favorite.HOME_SCORE to TEXT,
             Favorite.AWAY_TEAM to TEXT,
             Favorite.AWAY_SCORE to TEXT)*/
     }

     override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
         // Here you can upgrade tables, as usual
       /*  db.dropTable(Favorite.TABLE_NAME, true)*/
     }
 }

    // Access property for Context
    val Context.database: MyDatabaseOpenHelper
        get() = MyDatabaseOpenHelper.getInstance(applicationContext)