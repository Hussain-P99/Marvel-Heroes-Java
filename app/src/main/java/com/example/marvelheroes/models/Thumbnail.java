
package com.example.marvelheroes.models;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Thumbnail {

    @SerializedName("extension")
    private String mExtension;
    @SerializedName("path")
    private String mPath;

    public String getExtension() {
        return mExtension;
    }

    public void setExtension(String extension) {
        mExtension = extension;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

}
