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


    ImageView detailGambarBatik;
    TextView detailNamaBatik;
    TextView detailDaerahBatik;
    TextView detailHargaRendah;
    TextView detailHargaTinggi;
    TextView detailMaknaBatik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailGambarBatik = findViewById(R.id.detail_gambar_batik);
        detailNamaBatik = findViewById(R.id.detail_nama_batik);
        detailDaerahBatik = findViewById(R.id.detail_daerah_batik);
        detailHargaRendah = findViewById(R.id.detail_harga_rendah);
        detailHargaTinggi = findViewById(R.id.detail_harga_tinggi);
        detailMaknaBatik = findViewById(R.id.detail_makna_batik);

        //Justify text
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detailMaknaBatik.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }


        Glide.with(DetailActivity.this)
                .load(getIntent().getStringExtra("linkBatik"))
                .placeholder(R.drawable.img_error)
                .into(detailGambarBatik);

        detailNamaBatik.setText(getIntent().getStringExtra("namaBatik"));
        detailDaerahBatik.setText(getIntent().getStringExtra("daerahBatik"));
        detailHargaRendah.setText("Rp." + getIntent().getStringExtra("hargaRendah"));
        detailHargaTinggi.setText("Rp." + getIntent().getStringExtra("hargaTinggi"));
        detailMaknaBatik.setText(getIntent().getStringExtra("maknaBatik"));



    }

}
