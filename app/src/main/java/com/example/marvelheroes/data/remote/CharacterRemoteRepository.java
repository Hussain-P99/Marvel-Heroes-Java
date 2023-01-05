package com.example.marvelheroes.data.remote;

import com.example.marvelheroes.models.CharacterResponse;

import io.reactivex.Flowable;
import retrofit2.Response;

public interface CharacterRemoteRepository {
    Flowable<Response<CharacterResponse>> getCharacters(String searchText);
}
