
package com.example.marvelheroes.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Data {

    @SerializedName("count")
    private String mCount;
    @SerializedName("limit")
    private String mLimit;
    @SerializedName("offset")
    private String mOffset;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("total")
    private String mTotal;

    public String getCount() {
        return mCount;
    }

    public void setCount(String count) {
        mCount = count;
    }

    public String getLimit() {
        return mLimit;
    }

    public void setLimit(String limit) {
        mLimit = limit;
    }

    public String getOffset() {
        return mOffset;
    }

    public void setOffset(String offset) {
        mOffset = offset;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public String getTotal() {
        return mTotal;
    }

    public void setTotal(String total) {
        mTotal = total;
    }

}
