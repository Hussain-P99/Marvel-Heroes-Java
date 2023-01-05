package com.example.marvelheroes.di;


import com.example.marvelheroes.data.AppRepository;
import com.example.marvelheroes.data.AppRepositoryImpl;
import com.example.marvelheroes.data.local.CharacterLocalRepository;
import com.example.marvelheroes.data.local.CharacterLocalRepositoryImpl;
import com.example.marvelheroes.data.local.dao.CharacterDao;
import com.example.marvelheroes.data.remote.CharacterRemoteRepository;
import com.example.marvelheroes.data.remote.CharacterRemoteRepositoryImpl;
import com.example.marvelheroes.network.MarvelApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataSourceModule {

    @Provides
    @Singleton
    static AppRepository providesAppRepository(CharacterRemoteRepository remoteRepository, CharacterLocalRepository localRepository) {
        return new AppRepositoryImpl(remoteRepository,localRepository);
    }

    @Provides
    static CharacterRemoteRepository providesRemoteRepository(MarvelApi api) {
        return new CharacterRemoteRepositoryImpl(api);
    }

    @Provides
    static CharacterLocalRepository providesLocalRepository(CharacterDao dao) {
        return new CharacterLocalRepositoryImpl(dao);
    }

}
