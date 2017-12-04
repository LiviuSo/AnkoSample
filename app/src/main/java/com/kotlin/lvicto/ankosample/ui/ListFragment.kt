package com.kotlin.lvicto.ankosample.ui

import android.app.Fragment
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kotlin.lvicto.ankosample.R
import com.kotlin.lvicto.ankosample.dao.ResortsDao
import com.kotlin.lvicto.ankosample.presenter.DefaultListPresenter
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ListFragment : Fragment() {

    private lateinit var mAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
    private lateinit var spinnerCountries: Spinner
    private lateinit var spinnerStates: Spinner
    private lateinit var spinnerRatings: Spinner
    private val ratingsRanges = arrayOf("Ratings", "0-49", "50-99", "100-149", "150-199", "200+")
    private val noCountryValue = "Country"
    private val noStateValue = "State"

    private lateinit var list: List<String>
    private lateinit var spinnerCountryValues: ArrayList<String>
    private lateinit var spinnerStateValues: ArrayList<String>
    private lateinit var mPresenter: DefaultListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = DefaultListPresenter(activity)
        spinnerCountryValues = getSpinnerValues(MyDatabaseOpenHelper.COL_COUNTRY)
        spinnerStateValues = getSpinnerValues(MyDatabaseOpenHelper.COL_STATE)
        list = mPresenter.getList(arrayListOf(null, null, null))
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        return UI {
            verticalLayout {
                lparams(width = matchParent, height = matchParent)

                verticalLayout {
                    linearLayout {
                        padding = 10
                        orientation = LinearLayout.HORIZONTAL
                        spinnerCountries = spinner { // countries
                            lparams(0, wrapContent) {
                                weight = 1.0f
                            }
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    spinnerCountryValues.toTypedArray())

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    Log.d("ListFragment", "spinnerCountries -> onItemSelected")
                                    list = mPresenter.getList(fetchSpinnersSelections())
                                    mAdapter.notifyDataSetChanged()
                                }
                            }
                        }
                        spinnerStates = spinner { // states
                            Log.d("ListFragment", "spinnerStates -> onItemSelected")

                            lparams(0, wrapContent) {
                                weight = 1.0f
                            }
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    spinnerStateValues)

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    Log.d("ListFragment", "spinnerStates -> onItemSelected")
                                    list = mPresenter.getList(fetchSpinnersSelections())
                                    mAdapter.notifyDataSetChanged()
                                }
                            }
                        }
                        spinnerRatings = spinner { // ratings
                            Log.d("ListFragment", "spinnerRatings -> onItemSelected")
                            lparams(0, wrapContent) {
                                weight = 1.0f
                            }
                            adapter = ArrayAdapter<String>(ctx,
                                    android.R.layout.simple_spinner_dropdown_item,
                                    ratingsRanges)

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }

                                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    list = mPresenter.getList(fetchSpinnersSelections())
                                    mAdapter.notifyDataSetChanged()
                                }
                            }
                        }.lparams(wrapContent, wrapContent)
                    }
                    view {
                        backgroundColor = Color.GRAY
                    }.lparams(matchParent, 2)
                }.lparams(width = matchParent, height = wrapContent)

                recyclerView {
                    Log.d("ListFragment", "recyclerView")
                    layoutManager = LinearLayoutManager(context, orientation, false)
                    adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                        override fun getItemCount(): Int = list.size

                        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
                            val view = TextView(context).apply {
                                padding = 10
                                layoutParams = ViewGroup.LayoutParams(matchParent, wrapContent)
                            }
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
                        }
                    }
                    mAdapter = adapter
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST))
                }.lparams(width = matchParent, height = matchParent)
            }
        }.view
    }


    private fun fetchSpinnersSelections(): ArrayList<Any?> {
        val values = arrayListOf<Any?>()
        val country = if(spinnerCountries.selectedItem == noCountryValue) {
            null
        } else {
            spinnerCountries.selectedItem
        }
        val state = if(spinnerStates.selectedItem == noStateValue) {
            null
        } else {
            spinnerStates.selectedItem
        }
        val ratings = if(spinnerRatings.selectedItem == "Ratings") {
            null
        } else {
            val parts = (spinnerRatings.selectedItem as String).split("-")
            if(parts.size == 2) {
                Pair(parts[0].toInt(), parts[1].toInt())
            } else { // expected 200+
                Pair(parts[0].substring(0, parts[0].length - 1).toInt(), 99999) // infinity
            }
        }
        values.add(country)
        values.add(state)
        values.add(ratings)
        return values
    }

    private fun getSpinnerValues(column: String, value: String? = null): ArrayList<String> {
        val values: ArrayList<String> = arrayListOf()
        values.addAll(ResortsDao(activity).getStringValues(column, value))
        values.sort()
        val noValue = when (column) {
            MyDatabaseOpenHelper.COL_COUNTRY -> noCountryValue
            MyDatabaseOpenHelper.COL_STATE -> noStateValue
            else -> ""
        }
        values.add(0, noValue)
        return values
    }
}