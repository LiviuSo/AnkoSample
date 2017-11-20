package com.kotlin.lvicto.ankosample.view

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import com.kotlin.lvicto.ankosample.R
import org.jetbrains.anko.*

class MainView : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            background = ContextCompat.getDrawable(ctx, R.drawable.ic_bckgr_winter_trees)
            gravity = Gravity.CENTER

            textView {
                textResource = R.string.logged_in_message
                textSize = 20f
                textColor = Color.BLUE
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }
}