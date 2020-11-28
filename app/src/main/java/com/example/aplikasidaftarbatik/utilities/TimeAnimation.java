package com.example.aplikasidaftarbatik.utilities;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

public class TimeAnimation {

    TextView textWaktu;

    public TimeAnimation(TextView textWaktu) {
        this.textWaktu = textWaktu;
    }

    public void displayAnimation() {
        //persiapan muncul
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animation = ObjectAnimator.ofFloat(textWaktu, "translationY", -100f);
                animation.setDuration(2000);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.start();

                //durasi munculnya text
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(textWaktu, "translationY", 200f);
                        animation2.setDuration(2000);
                        animation2.setInterpolator(new AccelerateDecelerateInterpolator());
                        animation2.start();
                    }
                }, 10000);
            }
        }, 2000);
    }
}
