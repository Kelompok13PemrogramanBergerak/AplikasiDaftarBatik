package com.example.aplikasidaftarbatik.roomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.models.BatikSlide;

import java.util.List;

public class BatikRepository {

    private BatikDao mBatikDao;
    private LiveData<List<Batik>> mAllBatik;
    private LiveData<List<BatikSlide>> mAllBatikPopular;
    private LiveData<List<Batik>> mSearchBatik;

    public BatikRepository(Application application) {
        BatikRoomDatabase db = BatikRoomDatabase.getDatabase(application);
        mBatikDao = db.batikDao();
        mAllBatik = mBatikDao.getAllBatik();
        mAllBatikPopular = mBatikDao.getAllBatikPopular();
    }

    LiveData<List<Batik>> getAllBatik() {
        return mAllBatik;
    }

    public void insert(List<Batik> batiks) {
        BatikRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBatikDao.insertAllBatik(batiks);
        });
    }

    LiveData<List<BatikSlide>> getAllBatikPopular() {
        return mAllBatikPopular;
    }

    public void insertPopular(List<BatikSlide> batikslide) {
        BatikRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBatikDao.insertAllBatikPopular(batikslide);
        });
    }

    LiveData<List<Batik>> searchBatik(String search) {
        return mBatikDao.searchBatik(search);
    }


}
