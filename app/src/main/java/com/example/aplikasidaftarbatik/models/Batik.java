package com.example.aplikasidaftarbatik.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Batik {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_batik")
    @Expose
    private String namaBatik;
    @SerializedName("daerah_batik")
    @Expose
    private String daerahBatik;
    @SerializedName("makna_batik")
    @Expose
    private String maknaBatik;
    @SerializedName("harga_rendah")
    @Expose
    private Integer hargaRendah;
    @SerializedName("harga_tinggi")
    @Expose
    private Integer hargaTinggi;
    @SerializedName("hitung_view")
    @Expose
    private Integer hitungView;
    @SerializedName("link_batik")
    @Expose
    private String linkBatik;


    public Batik(Integer id,
                 String namaBatik,
                 String daerahBatik,
                 String maknaBatik,
                 Integer hargaRendah,
                 Integer hargaTinggi,
                 Integer hitungView,
                 String linkBatik) {
        this.id = id;
        this.namaBatik = namaBatik;
        this.daerahBatik = daerahBatik;
        this.maknaBatik = maknaBatik;
        this.hargaRendah = hargaRendah;
        this.hargaTinggi = hargaTinggi;
        this.hitungView = hitungView;
        this.linkBatik = linkBatik;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBatik() {
        return namaBatik;
    }

    public void setNamaBatik(String namaBatik) {
        this.namaBatik = namaBatik;
    }

    public String getDaerahBatik() {
        return daerahBatik;
    }

    public void setDaerahBatik(String daerahBatik) {
        this.daerahBatik = daerahBatik;
    }

    public String getMaknaBatik() {
        return maknaBatik;
    }

    public void setMaknaBatik(String maknaBatik) {
        this.maknaBatik = maknaBatik;
    }

    public Integer getHargaRendah() {
        return hargaRendah;
    }

    public void setHargaRendah(Integer hargaRendah) {
        this.hargaRendah = hargaRendah;
    }

    public Integer getHargaTinggi() {
        return hargaTinggi;
    }

    public void setHargaTinggi(Integer hargaTinggi) {
        this.hargaTinggi = hargaTinggi;
    }

    public Integer getHitungView() {
        return hitungView;
    }

    public void setHitungView(Integer hitungView) {
        this.hitungView = hitungView;
    }

    public String getLinkBatik() {
        return linkBatik;
    }

    public void setLinkBatik(String linkBatik) {
        this.linkBatik = linkBatik;
    }

    @Override
    public String toString() {
        return "Batik{" +
                "id=" + id +
                ", namaBatik='" + namaBatik + '\'' +
                ", daerahBatik='" + daerahBatik + '\'' +
                ", maknaBatik='" + maknaBatik + '\'' +
                ", hargaRendah=" + hargaRendah +
                ", hargaTinggi=" + hargaTinggi +
                ", hitungView=" + hitungView +
                ", linkBatik='" + linkBatik + '\'' +
                '}';
    }
}
