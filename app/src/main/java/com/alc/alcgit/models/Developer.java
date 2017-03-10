package com.alc.alcgit.models;

import android.graphics.Bitmap;

/**
 * Created by sureife on 10/03/2017.
 */

public class Developer {
    private String username;

    private Bitmap profilePhoto;

    private String githubUrl;


    public Developer(String username, Bitmap profilePhoto, String githubUrl) {
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.githubUrl = githubUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public Bitmap getProfilePhoto() {
        return profilePhoto;
    }

    public String getUsername() {
        return username;
    }
}
