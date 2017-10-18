package com.llamalabb.simplefirebaselogin.home

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.llamalabb.simplefirebaselogin.R
import com.llamalabb.simplefirebaselogin.signup.SignupActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {
    override var mPresenter: HomeContract.Presenter = HomePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        mPresenter.onCreate()
    }

}
