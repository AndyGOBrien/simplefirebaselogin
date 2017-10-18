package com.llamalabb.simplefirebaselogin.signup

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by andy on 10/18/17.
 */
class SignupPresenter(val view: SignupContract.View) : SignupContract.Presenter {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate() {
        mAuth = FirebaseAuth.getInstance()
    }


    override fun createAccount(email: String, password: String, confirm: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser!!
                    view.showSuccess()
                } else {
                    view.showFailure()
                }
            }
    }

    override fun confirmEmailNotExist(email: String) {

    }

    override fun confirmPasswordMatch(password: String, confirm: String): Boolean {

        if (password == confirm) return true

        return false
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