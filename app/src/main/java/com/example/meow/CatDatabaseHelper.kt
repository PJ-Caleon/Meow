package com.example.meow

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CatDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CatDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Cats"
        private const val COLUMN_ID = "id"
        private const val COLUMN_SECTION = "section"
        private const val COLUMN_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_SECTION TEXT NOT NULL,
                $COLUMN_NAME TEXT NOT NULL
            );
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Insert a cat into the database
    fun insertCat(section: String, name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_SECTION, section)
            put(COLUMN_NAME, name)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Get all cats from the database
    fun getAllCats(): MutableMap<String, MutableList<String>> {
        val catMap = mutableMapOf<String, MutableList<String>>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val section = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SECTION))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))

            if (!catMap.containsKey(section)) {
                catMap[section] = mutableListOf()
            }
            catMap[section]?.add(name)
        }
        cursor.close()
        db.close()
        return catMap
    }

    // Delete all cats from the database
    fun clearDatabase() {
        val db = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
        db.close()
    }

    // Delete all cats from a specific section
    fun clearSection(section: String) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_SECTION = ?", arrayOf(section))
        db.close()
    }

    // Check if the database is empty
    fun isDatabaseEmpty(): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM $TABLE_NAME", null)
        var isEmpty = true
        if (cursor.moveToFirst()) {
            isEmpty = cursor.getInt(0) == 0
        }
        cursor.close()
        return isEmpty
    }
}
