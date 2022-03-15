package com.example.gamesapplication.ui.main;

import static com.example.gamesapplication.utils.Constants.CUSTOM_TAG;
import static com.example.gamesapplication.utils.Constants.GAME_ID;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.gamesapplication.adapter.GameAdapter;
import com.example.gamesapplication.data.search.SearchPart;
import com.example.gamesapplication.databinding.ActivityMainBinding;
import com.example.gamesapplication.ui.detail.GameDetailActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel mViewModel;
    private GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        this.initComponents();
        this.initClicks();
        this.initObservers();
        this.observeGameData();


    }


    private void initComponents(){

        binding.rvGames.setLayoutManager(new LinearLayoutManager(this));
        binding.rvGames.setItemAnimator(new DefaultItemAnimator());
        gameAdapter = new GameAdapter(this);
        binding.rvGames.setAdapter(gameAdapter);

    }


    private void initClicks(){
        gameAdapter.setOnClickListener((pos, searchPart) ->{
            Log.d(CUSTOM_TAG, "onClick: ");

            Intent intent = new Intent(MainActivity.this, GameDetailActivity.class);
            intent.putExtra(GAME_ID, searchPart.getId());
            startActivity(intent);
        } );
    }


    private void initObservers(){
        mViewModel.getGameList().observe(this, new Observer<List<SearchPart>>() {
            @Override
            public void onChanged(List<SearchPart> searchParts) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareRecycler(searchParts);
            }
        });
    }

    private void observeGameData() {
        mViewModel.getGamesData();
    }

    private void prepareRecycler(List<SearchPart> models) {
        gameAdapter.setAdapterList(models);
    }
}