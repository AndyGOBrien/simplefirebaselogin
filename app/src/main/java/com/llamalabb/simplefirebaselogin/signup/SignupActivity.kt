package com.llamalabb.simplefirebaselogin.signup

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.llamalabb.simplefirebaselogin.R
import com.llamalabb.simplefirebaselogin.login.LoginActivity
import com.llamalabb.simplefirebaselogin.utils.Utils

class SignupActivity : AppCompatActivity(), SignupContract.View {

    override var mPresenter: SignupContract.Presenter = SignupPresenter(this)

    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mConfirmEditText: EditText
    private lateinit var mShowPasswordLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mEmailEditText = findViewById(R.id.email_editText)
        mPasswordEditText = findViewById(R.id.password_editText)
        mConfirmEditText = findViewById(R.id.confirm_editText)
        mShowPasswordLink = findViewById(R.id.show_hide_link)

        mPresenter.onCreate()
    }

    fun createButtonClicked(view: View){
        val email = mEmailEditText.text.toString()
        val password = mPasswordEditText.text.toString()
        val confirm = mConfirmEditText.text.toString()

        mPresenter.createAccount(email, password, confirm)
    }

    fun haveAccountLinkClicked(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    fun showTextClicked(view: View){
        if(mShowPasswordLink.text == "show") {
            mShowPasswordLink.text = "hide"
            mPasswordEditText.transformationMethod = null
            mConfirmEditText.transformationMethod = null
        } else {
            mShowPasswordLink.text = "show"
            mPasswordEditText.transformationMethod = PasswordTransformationMethod()
            mConfirmEditText.transformationMethod = PasswordTransformationMethod()
        }
    }

    override fun showFailure(msg: String) {
        Utils.showMessageShort(this, msg)
    }

    override fun showSuccess() {
        Utils.showMessageShort(this, "Signup Success!")
    }

    override fun showPasswordsMismatch() {
        Utils.showMessageShort(this, "Password entries do not match")
    }

}
