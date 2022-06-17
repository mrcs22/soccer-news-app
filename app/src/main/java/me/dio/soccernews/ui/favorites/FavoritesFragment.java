package me.dio.soccernews.ui.favorites;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.dio.soccernews.MainActivity;
import me.dio.soccernews.databinding.FragmentFavoritesBinding;
import me.dio.soccernews.domain.News;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel =
                new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.loadFavorteNews();

        return root;
    }

    private void loadFavorteNews() {
        MainActivity mainActivity = (MainActivity) getActivity();
        if(mainActivity != null){
            List<News> favoriteNews = mainActivity.getDb().newsDao().loadFavoriteNews(true);
            binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
                 mainActivity.getDb().newsDao().save(updatedNews);
                loadFavorteNews();
            }));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}