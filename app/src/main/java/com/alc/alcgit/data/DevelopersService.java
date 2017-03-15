package com.alc.alcgit.data;

import com.alc.alcgit.remote.GithubDevSearchAPI;
import com.alc.alcgit.remote.models.Developer;
import com.alc.alcgit.remote.models.SearchResults;
import com.alc.alcgit.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sureife on 10/03/2017.
 */

public  class DevelopersService {
    public static final String PER_PAGE = "100";

    public interface OnDevelopersLoadedListener{
        void onDevelopersLoaded(List<Developer> developers);

        void onDevelopersLoadFailure(String errorMessage);
    }

    public static void getDevelopers(String searchQualifiers, final OnDevelopersLoadedListener developersLoadedListener){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubDevSearchAPI apiService = retrofit.create(GithubDevSearchAPI.class);

        Call<SearchResults> call = apiService.getDevelopers(searchQualifiers,PER_PAGE,Constants.ACCESS_TOKEN);

        call.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                developersLoadedListener.onDevelopersLoaded(response.body().getDevelopers());
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                developersLoadedListener.onDevelopersLoadFailure(t.getMessage());
            }
        });
    }
}
