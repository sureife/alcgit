package com.alc.alcgit.devs;

/**
 * Created by sureife on 06/03/2017.
 */



import com.alc.alcgit.remote.models.Developer;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
public class DevelopersContract {
    interface View{
        void showDevelopers(List<Developer> developers);

        void openDevDetails(Developer developer);

        void hideProgressIndicator();

        boolean checkNetworkConnection();

        void showErrorMessage(String errorMessage);
    }

    interface UserActionListener{
        void showDevDetail(Developer developer);

        void loadDevelopers(String searchQualifiers);
    }
}
