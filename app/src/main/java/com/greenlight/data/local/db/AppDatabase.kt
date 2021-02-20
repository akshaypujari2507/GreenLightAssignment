package com.greenlight.data.local.db

import android.content.Context
import android.util.Log
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.greenlight.data.local.doa.*
import com.greenlight.data.local.entities.*

@Database(
    entities = arrayOf(
        Country::class, Zone::class, Region::class, Area::class,
        Employee::class
    ), version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private val LOG_TAG: String = AppDatabase::class.simpleName.toString()
        private val LOCK: Any = Object()
        private val DATABSE_NAME: String = "myDB"

        @Volatile
        private var sInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                synchronized(LOCK) {
                    Log.d(LOG_TAG, "Creating new database instance")
                    sInstance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, AppDatabase.DATABSE_NAME
                    )
                        .build()
                }
            }
            Log.d(LOG_TAG, "Getting the database instance")
            return sInstance!!
        }

    }


    abstract fun countryDao(): CountryDao
    abstract fun zoneDao(): ZoneDao
    abstract fun regionDao(): RegionDao
    abstract fun areaDao(): AreaDao
    abstract fun employeeDao(): EmployeeDao

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented")
    }

    override fun clearAllTables() {
        TODO("not implemented")
    }
}