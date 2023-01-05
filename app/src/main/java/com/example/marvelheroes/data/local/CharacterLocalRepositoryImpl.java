package com.example.marvelheroes.data.local;

import android.annotation.SuppressLint;

import com.example.marvelheroes.data.local.dao.CharacterDao;
import com.example.marvelheroes.dto.Character;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class CharacterLocalRepositoryImpl implements CharacterLocalRepository {

    private final CharacterDao characterDao;

    @Inject
    public CharacterLocalRepositoryImpl(CharacterDao dao) {
        this.characterDao = dao;
    }

    @Override
    public Flowable<List<Character>> getAllCharacters() {
        return characterDao.getAllCharacters();
    }

    @SuppressLint("CheckResult")
    @Override
    public Completable updateCharacter(Character character) {
        return characterDao.update(character);
    }

    @Override
    public void addCharacter(Character character) {
        characterDao.insert(character).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public void addAllCharacters(List<Character> characters) {
        characterDao.insertAll(characters).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    @Override
    public Flowable<Character> getCharacterById(String characterId) {
        return characterDao.getCharacterById(characterId);
    }

    @Override
    public Flowable<List<Character>> getBookmarkedCharacters() {
        return characterDao.getBookmarkedCharacters();
    }
}
