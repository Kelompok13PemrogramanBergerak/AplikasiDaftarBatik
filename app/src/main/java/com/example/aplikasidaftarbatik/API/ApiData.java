package com.example.aplikasidaftarbatik.API;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.aplikasidaftarbatik.models.Hotel;
import com.example.aplikasidaftarbatik.roomdatabase.HotelViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiData {

    HotelViewModel mHotelViewModel;

    public ApiData(HotelViewModel mHotelViewModel) {
        this.mHotelViewModel = mHotelViewModel;
    }

    public void getData() {
        getAllHotel();
    }

    public void getAllHotel() {
        List<Hotel> dataHotel = new ArrayList<>();
        AndroidNetworking.get("https://dev.farizdotid.com/api/purwakarta/hotel")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hotel");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                Hotel item = new Hotel(
                                        data.getInt("id"),
                                        data.getString("nama"),
                                        data.getString("alamat"),
                                        data.getString("nomor_telp"),
                                        data.getString("kordinat"),
                                        data.getString("gambar_url"));

                                dataHotel.add(item);
                            }

                            mHotelViewModel.insertHotel(dataHotel);
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
