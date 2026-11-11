package com.mycompany.pengaduandesaa;

public class Pengaduan {
    public static final String MENUNGGU = "Menunggu Verifikasi";
    public static final String DIVERIFIKASI = "Diverifikasi";
    public static final String DITOLAK = "Ditolak";
    public static final String DITANGANI = "Sedang Ditangani";
    public static final String SELESAI = "Selesai";

    private int id;
    private String nikPelapor;
    private String dusun;
    private String kategori;
    private String deskripsi;
    private String status;
    private String catatan;

    public Pengaduan(int id, String nikPelapor, String dusun, String kategori, String deskripsi) {
        this.id = id;
        this.nikPelapor = nikPelapor;
        this.dusun = dusun;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.status = MENUNGGU;
        this.catatan = "-";
    }

    public int getId() { return id; }
    public String getNikPelapor() { return nikPelapor; }
    public String getDusun() { return dusun; }
    public String getKategori() { return kategori; }
    public String getDeskripsi() { return deskripsi; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCatatan() { return catatan; }
    public void setCatatan(String catatan) { this.catatan = catatan; }

    public void tampilkan() {
        System.out.println("[#" + id + "] " + kategori + " | Dusun " + dusun);
        System.out.println("     Isi    : " + deskripsi);
        System.out.println("     Status : " + status);
        System.out.println("     Catatan: " + catatan);
    }
}