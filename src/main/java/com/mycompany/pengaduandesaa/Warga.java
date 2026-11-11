package com.mycompany.pengaduandesaa;

public class Warga extends Pengguna {
    private String dusun;

    public Warga(String nik, String nama, String password, String dusun) {
        super(nik, nama, password);
        this.dusun = dusun;
    }

    public String getDusun() { return dusun; }
    public void setDusun(String dusun) { this.dusun = dusun; }

    @Override
    public String getPeran() {
        return "Warga";
    }

    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Dusun: " + dusun);
    }
}