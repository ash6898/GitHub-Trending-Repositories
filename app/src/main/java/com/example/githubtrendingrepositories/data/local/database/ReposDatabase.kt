package com.example.githubtrendingrepositories.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubtrendingrepositories.data.local.dao.ReposDao
import com.example.githubtrendingrepositories.data.local.entity.ReposEntity

@Database(entities = [ReposEntity::class], version = 1, exportSchema = false)
abstract class ReposDatabase: RoomDatabase() {

    abstract fun reposDao(): ReposDao

    companion object{
        private var INSTANCE: ReposDatabase? = null

        fun getDatabase(context: Context): ReposDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ReposDatabase::class.java,
                    "repos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}