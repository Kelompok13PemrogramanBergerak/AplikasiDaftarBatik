package com.example.aplikasidaftarbatik.API;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasidaftarbatik.models.Batik;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiData {




    BatikViewModel mBatikViewModel;

    public ApiData(BatikViewModel mBatikViewModel) {
        this.mBatikViewModel = mBatikViewModel;
    }

    public void getData() {
        getAllBatik();
        getAllPopularBatik();
    }

    public void getAllBatik() {


        List<Batik> dataBatik = new ArrayList<>();
        AndroidNetworking.get("https://batikita.herokuapp.com/index.php/batik/all")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        JSONArray jsonArray = null;
                        try {
                            jsonArray = response.getJSONArray("hasil");
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

                            mBatikViewModel.insert(dataBatik);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }



    public void getAllPopularBatik() {
        List<BatikSlide> dataBatikPopular = new ArrayList<>();
        AndroidNetworking.get("https://batikita.herokuapp.com/index.php/batik/popular")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if (response != null) {
                                JSONArray jsonArray = response.getJSONArray("hasil");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);
                                    BatikSlide item = new BatikSlide(
                                            data.getInt("id"),
                                            data.getString("nama_batik"),
                                            data.getString("link_batik"));
                                    dataBatikPopular.add(item);
                                }

                                mBatikViewModel.insertPopular(dataBatikPopular);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }
}
