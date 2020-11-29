package com.example.aplikasidaftarbatik.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.aplikasidaftarbatik.models.Hotel;

import java.util.List;

@Dao
public interface HotelDao {

    @Query("SELECT * from hotel_table")
    LiveData<List<Hotel>> getAllHotel();

    @Query("SELECT * from hotel_table ORDER BY RANDOM() LIMIT 6")
    LiveData<List<Hotel>> getAllHotelSlide();

    @Query("SELECT * from hotel_table WHERE nama LIKE '%' || :search || '%'")
    LiveData<List<Hotel>> searchHotel(String search);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllHotel(List<Hotel> hotels);

    @Query("DELETE FROM hotel_table")
    void deleteAllHotel();

}
