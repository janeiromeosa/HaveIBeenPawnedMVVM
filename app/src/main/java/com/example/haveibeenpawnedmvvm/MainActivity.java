package com.example.haveibeenpawnedmvvm;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haveibeenpawnedmvvm.home.HaveIBeenPawnedAdapter;
import com.example.haveibeenpawnedmvvm.home.HomeViewModel;
import com.example.haveibeenpawnedmvvm.model.HaveIBeenPawnedRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnGetResult);
        RecyclerView recyclerView = findViewById(R.id.rvData);

        final EditText etDomain = findViewById(R.id.etDomain);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration
                (this, linearLayoutManager.getOrientation()));

        final HaveIBeenPawnedAdapter haveIBeenPawnedAdapter = new HaveIBeenPawnedAdapter();
        recyclerView.setAdapter(haveIBeenPawnedAdapter);

        final HomeViewModel homeViewModel = new HomeViewModel();
        homeViewModel.getResponseLiveData().observe(this, new Observer<List<HaveIBeenPawnedRepo>>() {
            @Override
            public void onChanged(@Nullable List<HaveIBeenPawnedRepo> haveIBeenPawnedRepos) {
                haveIBeenPawnedAdapter.setData(haveIBeenPawnedRepos);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeViewModel.getDomain(etDomain.getText().toString());
            }
        });
    }
}
