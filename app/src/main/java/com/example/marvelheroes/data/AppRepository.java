package com.example.marvelheroes.data;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.marvelheroes.dto.Character;

import java.util.List;

import io.reactivex.Flowable;

public interface AppRepository {

    void getAllCharacters(boolean shouldRefresh);

    void updateCharacter(Character character);

    void addCharacter(Character character);

    void addAllCharacters(List<Character> characters);

    void searchCharacter(String searchText);

    void getCharacterById(String characterId);

    void getBookmarkedCharacters();

    void bookmarkCharacter(Character character);

    LiveData<List<Character>> observeCharacters();

    LiveData<String> observeErrors();

}
