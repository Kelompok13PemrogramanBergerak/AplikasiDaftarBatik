package com.example.aplikasidaftarbatik.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarbatik.R;
import com.example.aplikasidaftarbatik.activities.DetailActivity;
import com.example.aplikasidaftarbatik.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    List<Hotel> data = new ArrayList<>();
    Context context;

    public HotelAdapter(Context ct) {
        this.context = ct;
    }

    public void setBatikList(List<Hotel> hotels) {
        this.data = hotels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {

        Hotel currentHotel = data.get(position);

        holder.bindTo(currentHotel);

    }

    @Override
    public int getItemCount() {
        if (this.data != null) {
            return data.size();
        }
        return 0;
    }

    public class HotelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myNamaHotel, myNoTelp, myAlamatHotel;
        ImageView myImage;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);

            myNamaHotel = itemView.findViewById(R.id.namaHotel);
            myNoTelp = itemView.findViewById(R.id.nomorTelpon);
            myAlamatHotel = itemView.findViewById(R.id.alamatHotel);
            myImage = itemView.findViewById(R.id.gambar);

            itemView.setOnClickListener(this);

        }

        public void bindTo(Hotel currentHotel) {
            myNamaHotel.setText(currentHotel.getNama());
            myNoTelp.setText(currentHotel.getNomor_telp());
            myAlamatHotel.setText(currentHotel.getAlamat());

            Glide.with(context)
                 .load(currentHotel.getGambar_url())
                 .skipMemoryCache(true)
                 .placeholder(R.drawable.img_error)
                 .centerCrop()
                 .error(R.drawable.img_error)
                 .into(myImage);
        }

        @Override
        public void onClick(View view) {
            Hotel currentHotel = data.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("id",currentHotel.getId().toString());
            detailIntent.putExtra("nama", currentHotel.getNama());
            detailIntent.putExtra("alamat", currentHotel.getAlamat());
            detailIntent.putExtra("nomor_telp", currentHotel.getNomor_telp());
            detailIntent.putExtra("kordinat", currentHotel.getKordinat());
            detailIntent.putExtra("gambar_url", currentHotel.getGambar_url());

            context.startActivity(detailIntent);
        }
    }
}
