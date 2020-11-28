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
import com.example.aplikasidaftarbatik.adapters.BatikAdapter;
import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.roomdatabase.BatikViewModel;

import java.util.List;

public class SearchActivity extends AppCompatActivity {


    TextView infoSearch;
    RecyclerView searchRecycler;
    SearchView searchButton;


    BatikAdapter searchAdapter;

    BatikViewModel mBatikViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        infoSearch = findViewById(R.id.label_info_search);
        searchRecycler = findViewById(R.id.searchRecyclerView);
        searchButton = findViewById(R.id.kolomcari);



        searchAdapter = new BatikAdapter(this);
        searchRecycler.setAdapter(searchAdapter);
        searchRecycler.setLayoutManager(new LinearLayoutManager(this));

        mBatikViewModel = new ViewModelProvider(this).get(BatikViewModel.class);


        String mainInput = getIntent().getStringExtra("query_search");


        searchButton.setSubmitButtonEnabled(true);
        searchButton.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearchBatik(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doSearchBatik(newText);
                return false;
            }

            private void doSearchBatik(String inputData) {

                mBatikViewModel.searchBatik(inputData).observe(SearchActivity.this, new Observer<List<Batik>>() {
                    @Override
                    public void onChanged(List<Batik> batiks) {
                        if (batiks.size() > 0) {
                            searchAdapter.setBatikList(batiks);
                            infoSearch.setText("Menampilkan hasil pencarian " + inputData);
                        } else {
                            searchAdapter.setBatikList(batiks);
                            infoSearch.setText("Tidak menemukan hasil pencarian " + inputData);
                        }
                    }
                });
            }
        });

        searchButton.setQuery(mainInput, true);


    }
}
