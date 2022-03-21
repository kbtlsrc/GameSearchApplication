package com.example.gamesapplication.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gamesapplication.R;
import com.example.gamesapplication.data.all.Games;
import com.example.gamesapplication.data.search.SearchPart;
import com.example.gamesapplication.databinding.ItemGamesBinding;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GamesViewHolders> {
        private List<SearchPart> games;
        private Context context;
        private ItemClickListener itemClickListener;

        public GameAdapter(Context context){
            this.context = context;
            this.games = new ArrayList<>();
        }

        public void setAdapterList(List<SearchPart> games){
          //  this.games.clear();
            this.games.addAll(games);
            this.notifyDataSetChanged();
        }



    @NonNull
    @Override
    public GamesViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGamesBinding binding =ItemGamesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GamesViewHolders(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesViewHolders holder, int position) {
        SearchPart gameModel = games.get(position);
        if (!TextUtils.isEmpty(gameModel.getThumbnail()))
            Glide.with(context)
                    .load(gameModel.getThumbnail())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.binding.ivThumbnail);
        if (!TextUtils.isEmpty(gameModel.getTitle()))
            holder.binding.tvTitle.setText(gameModel.getTitle());
        if (!TextUtils.isEmpty(gameModel.getShortDescription()))
            holder.binding.tvShort.setText(gameModel.getShortDescription());
        if (!TextUtils.isEmpty(gameModel.getGenre()))
            holder.binding.tvGenre.setText(String.format("Genre: %s", gameModel.getGenre()));
        if (!TextUtils.isEmpty(gameModel.getPlatform()))
            holder.binding.tvPlatform.setText(String.format("Platform: %s", gameModel.getPlatform()));
        if (!TextUtils.isEmpty(gameModel.getPublisher()))
            holder.binding.tvPublisher.setText(String.format("Publisher: %s", gameModel.getPublisher()));
        if (!TextUtils.isEmpty(gameModel.getDeveloper()))
            holder.binding.tvDeveloper.setText(String.format("Developer: %s", gameModel.getDeveloper()));
        if (!TextUtils.isEmpty(gameModel.getReleaseDate()))
            holder.binding.tvRelease.setText(gameModel.getReleaseDate());
    }



    private SearchPart getItem(int pos){ return games.get(pos);}


    @Override
    public int getItemCount() {
        return games.size() ;
    }

    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onClick(int pos, SearchPart game);
    }


    public class GamesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

            ItemGamesBinding  binding;

        public GamesViewHolders(ItemGamesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
            
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(getAdapterPosition(), getItem(getAdapterPosition()));


        }
    }
}
