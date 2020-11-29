package com.example.aplikasidaftarbatik.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "hotel_table")
public class Hotel {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("nama")
    @Expose
    @ColumnInfo(name = "nama")
    private String nama;

    @SerializedName("alamat")
    @Expose
    @ColumnInfo(name = "alamat")
    private String alamat;

    @SerializedName("nomor_telp")
    @Expose
    @ColumnInfo(name = "nomor_telp")
    private String nomor_telp;

    @SerializedName("kordinat")
    @Expose
    @ColumnInfo(name = "kordinat")
    private String kordinat;

    @SerializedName("gambar_url")
    @Expose
    @ColumnInfo(name = "gambar_url")
    private String gambar_url;

    public Hotel(Integer id, String nama, String alamat, String nomor_telp, String kordinat, String gambar_url) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomor_telp = nomor_telp;
        this.kordinat = kordinat;
        this.gambar_url = gambar_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomor_telp() {
        return nomor_telp;
    }

    public void setNomor_telp(String nomor_telp) {
        this.nomor_telp = nomor_telp;
    }

    public String getKordinat() {
        return kordinat;
    }

    public void setKordinat(String kordinat) {
        this.kordinat = kordinat;
    }

    public String getGambar_url() {
        return gambar_url;
    }

    public void setGambar_url(String gambar_url) {
        this.gambar_url = gambar_url;
    }
}
