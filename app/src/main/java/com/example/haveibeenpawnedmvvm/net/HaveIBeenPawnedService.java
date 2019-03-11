package com.example.haveibeenpawnedmvvm.net;

import com.example.haveibeenpawnedmvvm.model.HaveIBeenPawnedRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.haveibeenpawnedmvvm.net.Constants.END_POINT;

public interface HaveIBeenPawnedService {

    @GET(END_POINT)
    Call <List<HaveIBeenPawnedRepo>> getRepos(@Query("domain") String domain);

}
