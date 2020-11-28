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
import com.example.aplikasidaftarbatik.models.Batik;

import java.util.ArrayList;
import java.util.List;

public class BatikAdapter extends RecyclerView.Adapter<BatikAdapter.BatikViewHolder> {

    List<Batik> data = new ArrayList<>();
    Context context;

    public BatikAdapter(Context ct) {
        this.context = ct;
    }

    public void setBatikList(List<Batik> batiks) {
        this.data = batiks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BatikViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new BatikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatikViewHolder holder, int position) {

        Batik currentBatik = data.get(position);

        holder.bindTo(currentBatik);

    }

    @Override
    public int getItemCount() {
        if (this.data != null) {
            return data.size();
        }
        return 0;
    }

    public class BatikViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myNamaBatik, myDaerahBatik, myInfoBatik;
        ImageView myImage;

        public BatikViewHolder(@NonNull View itemView) {
            super(itemView);

            myNamaBatik = itemView.findViewById(R.id.namaBatik);
            myDaerahBatik= itemView.findViewById(R.id.daerahBatik);
            myInfoBatik = itemView.findViewById(R.id.infoBatik);
            myImage = itemView.findViewById(R.id.gambarBatik);

            itemView.setOnClickListener(this);

        }

        public void bindTo(Batik currentBatik) {
            myNamaBatik.setText(currentBatik.getNamaBatik());
            myDaerahBatik.setText(currentBatik.getDaerahBatik());
            myInfoBatik.setText(currentBatik.getMaknaBatik());

            Glide.with(context)
                 .load(currentBatik.getLinkBatik())
                 .skipMemoryCache(true)
                 .placeholder(R.drawable.img_error)
                 .centerCrop()
                 .error(R.drawable.img_error)
                 .into(myImage);
        }

        @Override
        public void onClick(View view) {
            Batik currentBatik = data.get(getAdapterPosition());
            Intent detailIntent = new Intent(context, DetailActivity.class);
            detailIntent.putExtra("id", currentBatik.getId().toString());
            detailIntent.putExtra("namaBatik", currentBatik.getNamaBatik());
            detailIntent.putExtra("daerahBatik", currentBatik.getDaerahBatik());
            detailIntent.putExtra("maknaBatik", currentBatik.getMaknaBatik());
            detailIntent.putExtra("hargaRendah", currentBatik.getHargaRendah().toString());
            detailIntent.putExtra("hargaTinggi", currentBatik.getHargaTinggi().toString());
            detailIntent.putExtra("hitungView", currentBatik.getHitungView().toString());
            detailIntent.putExtra("linkBatik", currentBatik.getLinkBatik());

            context.startActivity(detailIntent);
        }
    }
}
