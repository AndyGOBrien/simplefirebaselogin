package com.llamalabb.simplefirebaselogin.home


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by andy on 10/18/17.
 */
class HomePresenter(val homeView: HomeContract.View) : HomeContract.Presenter {

    private lateinit var mAuth: FirebaseAuth



    override fun onCreate() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }
}

