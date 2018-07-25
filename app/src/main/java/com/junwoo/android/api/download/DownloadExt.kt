package com.junwoo.android.api.download

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.BufferedInputStream

fun Flowable<Response<ResponseBody>>.toBitmapFlowable() : Flowable<Bitmap> {
    return this.subscribeOn(Schedulers.newThread()).map {
        val bis = BufferedInputStream(it.body()?.byteStream(), 1024 * 8)
        val bitmMap = BitmapFactory.decodeStream(bis)
        bitmMap
    }
}