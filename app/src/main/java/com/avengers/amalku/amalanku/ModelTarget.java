package com.avengers.amalku.amalanku;

public class ModelTarget {
    String nama;
    Integer jumlah;
    String satuan;
    Integer imageId;

    public ModelTarget(String nama, int jumlah, String satuan, Integer imageId) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.imageId = imageId;
    }

    public String getNama() {
        return nama;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public Integer getImageId() {
        return imageId;
    }
}