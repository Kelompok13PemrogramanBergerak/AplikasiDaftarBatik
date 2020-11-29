package com.example.aplikasidaftarbatik.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarbatik.models.Hotel;

import java.util.List;

public class HotelViewModel extends AndroidViewModel {

    private HotelRepository mRepository;
    private LiveData<List<Hotel>> mAllHotel;

    public HotelViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HotelRepository(application);
        mAllHotel = mRepository.getAllHotel();
    }


    public LiveData<List<Hotel>> getAllHotel() {
        return mAllHotel;
    }

    public LiveData<List<Hotel>> getAllHotelSlide() {
        return mRepository.getAllHotelSlide();
    }

    public LiveData<List<Hotel>> searchHotel(String search) {
        return mRepository.searchHotel(search);
    }

    public void insertHotel(List<Hotel> hotels) {
        mRepository.insertHotel(hotels);
    }

}
