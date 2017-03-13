package com.alc.alcgit.devdeets;

/**
 * Created by sureife on 06/03/2017.
 */

import android.net.Uri;

import com.alc.alcgit.remote.models.Developer;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface DevDetailContract {
    interface View{
        void openInBrowser(Uri pageUri);

        void showProfileUrl(String profileUrl);

        void showProfilePhoto(String profilePhotoUrl);

        void showUsername(String username);

        void launchShareIntent(String username,String profileUrl);

    }

    interface UserActionListener{
        void openWebPage(String url);

        void openDevDetails(Developer developer);

        void shareGithubProfile(String username,String profileUrl);
    }
}
