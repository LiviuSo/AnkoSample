package com.kotlin.lvicto.ankosample.presenter

interface ResortsListPresenter {
    fun getList(queryParams: ArrayList<Any?>): List<String>
}