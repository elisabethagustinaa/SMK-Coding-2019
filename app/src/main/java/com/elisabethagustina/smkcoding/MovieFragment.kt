package com.elisabethagustina.smkcoding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.elisabethagustina.smkcoding.adapter.MovieAdapter
import com.elisabethagustina.smkcoding.connection.ConfigRetrofit
import com.elisabethagustina.smkcoding.connection.MovieInterface
import com.elisabethagustina.smkcoding.model.MovieModel
import com.hanifabdullah21.smkcoding.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.view.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_movie, container, false)

        getListMovie()

//        val list = Movie.listMovie as ArrayList<MovieModel>
//        val layoutmanager = LinearLayoutManager(activity)
//        val adapter = MovieAdapter(list,activity!!.applicationContext)
//
//        return rootView.rv_movie.apply{
//            layoutManager = layoutmanager
//            setAdapter(adapter)
//        }

        return rootView
    }

        private fun getListMovie() {
            val movieNowPlaying = ConfigRetrofit.retrofitConfig()
                .create(MovieInterface::class.java)
                .getListMovieNowPlaying(
                    "f170aa9657a8414b7c736348323c7af6")

            movieNowPlaying
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    //menerima respon yang berhasil
                    val list = response.results
                    val layoutmanager = LinearLayoutManager(activity)
                    val adapter = MovieAdapter(list, activity!!.applicationContext)

                    rootView.rv_movie.apply {
                        layoutManager = layoutmanager
                        setAdapter(adapter)
                    }
                },{
                    //menerima respon yang gagal
                })
        }

}
