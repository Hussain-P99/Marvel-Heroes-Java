package com.example.marvelheroes.network;

import com.example.marvelheroes.models.CharacterResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("/v1/public/characters")
    Flowable<Response<CharacterResponse>> getCharacters(
            @Query("ts") String ts,
            @Query("apikey") String apiKey,
            @Query("hash") String hash,
            @Query("limit") int limit ,
            @Query("offset") int offset,
            @Query("nameStartsWith") String search
    );

//    @GET("/v1/public/characters/{characterId}")
//    suspend fun getCharacter(@Path("characterId") characterId: Int) : CharacterResponse?
}
