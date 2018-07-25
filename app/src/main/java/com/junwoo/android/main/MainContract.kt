package com.junwoo.android.main

interface MainContract{
    interface View{
        fun onListUpdate(ownerItem: OwnerItem, list : ArrayList<RepoItem>)
        fun onExceptionOccured()
    }
    interface Presenter{
        fun find(userId: String)
    }
    interface Model{
        fun getData(userId: String
                    , after: (OwnerItem)->Unit
                    , error: ((String)->Unit)? = null)
        fun setData(ownerItem: OwnerItem)
    }

}