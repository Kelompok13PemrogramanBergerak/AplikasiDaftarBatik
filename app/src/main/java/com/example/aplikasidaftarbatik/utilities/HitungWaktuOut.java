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

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        String hasilWaktu = "";

        if(diffSeconds >= 0 && diffMinutes < 10 && diffHours == 0 && diffDays == 0) {
            hasilWaktu = "sebentar";
        } else if (diffSeconds >= 0 && diffMinutes >= 10 && diffHours == 0 && diffDays == 0) {
            hasilWaktu = diffMinutes + " menit " + diffSeconds + " detik";
        } else if (diffSeconds >= 0 && diffMinutes > 0 && diffHours > 0 && diffDays == 0) {
            hasilWaktu = diffHours + " jam " + diffMinutes + " menit";
        } else if ( diffSeconds >= 0 && diffMinutes > 0 && diffHours > 0 && diffDays > 0) {
            hasilWaktu = diffDays + " hari " + diffHours + " jam";
        }

        return hasilWaktu;
    }
}
