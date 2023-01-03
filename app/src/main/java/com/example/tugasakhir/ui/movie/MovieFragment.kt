package com.project.proyek_akhir_mobile_programming.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugasakhir.databinding.FragmentMovieBinding
import com.project.proyek_akhir_mobile_programming.data.client.ApiClient
import com.project.proyek_akhir_mobile_programming.data.model.ListResponse
import com.project.proyek_akhir_mobile_programming.data.model.MovieResponse
import com.example.tugasakhir.ui.detail.DetailActivity
import com.example.tugasakhir.ui.movie.MovieAdapter
import com.project.proyek_akhir_mobile_programming.utils.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private lateinit var binding: FragmentMovieBinding

    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        if (_binding == null){
            _binding = FragmentMovieBinding.inflate(inflater, container, false)
            binding = _binding as FragmentMovieBinding
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // init
        adapter = MovieAdapter().apply {
            onClick { data ->
                Intent(activity, DetailActivity::class.java).also { intent ->
                    intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.data[0])
                    intent.putExtra(DetailActivity.EXTRA_DATA, data)
                    startActivity(intent)
                }
            }
        }

        getMovies()
    }

    private fun getMovies() {

        showLoading(true)

        ApiClient.instance.getMovies()
            .enqueue(object : Callback<ListResponse<MovieResponse>>{
                override fun onResponse(
                    call: Call<ListResponse<MovieResponse>>,
                    response: Response<ListResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful){
                        binding.apply {
                            adapter.movies = response.body()?.results as MutableList<MovieResponse>
                            rvMovie.adapter = adapter
                            rvMovie.setHasFixedSize(true)
                        }
                        showLoading(false)
                    }else{
                        activity?.showToast(response.message().toString())
                    }
                }

                override fun onFailure(call: Call<ListResponse<MovieResponse>>, t: Throwable) {
                    activity?.showToast(t.message.toString())
                    showLoading(false)
                }

            })
    }

    private fun showLoading(state: Boolean){
        binding.apply {
            if (state){
                progressBar.visibility = View.VISIBLE
                rvMovie.visibility = View.GONE
            }else{
                progressBar.visibility = View.GONE
                rvMovie.visibility = View.VISIBLE
            }
        }
    }
}