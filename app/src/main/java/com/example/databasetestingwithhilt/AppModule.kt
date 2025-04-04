package com.example.databasetestingwithhilt

import android.content.Context
import androidx.room.Room
import com.example.databasetestingwithhilt.Database.AppDatabase
import com.example.databasetestingwithhilt.Database.FoodDao
import com.example.databasetestingwithhilt.Database.NutrientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext
        , AppDatabase::class.java
        , AppDatabase.databaseName).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(database : AppDatabase): FoodDao {
        return database.foodDao()
    }
    @Provides
    @Singleton
    fun providesRepository(dao : FoodDao): NutrientRepository {
        return NutrientRepository(dao)
    }
}