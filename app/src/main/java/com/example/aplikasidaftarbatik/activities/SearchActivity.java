package com.example.aplikasidaftarbatik.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasidaftarbatik.R;
import com.example.aplikasidaftarbatik.adapters.HotelAdapter;
import com.example.aplikasidaftarbatik.models.Hotel;
import com.example.aplikasidaftarbatik.roomdatabase.HotelViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    TextView infoSearch;
    RecyclerView searchRecycler;
    SearchView searchButton;

    HotelAdapter searchAdapter;

    HotelViewModel mHotelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        infoSearch = findViewById(R.id.label_info_search);
        searchRecycler = findViewById(R.id.searchRecyclerView);
        searchButton = findViewById(R.id.kolomcari);

        searchAdapter = new HotelAdapter(this);
        searchRecycler.setAdapter(searchAdapter);
        searchRecycler.setLayoutManager(new LinearLayoutManager(this));

        mHotelViewModel = new ViewModelProvider(this).get(HotelViewModel.class);


        String mainInput = getIntent().getStringExtra("query_search");


        searchButton.setSubmitButtonEnabled(true);
        searchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearchHotel(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doSearchHotel(newText);
                return false;
            }

            private void doSearchHotel(String inputData) {

                mHotelViewModel.searchHotel(inputData).observe(SearchActivity.this, new Observer<List<Hotel>>() {
                    @Override
                    public void onChanged(List<Hotel> hotels) {
                        if (hotels.size() > 0) {
                            searchAdapter.setBatikList(hotels);
                            infoSearch.setText("Menampilkan hasil pencarian " + inputData);
                        } else {
                            searchAdapter.setBatikList(hotels);
                            infoSearch.setText("Tidak menemukan hasil pencarian " + inputData);
                        }
                    }
                });
            }
        });

        searchButton.setQuery(mainInput, true);


    }
}
