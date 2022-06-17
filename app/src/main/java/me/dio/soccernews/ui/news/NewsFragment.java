package me.dio.soccernews.ui.news;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import me.dio.soccernews.MainActivity;
import me.dio.soccernews.R;
import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if(activity != null){
                   activity.getDb().newsDao().save(updatedNews) ;
                }

            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case DOING:
                    break;
                case DONE:
                    binding.pbLoading.setVisibility(View.GONE);
                    break;

                case ERROR:
                    showMessage(getString(R.string.txt_connection_error));
                    binding.tvNoNetwork.setVisibility(View.VISIBLE);
                    break;
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showMessage(String message){
        binding.pbLoading.setVisibility(View.GONE);

        Snackbar.make(binding.rvNews, message, Snackbar.LENGTH_LONG)
                .setTextColor(Color.RED)
                .show();
    }
}