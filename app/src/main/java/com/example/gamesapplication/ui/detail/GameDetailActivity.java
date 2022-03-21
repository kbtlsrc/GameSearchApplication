package com.example.gamesapplication.ui.detail;

import static com.example.gamesapplication.utils.Constants.CUSTOM_TAG;
import static com.example.gamesapplication.utils.Constants.GAME_ID;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.gamesapplication.R;
import com.example.gamesapplication.data.all.Games;
import com.example.gamesapplication.databinding.ActivityGameDetailBinding;
import com.example.gamesapplication.utils.Constants;

public class GameDetailActivity extends AppCompatActivity {
        private ActivityGameDetailBinding binding;
        private GameDetailViewModel mviewModel;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding = ActivityGameDetailBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

                mviewModel = ViewModelProviders.of(this).get(GameDetailViewModel.class);

                initObservers();
                //checkArguments();
                getGameDetail();

        }

                /*  private void checkArguments() {
                        Bundle bundle = getIntent().getExtras();
                        if (bundle != null) {
                                int gameId = bundle.getInt(Constants.GAME_ID);
                                mviewModel.getGameDetail(gameId);
                        }
                        else{
                                finish();
                                Toast.makeText(this, "Game not found.", Toast.LENGTH_SHORT).show();
                        }
                }*/


        private void initObservers(){
                //mviewModel.getGameDetail().observe(this, this::prepareView);
               mviewModel.getGameDetail().observe(this, new Observer<Games>() {
                        @Override
                        public void onChanged(Games games) {
                                Log.d(CUSTOM_TAG, "onChanged: ");
                                prepareView(games);
                        }
                });
        }


       private void getGameDetail(){
                int gameId = 0;
               //region get data from previous Activity
                Bundle extras =getIntent().getExtras();
                if(extras != null){
                        gameId = extras.getInt(GAME_ID, 0);
                }

                if(gameId != 0 )
                        mviewModel.getGameDetail(gameId);
                else
                        Toast.makeText(this, "Game not found.", Toast.LENGTH_SHORT).show();
        }

        private void prepareView(Games game){

                if(!TextUtils.isEmpty(game.getThumbnail()))
                {
                        Glide.with(this)
                                .load(game.getThumbnail())
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(binding.iGame.ivThumbnail);
                }
                if (!TextUtils.isEmpty(game.getTitle()))
                        binding.iGame.tvTitle.setText(game.getTitle());
                if (!TextUtils.isEmpty(game.getShortDescription()))
                        binding.iGame.tvShort.setText(game.getShortDescription());
                if (!TextUtils.isEmpty(game.getGenre()))
                        binding.iGame.tvGenre.setText(String.format("Genre: %s", game.getGenre()));
                if (!TextUtils.isEmpty(game.getPlatform()))
                        binding.iGame.tvPlatform.setText(String.format("Platform: %s", game.getPlatform()));
                if (!TextUtils.isEmpty(game.getPublisher()))
                        binding.iGame.tvPublisher.setText(String.format("Publisher: %s", game.getPublisher()));
                if (!TextUtils.isEmpty(game.getDeveloper()))
                        binding.iGame.tvDeveloper.setText(String.format("Developer: %s", game.getDeveloper()));
                if (!TextUtils.isEmpty(game.getReleaseDate()))
                        binding.iGame.tvRelease.setText(game.getReleaseDate());

                if (game.getScreenshots().size() > 0) {
                        try {
                                if (!TextUtils.isEmpty(game.getScreenshots().get(0).getImage()))
                                        Glide.with(this)
                                                .load(game.getScreenshots().get(0).getImage())
                                                .placeholder(R.drawable.ic_launcher_background)
                                                .into(binding.ivScreenshot);
                        } catch (Exception e) {
                                Log.d(CUSTOM_TAG, "prepareView: " + e.toString());
                        }
                }


        }
}
