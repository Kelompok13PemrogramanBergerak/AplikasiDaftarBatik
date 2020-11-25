package com.example.aplikasidaftarbatik.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasidaftarbatik.models.Batik;
import com.example.aplikasidaftarbatik.adapters.BatikAdapter;
import com.example.aplikasidaftarbatik.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Batik> dataBatik;
    BatikAdapter batikAdapter;

    //wendi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.idRecycleView);
        dataBatik = new ArrayList<>();
        AndroidNetworking.initialize(getApplicationContext());

        getAllBatik();

        batikAdapter = new BatikAdapter(dataBatik, this);
        mRecyclerView.setAdapter(batikAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getAllBatik() {
        AndroidNetworking.get("https://batikita.herokuapp.com/index.php/batik/all")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("hasil");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    Batik item = new Batik(
                                            data.getInt("id"),
                                            data.getString("nama_batik"),
                                            data.getString("daerah_batik"),
                                            data.getString("makna_batik"),
                                            data.getInt("harga_rendah"),
                                            data.getInt("harga_tinggi"),
                                            data.getInt("hitung_view"),
                                            data.getString("link_batik"));
                                    dataBatik.add(item);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Gagal di Load!", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                            Log.e("JSON Parser", "Error parsing data " + e.toString());
                        }

                        batikAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getApplicationContext(), "Tidak dapat terhubung ke internet!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}