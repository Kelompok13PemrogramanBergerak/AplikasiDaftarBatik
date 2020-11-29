package com.example.aplikasidaftarbatik.activities;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarbatik.R;

import static android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {


    ImageView detailGambarHotel;
    TextView detailNamaHotel;
    TextView detailNomorTelepon;
    TextView detailAlamatHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailGambarHotel = findViewById(R.id.detail_gambar_hotel);
        detailNamaHotel = findViewById(R.id.detail_nama_hotel);
        detailNomorTelepon = findViewById(R.id.detail_nomor_telepon);
        detailAlamatHotel = findViewById(R.id.detail_alamat_hotel);


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
}
