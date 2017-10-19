package com.llamalabb.simplefirebaselogin.signup

import com.llamalabb.simplefirebaselogin.BasePresenter
import com.llamalabb.simplefirebaselogin.BaseView

/**
 * Created by andy on 10/18/17.
 */
interface SignupContract {
    interface View : BaseView<Presenter>{
        fun showFailure(msg: String)
        fun showSuccess()
        fun showPasswordsMismatch()
    }

    interface Presenter : BasePresenter{
        fun createAccount(email: String, password: String, confirm: String)
        fun confirmPasswordMatch(password: String, confirm: String): Boolean
    }
}