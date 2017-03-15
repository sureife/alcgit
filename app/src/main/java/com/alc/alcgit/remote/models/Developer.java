package com.alc.alcgit.remote.models;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sureife on 10/03/2017.
 */

public class Developer implements Serializable{

    @SerializedName("login")
    private
    String username;

    @SerializedName("avatar_url")
    private
    String profilePicUrl;

    @SerializedName("html_url")
    private
    String githubUrl;

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public String getUsername() {
        return username;
    }
}
