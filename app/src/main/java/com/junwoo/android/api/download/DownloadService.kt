package com.junwoo.android.api.download

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import okio.BufferedSink
import okio.Okio
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface DownloadService {

    @Streaming
    @GET
    fun downloadFile(@Url fileUrl:String) : Flowable<Response<ResponseBody>>

}
