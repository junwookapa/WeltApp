package com.junwoo.android.main

import android.support.v7.widget.RecyclerView
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.junwoo.android.welttestapp.R

data class OwnerItem(
        var bitmap: Bitmap? = null ,
        var name : String? = null){


    var repoItems = ArrayList<RepoItem>()
    var imageUrl: String? = null

    var isRepoUpdated = false
    var isOwnerUpdated = false

    fun isUpdate() = isOwnerUpdated && isRepoUpdated

    fun add(repoItem : RepoItem){
        repoItems.add(repoItem)
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val title : ImageView? = itemView?.findViewById<ImageView>(R.id.item_owner_pic)
        val name : TextView? = itemView?.findViewById(R.id.item_owner_name)

        fun from(item: OwnerItem){
            this.title?.setImageBitmap(item.bitmap)
            this.name?.text = item.name
        }
    }
}

data class RepoItem(
        var name : String? = null,
        var des : String? = null,
        var cnt : Int? = null){

    fun toHolder(holder :Holder){
        holder.from(this)
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val name : TextView? = itemView?.findViewById(R.id.item_repo_name)
        val des : TextView? = itemView?.findViewById(R.id.item_repo_des)
        val cnt : TextView? = itemView?.findViewById(R.id.item_repo_cnt)

        fun from(item: RepoItem){
            this.name?.text = item.name
            this.des?.text = item.des
            this.cnt?.text = item.cnt.toString()
        }
    }
}
fun OwnerItem.toGson() : String{
    val hashMap = HashMap<String, List<RepoItem>>()
    hashMap.put(this.name!!, this.repoItems)
    Log.e("test", Gson().toJson(hashMap))
    return Gson().toJson(hashMap)
}