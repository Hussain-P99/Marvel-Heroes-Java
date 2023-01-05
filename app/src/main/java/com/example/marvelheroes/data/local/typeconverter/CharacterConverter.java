package com.example.marvelheroes.data.local.typeconverter;

import androidx.room.TypeConverter;

import com.example.marvelheroes.models.Comics;
import com.example.marvelheroes.models.Events;
import com.example.marvelheroes.models.Item;
import com.example.marvelheroes.models.Series;
import com.example.marvelheroes.models.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CharacterConverter {

    Type typeToken = TypeToken.getParameterized(List.class, Item.class).getType();

    @TypeConverter
    public String itemToString(List<Item> comic) {
        Gson gson = new Gson();
        return gson.toJson(comic);
    }

    @TypeConverter
    public List<Item> stringToItem(String series) {
        return new Gson().fromJson(series, typeToken);
    }

    @TypeConverter
    public String urlsToString(List<Url> urls) {
        Gson gson = new Gson();
        return gson.toJson(urls);
    }

    @TypeConverter
    public List<Url> stringToUrls(String urls) {
        Type typeToken = TypeToken.getParameterized(List.class, Url.class).getType();
        return new Gson().fromJson(urls, typeToken);
    }

}
