package com.example.marvelheroes.data.local.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.marvelheroes.data.local.dao.CharacterDao;
import com.example.marvelheroes.data.local.typeconverter.CharacterConverter;
import com.example.marvelheroes.dto.Character;

@Database(entities = {Character.class}, version = 1,exportSchema = false)
@TypeConverters(CharacterConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}
