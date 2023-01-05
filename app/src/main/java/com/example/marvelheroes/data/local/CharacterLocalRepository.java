package com.example.marvelheroes.data.local;

import com.example.marvelheroes.dto.Character;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface CharacterLocalRepository {


    Flowable<List<Character>> getAllCharacters();

    Completable updateCharacter(Character character);

    void addCharacter(Character character);

    void addAllCharacters(List<Character> characters);

    Flowable<Character> getCharacterById(String characterId);

    Flowable<List<Character>> getBookmarkedCharacters();

}
