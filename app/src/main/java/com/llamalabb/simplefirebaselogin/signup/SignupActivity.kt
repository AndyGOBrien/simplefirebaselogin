package com.llamalabb.simplefirebaselogin.signup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.llamalabb.simplefirebaselogin.R
import com.llamalabb.simplefirebaselogin.utils.Utils

class SignupActivity : AppCompatActivity(), SignupContract.View {

    override var mPresenter: SignupContract.Presenter = SignupPresenter(this)

    lateinit var mEmailEditText: EditText
    lateinit var mPasswordEditText: EditText
    lateinit var mConfirmEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mEmailEditText = findViewById(R.id.email_editText)
        mPasswordEditText = findViewById(R.id.password_editText)
        mConfirmEditText = findViewById(R.id.confirm_editText)

        mPresenter.onCreate()
    }

    fun onSignupButtonClicked(view: View){
        val password = mPasswordEditText.text.toString()
        val confirm = mConfirmEditText.text.toString()
        val email = mEmailEditText.text.toString()

        if(mPresenter.confirmPasswordMatch(password, confirm)) {
            mPresenter.createAccount(email, password, confirm)
        } else {
            showFailure()
        }
    }

    override fun showFailure() {
        Utils.showMessageShort(this, "Signup Failed")
    }

    override fun showSuccess() {
        Utils.showMessageShort(this, "Signup Success!")
    }



}
