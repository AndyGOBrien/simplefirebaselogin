package com.llamalabb.simplefirebaselogin.data.external

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by andy on 10/19/17.
 */
object LoginService {

    private val mAuth: FirebaseAuth

    init{
        mAuth = FirebaseAuth.getInstance()
    }

}