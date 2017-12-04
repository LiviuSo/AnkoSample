package com.kotlin.lvicto.ankosample.presenter

import android.content.Context
import com.kotlin.lvicto.ankosample.dao.ResortsDao

class DefaultListPresenter(private val context: Context) : ResortsListPresenter {

    override fun getList(queryParams: ArrayList<Any?>): List<String> {
        val country = queryParams[0] as String?
        val state = queryParams[1]  as String?
        val ratings = queryParams[2] as Pair<Int, Int>?
        return ResortsDao(context).query(country, state, ratings).map { it.toString() }
    }
}