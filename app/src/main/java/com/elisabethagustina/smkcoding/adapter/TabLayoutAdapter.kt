package com.elisabethagustina.smkcoding.adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.elisabethagustina.smkcoding.MovieFragment


class TabLayoutAdapter(fm: FragmentManager, context: Context)
    : FragmentStatePagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
        val movieFragment = MovieFragment()
        // sudah bisa masuk ke dalam fragmern tetapi belum membawa apa-apa
        val bundle = Bundle()
        when(position){ //mengisi bundle berdasarkan posisinya
            0 -> bundle.putString("KEY", "MOVIE")
            1 -> bundle.putString("KEY", "TV")
            // baru menyiapkan barang yg akan dibawa


        }

        movieFragment.arguments = bundle

        return movieFragment

    }

    override fun getCount(): Int {
        return 2 //dipastikan lagi berapa barang yg akan dibawa
        // adapter itu sebagai penghubung antara tampilan dengan konten yang akan diisi
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MOVIE"
            1 -> "TV"
            else -> "TIDAK ADA"
        }
    }
}