package com.alc.alcgit.remote;



import com.alc.alcgit.remote.models.Developer;
import com.alc.alcgit.remote.models.SearchResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sureife on 10/03/2017.
 */

public interface GithubDevSearchAPI {

    @GET("/search/users")
    Call<SearchResults> getDevelopers(@Query(value = "q",encoded = true) String userSearchQualifiers,@Query("per_page") String pageCount, @Query(value ="access_token",encoded = true) String token);


}
