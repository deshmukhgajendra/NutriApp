package com.example.databasetestingwithhilt.di

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.room.Room
import com.example.databasetestingwithhilt.util.AppDatabase
import com.example.databasetestingwithhilt.data.local.FoodDao
import com.example.databasetestingwithhilt.repository.NutrientRepository
import com.example.databasetestingwithhilt.data.local.SleepDao
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
import com.example.databasetestingwithhilt.R
import com.example.databasetestingwithhilt.repository.StepRepository
import com.example.databasetestingwithhilt.util.StepSensorManager


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
    fun providesSleepDao(database: AppDatabase): SleepDao {
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

    @Provides
    @Singleton
    fun providesSensorManager(@ApplicationContext context: Context): StepSensorManager {
        return StepSensorManager(context)
    }

    @Provides
    @Singleton
    fun providesNotificationManager(@ApplicationContext context: Context):NotificationManager{
        return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @Singleton
    fun providesNotificationBuilder(@ApplicationContext context: Context):NotificationCompat.Builder{
        return NotificationCompat.Builder(
            context,"default_channel"
        )
            .setSmallIcon(R.drawable.shoe_prints_svgrepo_com)
            .setPriority(NotificationCompat.PRIORITY_MAX)
    }

    @Provides
    @Singleton
    fun providesStepRepository(firebaseAuth: FirebaseAuth, databaseReference: DatabaseReference): StepRepository {
        return StepRepository(firebaseAuth,databaseReference)
    }

}