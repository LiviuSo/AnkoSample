package com.kotlin.lvicto.ankosample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kotlin.lvicto.ankosample.R
import org.jetbrains.anko.frameLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listFragment = ListFragment()
        frameLayout {
            id = R.id.rv_container
            fragmentManager.beginTransaction().replace(R.id.rv_container, listFragment).commit()
        }
    }
}
