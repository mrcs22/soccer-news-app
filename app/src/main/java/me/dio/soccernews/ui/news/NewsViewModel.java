package me.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import me.dio.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        List<News> news = new ArrayList<>();

        //TODO remover mock de notícias
        news.add(new News("Ferrovária tem desfalque importante","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Ferrinha Joga no sábado","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));
        news.add(new News("Copa do mundo feminina está terminando","Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}