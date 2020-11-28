package com.example.aplikasidaftarbatik.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.aplikasidaftarbatik.R;
import com.example.aplikasidaftarbatik.models.BatikSlide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class BatikSliderAdapter extends SliderViewAdapter<BatikSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<BatikSlide> batikSlides = new ArrayList<>();

    public BatikSliderAdapter(Context context) {
        this.context = context;
    }

    public void setBatikList(List<BatikSlide> batikslides) {
        this.batikSlides = batikslides;
        notifyDataSetChanged();
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_view, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        BatikSlide sliderItem = batikSlides.get(position);

        viewHolder.batikDescription.setText(sliderItem.getNamaBatik());

        Glide.with(viewHolder.itemView)
                .load(sliderItem.getLinkBatik())
                .skipMemoryCache(true)
                .placeholder(R.drawable.bg_overlay)
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {

        if (this.batikSlides != null) {
            return batikSlides.size();
        }
        return 0;

    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView batikDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);

            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            batikDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
