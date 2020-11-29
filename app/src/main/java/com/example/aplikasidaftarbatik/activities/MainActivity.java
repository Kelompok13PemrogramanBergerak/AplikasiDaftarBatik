package com.example.aplikasidaftarbatik.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.example.aplikasidaftarbatik.API.ApiData;
import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.models.BatikSlide;
import com.example.aplikasidaftarbatik.R;
import com.example.aplikasidaftarbatik.adapters.BatikAdapter;
import com.example.aplikasidaftarbatik.adapters.BatikSliderAdapter;
import com.example.aplikasidaftarbatik.roomdatabase.BatikViewModel;
import com.example.aplikasidaftarbatik.utilities.CheckInternet;
import com.example.aplikasidaftarbatik.utilities.HitungWaktuOut;
import com.example.aplikasidaftarbatik.utilities.TimeAnimation;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String WAKTU_KELUAR = "waktu_keluar";

    //recyclerView
    BatikAdapter batikAdapter;
    RecyclerView idRecyclerView;

    //Slider layout
    SliderView sliderView;
    BatikSliderAdapter SlideAdapter;

    // Refresh layout
    TextView labelNoInternet;
    Button refreshButton;

    //Search layout
    SearchView searchView;

    //ViewModel
    BatikViewModel mBatikViewModel;

    //InternetConnection
    CheckInternet checkInternet;
    ApiData apiData;

    //Untuk menampilkan durasi keluar aplikasi
    TextView textWaktu;
    SimpleDateFormat dateFormat;
    String ambilWaktuKeluar;
    String ambilWaktuMasuk;

    private SharedPreferences mPreferences;

    private String sharedPrefFile =
            "com.example.android.hellosharedprefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idRecyclerView = findViewById(R.id.idRecyclerView);
        sliderView = findViewById(R.id.imageSlider);
        labelNoInternet = findViewById(R.id.label_no_internet);
        refreshButton = findViewById(R.id.refresh_button);
        searchView = findViewById(R.id.kolomcari);
        textWaktu = findViewById(R.id.label_waktu);

        //RecycleView Batik
        batikAdapter = new BatikAdapter(this);
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerView.setAdapter(batikAdapter);

        //Slider Batik
        SlideAdapter = new BatikSliderAdapter(this);
        sliderView.setSliderAdapter(SlideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();

        //Inisialisasi Fast Android Networking Library untuk panggil API
        AndroidNetworking.initialize(getApplicationContext());

        //Check Internet
        checkInternet = new CheckInternet(getApplicationContext());

        //inisialisasi untuk ambil waktu
        dateFormat = new SimpleDateFormat("dd/M/yyyy HH:mm:ss" , Locale.getDefault());

        //inisialisasi SharedPreferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //Inisialisasi view model
        mBatikViewModel = new ViewModelProvider(this).get(BatikViewModel.class);

        //Inisialisasi API
        apiData = new ApiData(mBatikViewModel);

        //Tombol Refresh
        refreshButton.setOnClickListener(view -> {
            hideRefresh();
            checkData();
        });

        //Periksa Data dari API
        checkData();

        //Ambil data dari database untuk RecyclerView
        mBatikViewModel.getAllBatik().observe(this, new Observer<List<Batik>>() {
            @Override
            public void onChanged(List<Batik> batiks) {
                batikAdapter.setBatikList(batiks);
            }
        });

        //Ambil data dari database untuk Image Slider
        mBatikViewModel.getAllBatikPopular().observe(this, new Observer<List<BatikSlide>>() {
            @Override
            public void onChanged(List<BatikSlide> batikSlides) {
                SlideAdapter.setBatikList(batikSlides);
            }
        });


        //set Pencarian
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class)
                        .putExtra("query_search", query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

    }


    //Periksa data di API
    public void checkData() {
        if (checkInternet.isConnected()) {
            Toast.makeText(MainActivity.this, "Sedang Memuat ...", Toast.LENGTH_SHORT).show();

            //Beri waktu aplikasi membaca cache
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {

                    //jika tidak ada cache, maka ambil data dari internet
                    if (batikAdapter.getItemCount() < 1) {
                        apiData.getData();
                    }
                }
            }, 2000);

        } else {
            Toast.makeText(MainActivity.this, "No Internet Connection ...", Toast.LENGTH_SHORT).show();

            //Beri waktu aplikasi membaca cache
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (batikAdapter.getItemCount() < 1) {
                        displayRefresh();
                    }
                }
            }, 2000);
        }
    }


    public void hideRefresh() {
        labelNoInternet.setVisibility(View.GONE);
        refreshButton.setVisibility(View.GONE);
    }

    public void displayRefresh() {
        labelNoInternet.setVisibility(View.VISIBLE);
        refreshButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ambilWaktuKeluar = dateFormat.format(new Date());

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(WAKTU_KELUAR, ambilWaktuKeluar);
        preferencesEditor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ambilWaktuMasuk = dateFormat.format(new Date());
        String waktuSebelumKeluar = mPreferences.getString(WAKTU_KELUAR,dateFormat.format(new Date()));

        if (!ambilWaktuMasuk.equals(waktuSebelumKeluar)) {
            try {
                Date waktuKeluar = dateFormat.parse(waktuSebelumKeluar);
                Date waktuMasuk = dateFormat.parse(ambilWaktuMasuk);

                HitungWaktuOut hitungWaktuOut = new HitungWaktuOut(waktuKeluar, waktuMasuk);
                String hasilWaktu = hitungWaktuOut.durasiKeluarAplikasi();

                if (!hasilWaktu.equals("sebentar")) {
                    textWaktu.setText("Selamat Datang Kembali, sudah " + hasilWaktu + " Anda Tidak Berkunjung");
                    new TimeAnimation(textWaktu).displayAnimation();
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
