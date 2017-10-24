package com.llamalabb.simplefirebaselogin.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.llamalabb.simplefirebaselogin.R
import com.llamalabb.simplefirebaselogin.signup.SignupActivity
import com.llamalabb.simplefirebaselogin.utils.Utils

class LoginActivity : AppCompatActivity(),
        LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{


    private val RC_SIGN_IN = 9001

    override var mPresenter: LoginContract.Presenter = LoginPresenter(this)

    lateinit var mPasswordEditText: EditText
    lateinit var mEmailEditText: EditText
    lateinit var mShowHideLink: TextView
    lateinit var mCreateAccountLink: TextView
    lateinit var mLoginButton: Button
    lateinit var mGoogleLoginButton: ImageButton

    lateinit var gso: GoogleSignInOptions
    lateinit var mGoogleApiClient: GoogleApiClient

    var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mEmailEditText = findViewById(R.id.email_editText)
        mPasswordEditText = findViewById(R.id.password_editText)
        mShowHideLink = findViewById(R.id.show_hide_link)
        mCreateAccountLink = findViewById(R.id.create_account_link)
        mLoginButton = findViewById(R.id.login_button)
        mGoogleLoginButton = findViewById(R.id.google_login_button)

        mShowHideLink.setOnClickListener(this)
        mCreateAccountLink.setOnClickListener(this)
        mLoginButton.setOnClickListener(this)
        mGoogleLoginButton.setOnClickListener(this)

        buildGoogleSignInObject()

        mPresenter.onCreate()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.login_button ->
                loginButtonClicked()
            R.id.show_hide_link->
                showHideLinkClicked()
            R.id.create_account_link->
                createAccountLinkClicked()
            R.id.google_login_button ->
                googleSignInButtonClicked()
        }
    }

    override fun createAccountLinkClicked(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }

    override fun loginButtonClicked(){
        val email = mEmailEditText.text.toString().toLowerCase().trim()
        val password = mPasswordEditText.text.toString().toLowerCase().trim()

        mPresenter.loginUser(email, password)
    }

    override fun showHideLinkClicked(){
        if(mShowHideLink.text == "show") {
            mShowHideLink.text = "hide"
            mPasswordEditText.transformationMethod = null
        } else {
            mShowHideLink.text = "show"
            mPasswordEditText.transformationMethod = PasswordTransformationMethod()
        }
    }

    override fun googleSignInButtonClicked(){
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun showFailure(msg: String) {
        Utils.showMessageLong(this, msg)
    }

    override fun showSuccess() {
        Utils.showMessageShort(this, "Login Successful")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent){
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    fun buildGoogleSignInObject(){

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_clint_id))
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    fun handleSignInResult(result: GoogleSignInResult){
        if(result.isSuccess){
            showSuccess()
            var account = result.signInAccount!!
            firebaseAuthWithGoogle(account)
        } else {
            showFailure("Google SignIn Failure")
        }
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener{task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser!!
                        showSuccess()
                    } else {
                        showFailure("Google Auth Failed")
                    }
                }
    }

}
