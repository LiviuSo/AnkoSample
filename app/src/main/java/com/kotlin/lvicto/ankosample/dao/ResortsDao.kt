package com.kotlin.lvicto.ankosample.dao

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.kotlin.lvicto.ankosample.model.WinterResort
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper.Companion.COL_COUNTRY
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper.Companion.COL_NAME
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper.Companion.COL_RATINGS
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper.Companion.COL_STATE
import com.kotlin.lvicto.ankosample.util.MyDatabaseOpenHelper.Companion.TABLE_NAME
import com.kotlin.lvicto.ankosample.util.PreferenceHelper
import com.kotlin.lvicto.ankosample.util.database
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.StringParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class ResortsDao(private val context: Context) {

    fun addAll(resorts: List<WinterResort>) {
        if(resorts.isEmpty())
            return

        PreferenceHelper.setHasItems(context, MyDatabaseOpenHelper.TABLE_NAME, true)
        for (resort in resorts) {
            context.database.use {
                val values = ContentValues()
                values.put(COL_NAME, resort.name)
                values.put(COL_COUNTRY, resort.country)
                values.put(COL_STATE, resort.state)
                values.put(COL_RATINGS, resort.ratings)
                insert(TABLE_NAME, null, values)
                Log.d("ResortsDao", "inserted: " + values.toString())
            }
        }
    }

    fun query(country: String?, state: String?, ratingsRange: Pair<Int, Int>?): List<WinterResort> {
        val hasCountryArg = !country.isNullOrBlank()
        val hasStateArg = !state.isNullOrBlank()
        val hasRatingsArg = ratingsRange != null

        val results: ArrayList<WinterResort> = arrayListOf()
        context.database.use {
            var argumentAdded = false
            var selection = ""
            val args: ArrayList<Pair<String, Any>> = arrayListOf()

            // build the query
            val sqb = select(TABLE_NAME)

            if(hasCountryArg) {
                selection += "$COL_COUNTRY = {country}"
                args.add("country" to country.toString())
                argumentAdded = true
            }
            if(hasStateArg) {
                if(argumentAdded) {
                    selection += " and "
                }
                selection += "$COL_STATE = {state}"
                args.add("state" to state.toString())
                argumentAdded = true
            }
            if(hasRatingsArg) {
                if (ratingsRange != null) {
                    if(argumentAdded) {
                        selection += " and "
                    }
                    selection += "$COL_RATINGS >= {min} and $COL_RATINGS < {max}"
                    args.add("min" to ratingsRange.first)
                    args.add("max" to ratingsRange.second)
                }
            }
            sqb.where(selection, *(args.toTypedArray()))

            // parse the arguments
            val parser = object : MapRowParser<WinterResort> {
                override fun parseRow(columns: Map<String, Any?>): WinterResort {
                    return WinterResort(columns.toMutableMap())
                }
            }
            sqb.exec {
                results.addAll(parseList(parser))
            }
        }
        return results
    }

    fun getStringValues(column: String, value: String?): List<String> {
        val values = arrayListOf<String>()

        context.database.use {
            val sqb = select(TABLE_NAME, column).distinct()
            if(value != null) {
                sqb.whereSimple(column, value)
            }
            values.addAll(sqb.exec {
                parseList(StringParser)
            })
        }
        return values
    }
}