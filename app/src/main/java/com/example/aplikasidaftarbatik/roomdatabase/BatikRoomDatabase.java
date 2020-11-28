package com.example.aplikasidaftarbatik.roomdatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.models.BatikSlide;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Batik.class, BatikSlide.class}, version = 1, exportSchema = false)
public abstract class BatikRoomDatabase extends RoomDatabase {


    public abstract BatikDao batikDao();

    private static volatile BatikRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static BatikRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (BatikRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BatikRoomDatabase.class, "batik_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }



    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                BatikDao dao = INSTANCE.batikDao();
                dao.deleteAll();
                dao.deleteAllPopular();


            });
        }
    };





}
