package com.llamalabb.simplefirebaselogin.data.external


import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Created by andy on 10/19/17.
 */
object LoginService {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var mCallBack: LoginCallBack


    interface LoginCallBack{
        fun accountLoginSuccessful()
        fun accountLoginFailure(msg: String)
    }

    fun register(callback: LoginCallBack){
        mCallBack = callback
    }

    fun loginUser(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{task: Task<AuthResult> ->
                    if(task.isSuccessful){
                        val user = mAuth.currentUser!!
                        mCallBack.accountLoginSuccessful()
                    } else {
                        mCallBack.accountLoginFailure(task.exception!!.message!!)
                    }
                }

    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener{task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser!!
                        mCallBack.accountLoginSuccessful()
                    } else {
                        mCallBack.accountLoginFailure("Google Auth Failed")
                    }
                }
    }

}