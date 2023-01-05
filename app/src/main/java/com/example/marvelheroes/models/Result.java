
package com.example.marvelheroes.models;

import java.util.List;
import com.example.marvelheroes.dto.Character;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Result {

    @SerializedName("comics")
    private Comics mComics;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("events")
    private Events mEvents;
    @SerializedName("id")
    private String mId;
    @SerializedName("modified")
    private String mModified;
    @SerializedName("name")
    private String mName;
    @SerializedName("resourceURI")
    private String mResourceURI;
    @SerializedName("series")
    private Series mSeries;
    @SerializedName("stories")
    private Stories mStories;
    @SerializedName("thumbnail")
    private Thumbnail mThumbnail;
    @SerializedName("urls")
    private List<Url> mUrls;

    public Comics getComics() {
        return mComics;
    }

    public void setComics(Comics comics) {
        mComics = comics;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Events getEvents() {
        return mEvents;
    }

    public void setEvents(Events events) {
        mEvents = events;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getResourceURI() {
        return mResourceURI;
    }

    public void setResourceURI(String resourceURI) {
        mResourceURI = resourceURI;
    }

    public Series getSeries() {
        return mSeries;
    }

    public void setSeries(Series series) {
        mSeries = series;
    }

    public Stories getStories() {
        return mStories;
    }

    public void setStories(Stories stories) {
        mStories = stories;
    }

    public Thumbnail getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        mThumbnail = thumbnail;
    }

    public List<Url> getUrls() {
        return mUrls;
    }

    public void setUrls(List<Url> urls) {
        mUrls = urls;
    }

    public Character toDto() {
        return new Character(getId(),getName(),getDescription(),getComics().getItems(),getSeries().getItems(),getEvents().getItems(),getUrls(),getThumbnail().getPath()+"."+getThumbnail().getExtension(),false);
    }

}
