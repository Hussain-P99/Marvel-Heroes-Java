package com.example.marvelheroes.di;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.marvelheroes.data.local.dao.CharacterDao;
import com.example.marvelheroes.data.local.db.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalDbModule {

    @Provides
    @Singleton
    static AppDatabase providesAppDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,AppDatabase.class,"Marvels Database").build();
    }

    @Provides
    static CharacterDao providesCharacterDao(AppDatabase appDatabase) {
        return appDatabase.characterDao();
    }
}
