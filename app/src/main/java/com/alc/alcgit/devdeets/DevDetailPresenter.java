package com.alc.alcgit.devdeets;

import android.net.Uri;

import com.alc.alcgit.remote.models.Developer;

/**
 * Created by sureife on 07/03/2017.
 */

public class DevDetailPresenter implements DevDetailContract.UserActionListener {
    private final DevDetailContract.View mDevDetailsView;

    public DevDetailPresenter(DevDetailContract.View view){
        mDevDetailsView = view;
    }

    @Override
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        mDevDetailsView.openInBrowser(webpage);
    }

    @Override
    public void openDevDetails(Developer developer) {
        mDevDetailsView.showProfilePhoto(developer.getProfilePicUrl());
        mDevDetailsView.showUsername(developer.getUsername());
        mDevDetailsView.showProfileUrl(developer.getGithubUrl());
    }

    @Override
    public void shareGithubProfile(String username, String profileUrl) {
        mDevDetailsView.launchShareIntent(username,profileUrl);
    }
}
