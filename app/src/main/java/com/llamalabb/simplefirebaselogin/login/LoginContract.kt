package com.llamalabb.simplefirebaselogin.login

import android.support.v4.app.FragmentActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.llamalabb.simplefirebaselogin.BasePresenter
import com.llamalabb.simplefirebaselogin.BaseView

/**
 * Created by andy on 10/18/17.
 */
interface LoginContract {
    interface View: BaseView<Presenter>{
        fun showFailure(msg: String)
        fun showSuccess()
        fun createAccountLinkClicked()
        fun loginButtonClicked()
        fun showHideLinkClicked()
        fun googleSignInButtonClicked()
    }

    interface Presenter : BasePresenter{
        fun loginUser(email: String, password: String)
        fun showHideText()
    }
}
