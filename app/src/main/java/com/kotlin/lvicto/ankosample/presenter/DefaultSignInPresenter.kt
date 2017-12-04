package com.kotlin.lvicto.ankosample.presenter

import android.content.Context
import com.kotlin.lvicto.ankosample.model.AuthCredentials
import com.kotlin.lvicto.ankosample.ui.SignInActivity
import com.kotlin.lvicto.ankosample.ui.SignInView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DefaultSignInPresenter(private val activity: Context, val mView: SignInView) : SignInPresenter {

    override fun checkUserCredentials(credentials: AuthCredentials): Boolean =
            "winter" == credentials.username && "frost" == credentials.password

    fun authorizeUser(userName: String, password: String) {
        doAsync {
            val authorized = checkUserCredentials(AuthCredentials(userName, password))

            uiThread {
                if (authorized) {
                    (activity as SignInActivity).launchMain()
                } else {
                    mView.showAccessDeniedAlertDialog()
                }
            }
        }
    }
}