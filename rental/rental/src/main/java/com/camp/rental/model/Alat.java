package com.camp.rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alat_camping")
public class Alat {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String kategori;
    private double harga;
    private String imageUrl;

    public Alat() {}
    public Alat(String nama, String kategori, double harga, String imageUrl) {
        this.nama = nama; this.kategori = kategori; this.harga = harga; this.imageUrl = imageUrl;
    }
    // Generate Getter & Setter
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNama() { return nama; } public void setNama(String nama) { this.nama = nama; }
    public String getKategori() { return kategori; } public void setKategori(String kategori) { this.kategori = kategori; }
    public double getHarga() { return harga; } public void setHarga(double harga) { this.harga = harga; }
    public String getImageUrl() { return imageUrl; } public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}