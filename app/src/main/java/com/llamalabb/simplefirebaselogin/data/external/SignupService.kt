package com.llamalabb.simplefirebaselogin.data.external

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by andy on 10/19/17.
 */
object SignupService {

    private var mAuth = FirebaseAuth.getInstance()
    private lateinit var mCallBack: SignupCallBack

    interface SignupCallBack {
        fun accountCreationSuccessful()
        fun accountCreationFailure(msg: String)
    }

    fun register(callback: SignupCallBack){
        mCallBack = callback
    }

    fun createAccount(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser!!
                        mCallBack.accountCreationSuccessful()
                    } else {
                        mCallBack.accountCreationFailure(task.exception!!.message!!)
                    }
                }
    }
}