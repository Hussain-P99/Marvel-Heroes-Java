package com.example.marvelheroes.dto;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.marvelheroes.models.Comics;
import com.example.marvelheroes.models.Events;
import com.example.marvelheroes.models.Item;
import com.example.marvelheroes.models.Series;
import com.example.marvelheroes.models.Url;

import java.util.List;


@Entity(tableName = "character")
public class Character {

    @PrimaryKey
    @NonNull
    private String characterId;

    private String name;

    private String description;

    public List<Item> getComics() {
        return comics;
    }

    public void setComics(List<Item> comics) {
        this.comics = comics;
    }

    public List<Item> getSeries() {
        return series;
    }

    public void setSeries(List<Item> series) {
        this.series = series;
    }

    public List<Item> getEvents() {
        return events;
    }

    public void setEvents(List<Item> events) {
        this.events = events;
    }

    private List<Item> comics;

    private List<Item> series;

    private List<Item> events;

    private List<Url> urls;

    private String thumbnail;

    private Boolean isBookmarked;

    public Character(@NonNull String characterId, String name, String description, List<Item> comics, List<Item> series, List<Item> events, List<Url> urls, String thumbnail, Boolean isBookmarked) {
        this.characterId = characterId;
        this.name = name;
        this.description = description;
        this.comics = comics;
        this.series = series;
        this.events = events;
        this.urls = urls;
        this.thumbnail = thumbnail;
        this.isBookmarked = isBookmarked;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(@NonNull String characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Boolean getBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(Boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
