
package com.example.marvelheroes.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Events {

    @SerializedName("available")
    private String mAvailable;
    @SerializedName("collectionURI")
    private String mCollectionURI;
    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("returned")
    private String mReturned;

    public String getAvailable() {
        return mAvailable;
    }

    public void setAvailable(String available) {
        mAvailable = available;
    }

    public String getCollectionURI() {
        return mCollectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        mCollectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        mItems = items;
    }

    public String getReturned() {
        return mReturned;
    }

    public void setReturned(String returned) {
        mReturned = returned;
    }

}
