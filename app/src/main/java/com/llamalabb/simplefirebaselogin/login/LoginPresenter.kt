package com.llamalabb.simplefirebaselogin.login
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.view.View
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.llamalabb.simplefirebaselogin.R
import com.llamalabb.simplefirebaselogin.data.external.LoginService

/**
 * Created by andy on 10/18/17.
 */
class LoginPresenter(val view:LoginContract.View) :
        LoginContract.Presenter,
        LoginService.LoginCallBack{

    override fun onCreate() {
        LoginService.register(this)
    }

    override fun loginUser(email: String, password: String) {
        LoginService.loginUser(email, password)
    }

    override fun accountLoginSuccessful() {
        view.showSuccess()
    }

    override fun accountLoginFailure(msg: String) {
        view.showFailure(msg)
    }

    override fun showHideText() {

    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
    }
}