package com.kotlin.lvicto.ankosample.view

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlin.lvicto.ankosample.R
import com.kotlin.lvicto.ankosample.model.WinterResortsList
import com.kotlin.lvicto.ankosample.view.DividerItemDecoration.Companion.VERTICAL_LIST
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListFragment : Fragment() {

    var list: List<String> = WinterResortsList.getResorts()
    private lateinit var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return UI {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)
                verticalLayout {
                    linearLayout {
                        padding = 20
                        orientation = LinearLayout.HORIZONTAL
                        spinner { // countries
                            val countries = arrayOf("All", "USA", "CAN")
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    countries)

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                }
                            }
                        }.lparams(wrapContent, wrapContent)
                        spinner { // states
                            val countries = arrayOf("All", "State 1", "State 2")
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    countries)

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                }
                            }
                        }.lparams(wrapContent, wrapContent)
                        spinner { // ratings
                            val countries = arrayOf("All", "0-49", "50-99", "100-149", "150-199", "200+")
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    countries)

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                }
                            }
                        }.lparams(wrapContent, wrapContent)
                    }
                    view {
                        backgroundColor = Color.GRAY
                    }.lparams(matchParent, 2)
                }.lparams(width = matchParent, height = wrapContent)

                recyclerView {
                    layoutManager = LinearLayoutManager(context, orientation, false)

                    adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                        override fun getItemCount(): Int = list.size


                        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
                            val view = TextView(context).apply {
                                padding = 10
                                layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
                            }

//                            val view = verticalLayout { // ???
//                                isClickable = true
//
//                                textView {
//                                    id = R.id.item_text
//                                    padding = 10
//                                }.lparams(matchParent, wrapContent)
//
//                                view {
//                                    backgroundColor = Color.GRAY
//                                }.lparams(matchParent, 1)
//                            }.lparams(matchParent, wrapContent)

                            return object : RecyclerView.ViewHolder(view) {}
                        }

                        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
                            holder!!.itemView.onClick {
                                val detailsFragment = DetailsFragment()
                                detailsFragment.setItem(list[position])
                                (ctx as MainActivity).fragmentManager.beginTransaction()
                                        .replace(R.id.rv_container, detailsFragment)
                                        .addToBackStack(DetailsFragment::class.java.simpleName)
                                        .commit()
                            }
                            (holder.itemView as TextView).text = list[position]
//                            (holder.itemView.findViewById<TextView>(R.id.item_text) as TextView).text = list[position] // ???
                        }
                    }
                    mAdapter = adapter

                    addItemDecoration(DividerItemDecoration(context, VERTICAL_LIST))
                }.lparams(width = matchParent, height = matchParent)
            }
        }.view
    }
}