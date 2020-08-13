package com.elegion.test.behancer.data_utils.api;

import com.example.domain.model.project.ProjectResponse;
import com.example.domain.model.user.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BehanceApi {

    @GET("v2/projects")
    Single<ProjectResponse> getProjects(@Query("q") String query);

    @GET("v2/users/{username}")
    Single<UserResponse> getUserInfo(@Path("username") String username);

    @GET("v2/users/{user}/projects")
    Single<ProjectResponse> getUserProjects(@Path("user") String username);
}
