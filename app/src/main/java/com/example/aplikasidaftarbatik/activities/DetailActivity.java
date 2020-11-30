package com.example.aplikasidaftarbatik.activities;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarbatik.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {


    ImageView detailGambarHotel;
    TextView detailNamaHotel;
    TextView detailNomorTelepon;
    TextView detailAlamatHotel;
    TextView labelNoLocation;
    GoogleMap mapView;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailGambarHotel = findViewById(R.id.detail_gambar_hotel);
        detailNamaHotel = findViewById(R.id.detail_nama_hotel);
        detailNomorTelepon = findViewById(R.id.detail_nomor_telepon);
        detailAlamatHotel = findViewById(R.id.detail_alamat_hotel);
        labelNoLocation = findViewById(R.id.label_no_location);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Justify text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detailAlamatHotel.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        Glide.with(DetailActivity.this)
                .load(getIntent().getStringExtra("gambar_url"))
                .placeholder(R.drawable.img_error)
                .into(detailGambarHotel);

        detailNamaHotel.setText(getIntent().getStringExtra("nama"));
        detailNomorTelepon.setText(getIntent().getStringExtra("nomor_telp"));
        detailAlamatHotel.setText(getIntent().getStringExtra("alamat"));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapView = googleMap;

        String koordinat = getIntent().getStringExtra("kordinat");

        if (!koordinat.equals("-")){
            String[] afterSplit = koordinat.split(", ");

            double lat = Double.parseDouble(afterSplit[0]);
            double lng = Double.parseDouble(afterSplit[1]);

            // Add a marker in Sydney and move the camera
            LatLng lokasi = new LatLng(lat, lng);
            mapView.addMarker(new MarkerOptions()
                    .position(lokasi)
                    .title(getIntent().getStringExtra("nama")));
            mapView.moveCamera(CameraUpdateFactory.newLatLng(lokasi));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi, 14), 5000, null);
                }
            }, 2000);
        } else {
            mapFragment.getView().setVisibility(View.GONE);
            labelNoLocation.setVisibility(View.VISIBLE);
        }
    }
}
