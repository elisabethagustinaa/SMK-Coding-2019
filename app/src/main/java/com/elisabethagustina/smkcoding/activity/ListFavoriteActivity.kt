package com.elisabethagustina.smkcoding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elisabethagustina.smkcoding.R
import com.elisabethagustina.smkcoding.database.DatabaseContract
import com.elisabethagustina.smkcoding.database.database
import com.elisabethagustina.smkcoding.model.ResultsItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class ListFavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_favorite)

        selectFavoriteListFromSqlite()
    }

    private fun selectFavoriteListFromSqlite() {
        database.use {
            val selectData = select(ResultsItem.TABLE_FAVORITE)
            val list : MutableList<DatabaseContract> =
                selectData.parseList(classParser<DatabaseContract>()) as MutableList
            val arrayListMovie = list as ArrayList<DatabaseContract>
            Log.d("LISTFAVORITE", arrayListMovie.toString())
        }
    }
}
