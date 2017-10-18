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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

