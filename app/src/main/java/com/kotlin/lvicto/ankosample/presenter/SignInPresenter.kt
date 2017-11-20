package com.kotlin.lvicto.ankosample.presenter

import com.kotlin.lvicto.ankosample.model.AuthCredentials

interface SignInPresenter {

    fun checkUserCredentials(credentials: AuthCredentials): Boolean
}