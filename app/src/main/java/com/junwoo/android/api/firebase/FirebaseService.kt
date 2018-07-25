package com.junwoo.android.api.firebase

import com.google.firebase.database.FirebaseDatabase

class FirebaseService {
    companion object {
        fun insert(msg : String){
            val firebaseDatabase = FirebaseDatabase.getInstance()
            val databaseReference = firebaseDatabase.reference
            databaseReference.child("git").push().setValue(msg)
        }
    }
}