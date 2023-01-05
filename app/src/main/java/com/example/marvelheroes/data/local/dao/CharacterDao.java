package com.example.marvelheroes.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.marvelheroes.dto.Character;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Character character);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertAll(List<Character> characters);

    @Update
    Completable update(Character character);

    @Query("SELECT * FROM character")
    Flowable<List<Character>> getAllCharacters();

    @Query("SELECT * FROM character WHERE characterId = :id")
    Flowable<Character> getCharacterById(String id);

    @Query("SELECT * FROM character WHERE isBookmarked == 1")
    Flowable<List<Character>> getBookmarkedCharacters();

}