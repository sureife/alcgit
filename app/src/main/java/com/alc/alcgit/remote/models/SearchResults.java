package com.alc.alcgit.remote.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sureife on 10/03/2017.
 */

public class SearchResults {

    @SerializedName("total_count")
    private
    int totalCount;

    @SerializedName("items")
    private
    List<Developer> developers;

    public int getTotalCount() {
        return totalCount;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }
}
