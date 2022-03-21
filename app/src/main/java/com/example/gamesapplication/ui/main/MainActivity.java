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
import android.widget.Toast;

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
       // binding.btnSearch.setOnClickListener(v -> mViewModel.search(binding.editSearch.getText().toString()));
        gameAdapter.setOnClickListener((pos, game) ->{


            Intent intent = new Intent(MainActivity.this, GameDetailActivity.class);
            intent.putExtra(GAME_ID, game.getId());
            startActivity(intent);
        } );
    }


    private void initObservers(){

       // mViewModel.getGameList().observe(this, this::prepareRecycler);

        mViewModel.getGameList().observe(this, new Observer<List<SearchPart>>() {
            @Override
            public void onChanged(List<SearchPart> gameModels) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareRecycler(gameModels);
            }
        });
        /*mViewModel.getSearchControl().observe(this, aBoolean -> {
            if (aBoolean)
                Toast.makeText(this, "You should enter at least one letter", Toast.LENGTH_SHORT).show();
        });*/

    }



    private void observeGameData() {
        mViewModel.getGamesData();
    }

    private void prepareRecycler(List<SearchPart> models) {
        gameAdapter.setAdapterList(models);
    }
}