package com.junwoo.android.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.junwoo.android.welttestapp.R

class MainActivity : AppCompatActivity(), MainContract.View {


    override fun onListUpdate(ownerItem: OwnerItem, list: ArrayList<RepoItem>) {
        Handler(Looper.getMainLooper()).post(Runnable {
            adapter.mOwnerItem = ownerItem
            adapter.mOwnerItem.repoItems = list
            adapter.notifyDataSetChanged()
            mErrorTv.visibility = View.INVISIBLE
        })
    }
    override fun onExceptionOccured() {
        Handler(Looper.getMainLooper()).post(Runnable {
            adapter.clear()
            mErrorTv.visibility = View.VISIBLE

        })

    }
    private lateinit var adapter: GitRepoAdapter
    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mErrorTv: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = GitRepoAdapter(this)
        val recyclerView =findViewById<RecyclerView>(R.id.main_rv)
        recyclerView.adapter = adapter
        mPresenter = MainPresenter(this)

        findViewById<AppCompatButton>(R.id.main_find_btn).also {
            it.setOnClickListener{
                mPresenter.find(findViewById<AppCompatEditText>(R.id.main_id_et).text.toString())
            }
        }

        mErrorTv = findViewById<AppCompatTextView>(R.id.main_error_tv)


    }

}
