package com.example.marvelheroes.ui;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.marvelheroes.data.AppRepository;
import com.example.marvelheroes.dto.Character;
import com.example.marvelheroes.models.CharacterResponse;
import com.example.marvelheroes.network.MarvelApi;
import com.example.marvelheroes.utils.Constants;
import com.example.marvelheroes.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {


    private AppRepository appRepository;

    @Inject
    public MainViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public void getCharacters(boolean shouldRefresh) {
        appRepository.getAllCharacters(shouldRefresh);
    }

    public LiveData<List<Character>> observeCharacter() {
        return appRepository.observeCharacters();
    }

    public LiveData<String> observeErrors() {
        return  appRepository.observeErrors();
    }

    public void searchCharacter(String searchString) {
        appRepository.searchCharacter(searchString);
    }

    public void bookmarkCharacter(Character character) {
        appRepository.bookmarkCharacter(character);
    }

    public void getBookmarkedCharacters() {
        appRepository.getBookmarkedCharacters();
    }
}
