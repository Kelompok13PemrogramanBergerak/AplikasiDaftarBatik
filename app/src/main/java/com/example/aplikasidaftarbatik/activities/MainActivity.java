package com.example.aplikasidaftarbatik.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasidaftarbatik.API.ApiData;
import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.adapters.BatikAdapter;
import com.example.aplikasidaftarbatik.R;
import com.example.aplikasidaftarbatik.utilities.CheckInternet;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //DataBatik
    List<Batik> dataBatik;
    List<BatikSlide> dataBatikPopular;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idRecyclerView = findViewById(R.id.idRecyclerView);
        sliderView = findViewById(R.id.imageSlider);
        labelNoInternet = findViewById(R.id.label_no_internet);
        refreshButton = findViewById(R.id.refresh_button);
        searchView = findViewById(R.id.kolomcari);
        //        dataBatik = new ArrayList<>();
//        dataBatikPopular = new ArrayList<>();

        //RecycleView Batik
        batikAdapter = new BatikAdapter(this, dataBatik);
        idRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        idRecyclerView.setAdapter(batikAdapter);

        //Slider Batik
        SlideAdapter = new BatikSliderAdapter(this, dataBatikPopular);
        sliderView.setSliderAdapter(SlideAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();

        AndroidNetworking.initialize(getApplicationContext());
        checkInternet = new CheckInternet(getApplicationContext());


        mBatikViewModel = new ViewModelProvider(this).get(BatikViewModel.class);
        apiData = new ApiData(mBatikViewModel);


        refreshButton.setOnClickListener(view -> {
            hideRefresh();
            checkData();
        });

        //Periksa Data dari API
        checkData();


        mBatikViewModel.getAllBatik().observe(this, new Observer<List<Batik>>() {
            @Override
            public void onChanged(List<Batik> batiks) {
                batikAdapter.setBatikList(batiks);
            }
        });


        mBatikViewModel.getAllBatikPopular().observe(this, new Observer<List<BatikSlide>>() {
            @Override
            public void onChanged(List<BatikSlide> batikSlides) {
                SlideAdapter.setBatikList(batikSlides);
            }
        });


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



    public void checkData() {
        if (checkInternet.isConnected()) {
            Toast.makeText(MainActivity.this, "Sedang Memuat ...", Toast.LENGTH_SHORT).show();
            apiData.getData();
        } else {
            Toast.makeText(MainActivity.this, "No Internet Connection ...", Toast.LENGTH_SHORT).show();
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
}