package com.elisabethagustina.smkcoding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elisabethagustina.smkcoding.R
import com.elisabethagustina.smkcoding.adapter.TabLayoutAdapter
import kotlinx.android.synthetic.main.activity_list_movie.*

class ListMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)

        viewpager.adapter =
            TabLayoutAdapter(supportFragmentManager, this)
        tabLayout.setupWithViewPager(viewpager)

    }
}
