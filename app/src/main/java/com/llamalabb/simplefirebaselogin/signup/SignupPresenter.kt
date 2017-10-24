package com.llamalabb.simplefirebaselogin.signup

import com.llamalabb.simplefirebaselogin.data.external.SignupService


/**
 * Created by andy on 10/18/17.
 */
class SignupPresenter(val view: SignupContract.View) :
        SignupContract.Presenter,
        SignupService.SignupCallBack {

    override fun onCreate() {
        SignupService.register(this)
    }

    override fun createAccount(email: String, password: String, confirm: String) {
        if(confirmPasswordMatch(password, confirm)) {
            SignupService.createAccount(email, password)
        } else {
            view.showPasswordsMismatch()
        }
    }

    override fun accountCreationSuccessful() {
        view.showSuccess()
    }

    override fun accountCreationFailure(msg: String) {
        view.showFailure(msg)
    }

    override fun confirmPasswordMatch(password: String, confirm: String): Boolean {
        if (password == confirm) return true
        return false
    }


    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }

}