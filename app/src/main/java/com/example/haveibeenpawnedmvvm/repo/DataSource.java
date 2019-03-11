package com.example.haveibeenpawnedmvvm.repo;

import java.util.Observer;

public interface DataSource {
    void getDataForPawnedSite(String domain);
    void setObserver(Observer observer);
}


