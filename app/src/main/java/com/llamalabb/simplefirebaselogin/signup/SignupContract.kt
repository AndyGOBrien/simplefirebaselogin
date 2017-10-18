package com.llamalabb.simplefirebaselogin.signup

import com.llamalabb.simplefirebaselogin.BasePresenter
import com.llamalabb.simplefirebaselogin.BaseView

/**
 * Created by andy on 10/18/17.
 */
interface SignupContract {
    interface View : BaseView<Presenter>{
        fun showFailure()
        fun showSuccess()
    }

    interface Presenter : BasePresenter{
        fun createAccount(email: String, password: String, confirm: String)
        fun confirmEmailNotExist(email: String)
        fun confirmPasswordMatch(password: String, confirm: String): Boolean
    }
}