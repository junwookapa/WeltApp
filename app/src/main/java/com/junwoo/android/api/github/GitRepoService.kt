package com.junwoo.android.api.github

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.junwoo.android.api.github.vo.Owner
import com.junwoo.android.api.github.vo.Repo
import com.junwoo.android.config.Config
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitRepoService {

    @GET("/users/{id}/repos")
    fun getRepo(@Path("id") id : String) : Flowable<List<Repo>>

    @GET("/users/{id}")
    fun getOwner(@Path("id") id : String) : Flowable<Owner>


}