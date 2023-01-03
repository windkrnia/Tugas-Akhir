package com.project.proyek_akhir_mobile_programming.data.api

import com.project.proyek_akhir_mobile_programming.data.model.ListResponse
import com.project.proyek_akhir_mobile_programming.data.model.MovieResponse
import com.project.proyek_akhir_mobile_programming.data.model.TvShowResponse
import com.project.proyek_akhir_mobile_programming.utils.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?api_key=$API_KEY")
    fun getMovies(): Call<ListResponse<MovieResponse>>

    @GET("tv/airing_today?api_key=$API_KEY")
    fun getTvShow(): Call<ListResponse<TvShowResponse>>

}