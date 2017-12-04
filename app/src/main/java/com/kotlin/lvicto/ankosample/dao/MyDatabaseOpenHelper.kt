package com.kotlin.lvicto.ankosample.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyDatabase", null, 1) {
    companion object {

        val TABLE_NAME = "Resorts"
        val COL_ID = "_id"
        val COL_NAME = "name"
        val COL_STATE = "state"
        val COL_COUNTRY = "country"
        val COL_RATINGS = "ratings"


        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(TABLE_NAME, true,
                COL_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                COL_NAME to TEXT,
                COL_STATE to TEXT,
                COL_COUNTRY to TEXT,
                COL_RATINGS to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable("User", true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)
