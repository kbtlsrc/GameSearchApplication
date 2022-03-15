package com.example.gamesapplication.ui.detail;

import static com.example.gamesapplication.utils.Constants.CUSTOM_TAG;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamesapplication.data.all.Games;
import com.example.gamesapplication.services.ClientGames;
import com.example.gamesapplication.services.IRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameDetailViewModel extends ViewModel {


        private MutableLiveData<Games> gameDetail =new MutableLiveData<>();

        public void getGameDetail(int gameId){
                IRequest request = ClientGames.getApiClient().create(IRequest.class);
                Call<Games> call =request.getGameDetail(gameId);
                call.enqueue(new Callback<Games>() {
                        @Override
                        public void onResponse(Call<Games> call, Response<Games> response) {
                                if(response.isSuccessful()){
                                        gameDetail.postValue(response.body());
                                }
                        }

                        @Override
                        public void onFailure(Call<Games> call, Throwable t) {
                                Log.d(CUSTOM_TAG, "onFailure: ");

                        }
                });


}

        public MutableLiveData<Games> getGameDetail() {
                return gameDetail;
        }
}
