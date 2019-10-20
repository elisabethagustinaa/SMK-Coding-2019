package com.elisabethagustina.smkcoding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.elisabethagustina.smkcoding.R
import com.elisabethagustina.smkcoding.database.DatabaseContract
import com.elisabethagustina.smkcoding.database.database
import com.elisabethagustina.smkcoding.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class DetailMovieActivity : AppCompatActivity() {

    var isMovieFavorite : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie = intent.getParcelableExtra<ResultsItem>("movie")

        tv_title_movie.text = movie.title
        tv_rating_movie.text = "Rating : ${movie.popularity}"
        tv_description_movie.text = movie.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+movie?.posterPath).into(iv_poster_movie)

        //Cek dulu movie sudah favorit atau belum
        checkMovieFavorite(movie)

        button_favorit.onClick {
           // addMovieToFavorite(movie)
            if (isMovieFavorite) {
                deleteMovieFromFavorite(movie)
            } else {
                addMovieToFavorite(movie)
            }
        }
    }

    private fun deleteMovieFromFavorite(movie: ResultsItem?) {
        database.use {
            delete(
                ResultsItem.TABLE_FAVORITE,
                "${ResultsItem.COLUMN_TITLE} = {title}",
                "title" to movie?.title.toString()
            )
            toast("Movie dihapus dari daftar favorit")
            isMovieFavorite = false
            button_favorit.text = "Tambah Favorit"
        }
    }

    private fun addMovieToFavorite(movie: ResultsItem?) {
        database.use {
            insert(ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE to movie?.title,
                ResultsItem.COLUMN_POSTERPATH to movie?.posterPath,
                ResultsItem.COLUMN_RATING to movie?.popularity,
                ResultsItem.COLUMN_DESCRIPTION to movie?.overview)

            toast("Berhasil ditambah ke favorit")

            isMovieFavorite = true
            button_favorit.text = "Hapus Favorit"
        }
    }

    private fun checkMovieFavorite(movie: ResultsItem?) {
    //TODO Pengecekan film ini sudah favorite atau belum
        database.use {
            var isFavorite = select(ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE)
                .whereArgs(ResultsItem.COLUMN_TITLE+" ={title}",
                    "title" to movie?.title.toString())
            val dataMovie : DatabaseContract?
                = isFavorite.parseOpt(classParser<DatabaseContract>())

            Log.d("FAVORITEMOVIE", dataMovie.toString())

            if (dataMovie!=null){

                isMovieFavorite = true
                button_favorit.text = "Hapus Favorite"
            } else {
                isMovieFavorite = false
                button_favorit.text = "Tambah Favorit"
            }
        }
    }
}
