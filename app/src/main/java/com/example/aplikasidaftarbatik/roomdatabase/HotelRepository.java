package com.example.aplikasidaftarbatik.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarbatik.models.Hotel;

import java.util.List;

public class HotelRepository {

    private HotelDao mHotelDao;
    private LiveData<List<Hotel>> mAllHotel;

    public HotelRepository(Application application) {
        HotelRoomDatabase db = HotelRoomDatabase.getDatabase(application);
        mHotelDao = db.hotelDao();
        mAllHotel = mHotelDao.getAllHotel();
    }

    LiveData<List<Hotel>> getAllHotel() {
        return mAllHotel;
    }

    LiveData<List<Hotel>> getAllHotelSlide() {
        return mHotelDao.getAllHotelSlide();
    }

    LiveData<List<Hotel>> searchHotel(String search) {
        return mHotelDao.searchHotel(search);
    }

    public void insertHotel(List<Hotel> hotels) {
        HotelRoomDatabase.databaseWriteExecutor.execute(() -> {
            mHotelDao.insertAllHotel(hotels);
        });
    }

}
