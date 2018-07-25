package com.junwoo.android.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.junwoo.android.welttestapp.R

class GitRepoAdapter(
        private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mOwnerItem : OwnerItem = OwnerItem()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0 ){
            val view = LayoutInflater.from(context).inflate(R.layout.git_repo_item_owner, parent, false)
            val height = parent.measuredHeight / 10
            view.minimumHeight = height
            OwnerItem.Holder(view)
        }else{
            val view = LayoutInflater.from(context).inflate(R.layout.git_repo_item_repo, parent, false)
            val height = parent.measuredHeight / 10
            view.minimumHeight = height
            RepoItem.Holder(view)
        }

    }

    override fun getItemCount(): Int {
        return mOwnerItem.repoItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0){
            val h = holder as OwnerItem.Holder
            holder.from(mOwnerItem)
        }else{
            val h = holder as RepoItem.Holder
            holder.from(mOwnerItem.repoItems[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position ==0 ) 0 else 1
    }
    fun clear(){
        mOwnerItem = OwnerItem()
        notifyDataSetChanged()
    }
}