package com.example.marvelheroes.data.remote;


import com.example.marvelheroes.models.CharacterResponse;
import com.example.marvelheroes.network.MarvelApi;
import com.example.marvelheroes.utils.Constants;
import com.example.marvelheroes.utils.Utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import retrofit2.Response;

@Singleton
public class CharacterRemoteRepositoryImpl implements CharacterRemoteRepository {

    private MarvelApi marvelApi;

    @Inject
    public CharacterRemoteRepositoryImpl(MarvelApi api) {
        this.marvelApi = api;
    }

    @Override
    public Flowable<Response<CharacterResponse>> getCharacters(String searchText) {
        String ts = String.valueOf(System.currentTimeMillis());
        String hash = Utils.calculateMd5Sum(ts, Constants.PUB_KEY,Constants.PRIV_KEY);
        return marvelApi.getCharacters(ts,Constants.PUB_KEY,hash,100,0,searchText);
    }
}
