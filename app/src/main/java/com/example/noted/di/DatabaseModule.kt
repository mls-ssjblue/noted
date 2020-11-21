package com.example.noted.di;


import android.content.Context
import com.example.noted.data.AppDatabase
import com.example.noted.data.NoteDao
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase) :NoteDao {
        return appDatabase.noteDao()
    }

}
