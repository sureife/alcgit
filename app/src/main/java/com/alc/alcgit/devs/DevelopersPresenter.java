package com.alc.alcgit.devs;



import com.alc.alcgit.data.DevelopersService;
import com.alc.alcgit.remote.models.Developer;

import java.util.List;

/**
 * Created by sureife on 10/03/2017.
 */

public class DevelopersPresenter implements DevelopersContract.UserActionListener  {

    private DevelopersContract.View developersView;

    DevelopersPresenter(DevelopersContract.View developersView){
        this.developersView = developersView;
    }

    @Override
    public void showDevDetail(Developer dev) {
        developersView.openDevDetails(dev);
    }

    @Override
    public void loadDevelopers(String searchQualifiers) {
        if(developersView.checkNetworkConnection()){
            DevelopersService.getDevelopers(searchQualifiers,loadedListener);
        }else {
            developersView.showErrorMessage("No Internet Connectivity");
        }
    }

    private DevelopersService.OnDevelopersLoadedListener loadedListener = new DevelopersService.OnDevelopersLoadedListener() {
        @Override
        public void onDevelopersLoaded(List<Developer> developers) {
            developersView.hideProgressIndicator();
            developersView.showDevelopers(developers);
        }

        @Override
        public void onDevelopersLoadFailure(String errorMessage) {
            developersView.showErrorMessage(errorMessage);
        }
    };
}
