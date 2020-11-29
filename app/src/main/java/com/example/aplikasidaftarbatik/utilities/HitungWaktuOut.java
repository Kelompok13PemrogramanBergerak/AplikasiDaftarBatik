package com.example.aplikasidaftarbatik.utilities;

import java.util.Date;

public class HitungWaktuOut {

    Date waktuKeluar;
    Date waktuMasuk;

    public HitungWaktuOut(Date waktuKeluar, Date waktuMasuk) {
        this.waktuKeluar = waktuKeluar;
        this.waktuMasuk = waktuMasuk;
    }

    public String durasiKeluarAplikasi() {
        long diff = waktuMasuk.getTime() - waktuKeluar.getTime();

        long seconds = diff / 1000 % 60;
        long minutes = diff / (60 * 1000) % 60;
        long hours = diff / (60 * 60 * 1000) % 24;
        long days = diff / (24 * 60 * 60 * 1000);

        String hasilWaktu = "";

        if(seconds >= 0 && minutes < 10 && hours == 0 && days == 0) {
            hasilWaktu = "sebentar";
        } else if (seconds >= 0 && minutes >= 10 && hours == 0 && days == 0) {
            hasilWaktu = minutes + " menit " + seconds + " detik";
        } else if (seconds >= 0 && minutes >= 0 && hours >= 0 && days == 0) {
            hasilWaktu = hours + " jam " + minutes + " menit";
        } else if ( seconds >= 0 && minutes >= 0 && hours >= 0 && days > 0) {
            hasilWaktu = days + " hari " + hours + " jam";
        } else {
            hasilWaktu = "lama";
        }

        return hasilWaktu;
    }
}
