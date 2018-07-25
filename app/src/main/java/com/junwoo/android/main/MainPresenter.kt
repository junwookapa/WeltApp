package com.junwoo.android.main

class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter {
    private val model : MainContract.Model = MainModel(this)

    override fun find(userId: String) {
        model.getData(userId,{
            mainView.onListUpdate(it, it.repoItems)

        }, {
            mainView.onExceptionOccured()
        })
    }
}