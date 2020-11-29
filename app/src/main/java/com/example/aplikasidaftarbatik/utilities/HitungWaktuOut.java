package com.example.aplikasidaftarbatik.utilities;

import java.time.Duration;
import java.util.Date;

public class HitungWaktuOut {

    Date waktuKeluar;
    Date waktuMasuk;

    public HitungWaktuOut(Date waktuKeluar, Date waktuMasuk) {
        this.waktuKeluar = waktuKeluar;
        this.waktuMasuk = waktuMasuk;
    }

    public String durasiKeluarAplikasi() {
        Duration diff = Duration.between(waktuKeluar.toInstant(), waktuMasuk.toInstant());

        long days = diff.toDays();
        diff = diff.minusDays(days);
        long hours = diff.toHours();
        diff = diff.minusHours(hours);
        long minutes = diff.toMinutes();
        diff = diff.minusMinutes(minutes);
        long seconds = diff.getSeconds();

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
