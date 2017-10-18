package com.llamalabb.simplefirebaselogin.login

import com.llamalabb.simplefirebaselogin.BasePresenter
import com.llamalabb.simplefirebaselogin.BaseView

/**
 * Created by andy on 10/18/17.
 */
interface LoginContract {
    interface View: BaseView<Presenter>{
    }

    interface Presenter : BasePresenter{

    }
}
