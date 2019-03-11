package com.example.haveibeenpawnedmvvm.repo;

import java.util.Observable;
import java.util.Observer;

public class HaveIBeenPawnedRepository extends Observable implements Observer, DataSource {

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public HaveIBeenPawnedRepository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getDataForPawnedSite(String domain) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getDataForPawnedSite(domain);
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }

    @Override
    public void update(Observable o, Object result) {
        setChanged();
        notifyObservers(result);
    }
}
