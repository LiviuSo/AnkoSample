package com.kotlin.lvicto.ankosample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kotlin.lvicto.ankosample.R
import com.kotlin.lvicto.ankosample.dao.MyDatabaseOpenHelper
import com.kotlin.lvicto.ankosample.dao.ResortsDao
import com.kotlin.lvicto.ankosample.model.WinterResortsList
import com.kotlin.lvicto.ankosample.util.PreferenceHelper
import org.jetbrains.anko.frameLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PreferenceHelper.hasItems(this, MyDatabaseOpenHelper.TABLE_NAME)) {
            Log.d("MainActivity", "hasItems = false adding")
            ResortsDao(this).addAll(WinterResortsList.resorts)
        }

        val listFragment = ListFragment()
        frameLayout {
            id = R.id.rv_container
            fragmentManager.beginTransaction().replace(R.id.rv_container, listFragment).commit()
        }
    }
}
