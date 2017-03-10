package com.alc.alcgit.remote;



import com.alc.alcgit.remote.models.Developer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sureife on 10/03/2017.
 */

public interface GithubDevSearchAPI {

    @GET("/search/users")
    Call<List<Developer>> getDevelopers(@Query("q") String userSearchQualifiers, @Query("access_token") String token);


}
