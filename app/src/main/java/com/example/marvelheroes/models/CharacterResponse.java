
package com.example.marvelheroes.models;

import com.example.marvelheroes.dto.Character;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class CharacterResponse {

    @SerializedName("attributionHTML")
    private String mAttributionHTML;
    @SerializedName("attributionText")
    private String mAttributionText;
    @SerializedName("code")
    private String mCode;
    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("data")
    private Data mData;
    @SerializedName("etag")
    private String mEtag;
    @SerializedName("status")
    private String mStatus;

    public String getAttributionHTML() {
        return mAttributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        mAttributionHTML = attributionHTML;
    }

    public String getAttributionText() {
        return mAttributionText;
    }

    public void setAttributionText(String attributionText) {
        mAttributionText = attributionText;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public String getEtag() {
        return mEtag;
    }

    public void setEtag(String etag) {
        mEtag = etag;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public List<Character> toDto() {
        List<Result> result = mData.getResults();
        List<Character> characters = new ArrayList<>();
        for (Result r : result) {
            characters.add(r.toDto());
        }
        return characters;
    }

}
