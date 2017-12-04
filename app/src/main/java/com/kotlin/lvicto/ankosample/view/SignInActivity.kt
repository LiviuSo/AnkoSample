package com.kotlin.lvicto.ankosample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.lvicto.ankosample.presenter.DefaultSignInPresenter
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.setContentView

class SignInActivity : AppCompatActivity() {

    private lateinit var mPresenter: DefaultSignInPresenter
    private lateinit var mView: SignInView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mView = SignInView()
        mView.setContentView(this)
        mPresenter = DefaultSignInPresenter(this, mView = mView)

    }

    fun authorizeUser(username: String, password: String) {
        mPresenter.authorizeUser(username, password)
    }

    fun launchMain() {
        startActivity(intentFor<MainActivity>())
    }
}