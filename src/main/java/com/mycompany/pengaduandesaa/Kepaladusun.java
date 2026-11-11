package com.mycompany.pengaduandesaa;

public class Kepaladusun extends Pengguna {
    private String dusun;

    public Kepaladusun(String nik, String nama, String password, String dusun) {
        super(nik, nama, password);
        this.dusun = dusun;
    }

    public String getDusun() { return dusun; }
    public void setDusun(String dusun) { this.dusun = dusun; }

    public String getPeran() {
        return "Kepala Dusun";
    }

    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Dusun: " + dusun);
    }
}