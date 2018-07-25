package com.junwoo.android.main

import android.graphics.BitmapFactory
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.junwoo.android.api.download.DownloadService
import com.junwoo.android.api.firebase.FirebaseService
import com.junwoo.android.api.github.GitRepoService
import com.junwoo.android.api.github.vo.Owner
import com.junwoo.android.api.github.vo.Repo
import com.junwoo.android.config.Config
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedInputStream

/**
 * Model 의 추상화 레벨은 원격이나, 내부(sqlite, network)는 같은 수준으로 합니다.
 * 시간상 생략하였습니다.
 * orm 도구는 greendao를 주로 썻으나, AAC의 ROOM 공부중입니다. 업무 특성상 많이 쓸 일은 없었습니다.
 * realm은 공부한정도입니다.
 */

class MainModel(val presenter: MainContract.Presenter) : MainContract.Model {

    private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private var mGitRepoService : GitRepoService? = null
    private var mDownloadService : DownloadService? = null



    private fun create(){
        if(mGitRepoService == null){
            mGitRepoService = retrofit.create(GitRepoService::class.java)
        }
        if(mDownloadService == null){
            mDownloadService = retrofit.create(DownloadService::class.java)
        }
    }

    override fun getData(userId: String, after: (OwnerItem)->Unit, error: ((String) -> Unit)?){
        var owner = OwnerItem()
        create()
        // rxjava 숙지가 좀더 필요함 : rxandroid changing, flatmap
        Flowable.merge(mGitRepoService!!.getOwner(userId), mGitRepoService!!.getRepo(userId))
                .subscribeOn(Schedulers.newThread())
                .subscribe (
                        {
                            if(it is List<*>){
                                it.forEach {
                                    if(it is Repo){
                                        val repo = RepoItem(it.fullName, it.description.toString(), it.stargazersCount)
                                        owner.repoItems.add(repo)
                                        owner.isRepoUpdated = true
                                    }
                                }
                            }

                            if(it is Owner){
                                owner.name = it.login
                                owner.imageUrl = it.avatarUrl
                                mDownloadService?.downloadFile(it.avatarUrl)?.subscribe{
                                    val bis = BufferedInputStream(it.body()?.byteStream(), 1024 * 8)
                                    val bitmMap = BitmapFactory.decodeStream(bis)
                                    owner.bitmap = bitmMap
                                    owner.isOwnerUpdated = true
                                }
                            }
                            if(owner.isUpdate()){
                                after(owner)
                                setData(owner)
                            }
                        },
                        {
                            error?.invoke(it.message!!)
                        }
                )
    }

    override fun setData(ownerItem: OwnerItem) {
        FirebaseService.insert(ownerItem.toGson())
    }

}