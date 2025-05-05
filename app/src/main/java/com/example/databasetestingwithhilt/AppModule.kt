package com.example.databasetestingwithhilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.databasetestingwithhilt.Database.AppDatabase
import com.example.databasetestingwithhilt.Database.FoodDao
import com.example.databasetestingwithhilt.Database.NutrientRepository
import com.example.databasetestingwithhilt.Database.SleepDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.work.WorkManager


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext
        , AppDatabase::class.java
        , AppDatabase.databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesDao(database : AppDatabase): FoodDao {
        return database.foodDao()
    }

    @Provides
    @Singleton
    fun providesSleepDao(database: AppDatabase):SleepDao{
        return database.sleepDao()
    }
    @Provides
    @Singleton
    fun providesRepository(dao : FoodDao,
                           firebaseAuth: FirebaseAuth,
                           databaseReference: DatabaseReference): NutrientRepository {
        return NutrientRepository(dao,firebaseAuth,databaseReference)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesDatabaseReference(): DatabaseReference = FirebaseDatabase.getInstance().reference


    @Provides
    @Singleton
    fun provideWorkManagerInstance(app: Application): WorkManager{
        return WorkManager.getInstance(app)
    }

}