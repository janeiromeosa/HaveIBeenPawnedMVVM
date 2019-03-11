package com.example.haveibeenpawnedmvvm.repo;

import com.example.haveibeenpawnedmvvm.model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawnedmvvm.net.Constants;
import com.example.haveibeenpawnedmvvm.net.HaveIBeenPawnedService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final HaveIBeenPawnedService haveIBeenPawnedService;

    public RemoteDataSource() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        haveIBeenPawnedService = retrofit.create(HaveIBeenPawnedService.class);

        HaveIBeenPawnedService haveIBeenPawnedService = retrofit.create(HaveIBeenPawnedService.class);
    }


    @Override
    public void getDataForPawnedSite(String domain) {
        final List<HaveIBeenPawnedRepo> haveIBeenPawnedRepos = new ArrayList<>();

        haveIBeenPawnedService.getRepos(domain).enqueue(new Callback<List<HaveIBeenPawnedRepo>>() {

            @Override
            public void onResponse(Call<List<HaveIBeenPawnedRepo>> call, Response<List<HaveIBeenPawnedRepo>> response) {
                if(response.isSuccessful() && response.body() != null){
                    haveIBeenPawnedRepos.clear();
                    haveIBeenPawnedRepos.addAll(response.body());
                    setChanged();
                    notifyObservers(haveIBeenPawnedRepos);
                }
            }

            @Override
            public void onFailure(Call<List<HaveIBeenPawnedRepo>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}
