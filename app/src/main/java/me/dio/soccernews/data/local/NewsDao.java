package me.dio.soccernews.data.local;

import android.view.View;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import me.dio.soccernews.domain.News;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news WHERE isFavorte = :isFavorite")
    List<News> loadFavoriteNews(boolean... isFavorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(News news);
}
