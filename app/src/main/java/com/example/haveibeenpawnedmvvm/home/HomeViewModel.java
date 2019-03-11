package com.example.haveibeenpawnedmvvm.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.haveibeenpawnedmvvm.model.HaveIBeenPawnedRepo;
import com.example.haveibeenpawnedmvvm.repo.DataSource;
import com.example.haveibeenpawnedmvvm.repo.HaveIBeenPawnedRepository;
import com.example.haveibeenpawnedmvvm.repo.LocalDataSource;
import com.example.haveibeenpawnedmvvm.repo.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HomeViewModel implements Observer {

    private final DataSource haveIBeenPawnedRepository;
    private final MutableLiveData<List<HaveIBeenPawnedRepo>> liveData = new MutableLiveData<>();

    public HomeViewModel() {
        haveIBeenPawnedRepository = new HaveIBeenPawnedRepository(new LocalDataSource(), new RemoteDataSource());
    }

    @Override
    public void update(Observable observable, Object result) {
        List<HaveIBeenPawnedRepo> haveIBeenPawnedRepos = (List<HaveIBeenPawnedRepo>) result;
        liveData.setValue(haveIBeenPawnedRepos);
    }

    public LiveData<List<HaveIBeenPawnedRepo>> getResponseLiveData(){
        return liveData;
    }

    public void getDomain(String domain){
        haveIBeenPawnedRepository.setObserver(this);
        haveIBeenPawnedRepository.getDataForPawnedSite(domain);
    }
}
