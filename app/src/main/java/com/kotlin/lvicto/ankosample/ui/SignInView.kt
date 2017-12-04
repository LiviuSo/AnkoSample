package com.kotlin.lvicto.ankosample.ui

import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.EditText
import com.kotlin.lvicto.ankosample.R
import org.jetbrains.anko.*

class SignInView : AnkoComponent<SignInActivity> {

    private lateinit var ankoContext: AnkoContext<SignInActivity>

    override fun createView(ui: AnkoContext<SignInActivity>): View = with(ui) {
        ankoContext = ui
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(20)
            gravity = Gravity.CENTER

            val username = editText {
                id = R.id.usernameEditText
                hintResource = R.string.sign_in_username
                setText("winter")// todo remove
            }.lparams(width = matchParent, height = wrapContent)

            val password = editText {
                id = R.id.usernamePassword
                hintResource = R.string.sign_in_password
                setText("frost") // todo remove
            }.lparams(width = matchParent, height = wrapContent)

            button {
                id = R.id.sign_in_button
                textResource = R.string.sign_in_button

                onClick {
                    handleOnSignInButtonPressed(ui, username.text.toString(), password.text.toString())
                }
            }.lparams(width = matchParent, height = wrapContent)

        }.applyRecursively { view ->
            when (view) {
                is EditText -> view.textSize = 22f
            }
        }.apply {
            background = ContextCompat.getDrawable(ctx, R.drawable.ic_bckgr_winter)
        }
    }

    private fun handleOnSignInButtonPressed(ui: AnkoContext<SignInActivity>, userName: String, password: String) {
        if(userName.isBlank() || password.isBlank()) {
            showAlertDialog(R.string.sign_in_invalid_cred_title, R.string.sign_in_invalid_cred_message)
        } else {
            ui.owner.authorizeUser(userName, password)
        }
    }

    fun showAccessDeniedAlertDialog() {
        showAlertDialog(R.string.sign_in_access_denied_title, R.string.sign_in_access_denied_message)
    }

    private fun showAlertDialog(@StringRes titleId: Int, @StringRes messageId: Int) {
        with(ankoContext) {
            alert(title = titleId, message = messageId) {
                positiveButton(com.kotlin.lvicto.ankosample.R.string.dialog_button_close) { }
            }.show()
        }
    }
}