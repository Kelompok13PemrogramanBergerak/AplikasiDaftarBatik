package com.example.aplikasidaftarbatik.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.models.BatikSlide;

import java.util.List;

public class BatikViewModel extends AndroidViewModel {

    private BatikRepository mRepository;
    private LiveData<List<Batik>> mAllBatik;
    private LiveData<List<BatikSlide>> mAllBatikPopular;

    public BatikViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BatikRepository(application);
        mAllBatik = mRepository.getAllBatik();
        mAllBatikPopular = mRepository.getAllBatikPopular();
    }

    public LiveData<List<Batik>> getAllBatik() {
        return mAllBatik;
    }

    public LiveData<List<BatikSlide>> getAllBatikPopular() {
        return mAllBatikPopular;
    }

    public void insert(List<Batik> batiks) {
        mRepository.insert(batiks);
    }

    public void insertPopular(List<BatikSlide> batikslide) {
        mRepository.insertPopular(batikslide);
    }

    public LiveData<List<Batik>> searchBatik(String search) {
        return mRepository.searchBatik(search);
    }


}
