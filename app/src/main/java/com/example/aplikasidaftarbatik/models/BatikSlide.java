package com.example.aplikasidaftarbatik.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "batik_popular_table")
public class BatikSlide {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @SerializedName("nama_batik")
    @Expose
    @ColumnInfo(name = "nama_batik")
    private String namaBatik;

    @SerializedName("link_batik")
    @Expose
    @ColumnInfo(name = "link_batik")
    private String linkBatik;

    public BatikSlide(Integer id, String namaBatik, String linkBatik) {
        this.id = id;
        this.namaBatik = namaBatik;
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

    public String getLinkBatik() {
        return linkBatik;
    }

    public void setLinkBatik(String linkBatik) {
        this.linkBatik = linkBatik;
    }
}
