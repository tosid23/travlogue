package com.aurora.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aurora.data.data.converter.CurrencyConverter
import com.aurora.data.data.converter.TransitModeConverter
import com.aurora.data.data.dao.ActivityDao
import com.aurora.data.data.dao.DayPlanDao
import com.aurora.data.data.dao.MessageDao
import com.aurora.data.data.dao.StayDao
import com.aurora.data.data.dao.TripDao
import com.aurora.data.data.entity.ActivityEntity
import com.aurora.data.data.entity.DayPlanEntity
import com.aurora.data.data.entity.DayPlanStayJunction
import com.aurora.data.data.entity.StayEntity
import com.aurora.data.data.entity.message.MessageEntity
import com.aurora.data.data.entity.trip.TripEntity

const val DB_NAME = "travlogue_db"

@Database(
    entities = [TripEntity::class, MessageEntity::class,
        ActivityEntity::class, StayEntity::class, DayPlanEntity::class,
        DayPlanStayJunction::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    TransitModeConverter::class,
    CurrencyConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun tripDao(): TripDao
    abstract fun activityDao(): ActivityDao
    abstract fun stayDao(): StayDao
    abstract fun dayPlanDao(): DayPlanDao

    companion object {
        const val DATABASE_NAME = DB_NAME
    }
}