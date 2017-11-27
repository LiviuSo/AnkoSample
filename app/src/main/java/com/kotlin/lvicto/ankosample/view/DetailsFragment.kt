package com.kotlin.lvicto.ankosample.view

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.lvicto.ankosample.R
import org.jetbrains.anko.*


class DetailsFragment : Fragment() {

    private lateinit var mItem: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return UI {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)
                background = ContextCompat.getDrawable(ctx, R.drawable.ic_bckgr_winter_trees)
                gravity = Gravity.CENTER

                textView {
                    textResource = R.string.logged_in_message
                    textSize = 20f
                    textColor = Color.BLUE
                    text = mItem
                }.lparams(width = wrapContent, height = wrapContent)
            }
        }.view
    }

    fun setItem(item: String) {
        mItem = item
    }
}