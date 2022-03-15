package com.example.gamesapplication.ui.main;

import static com.example.gamesapplication.utils.Constants.CUSTOM_TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamesapplication.data.search.SearchPart;
import com.example.gamesapplication.services.ClientGames;
import com.example.gamesapplication.services.IRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<SearchPart>> gameList = new MutableLiveData<>();
    private MutableLiveData<Boolean>searchControl =new MutableLiveData<>();

    public void search(String query){
        if(!query.isEmpty()){
            searchControl.postValue(false);
            getGamesData(query);
        }
        else
            searchControl.postValue(true);
    }



    public void getGamesData(String query){
        IRequest request = ClientGames.getApiClient().create(IRequest.class);
        Call<List<SearchPart>> call = request.getGameList("https://www.freetogame.com/api/", query);
        call.enqueue(new Callback<List<SearchPart>>() {
            @Override
            public void onResponse(Call<List<SearchPart>> call, Response<List<SearchPart>> response) {
                if(response.isSuccessful())
                gameList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SearchPart>> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");

            }
        });
    }

    public LiveData<List<SearchPart>> getGameList() {
        return gameList;
    }

    public MutableLiveData<Boolean> getSearchControl() {
        return searchControl;
    }
}
