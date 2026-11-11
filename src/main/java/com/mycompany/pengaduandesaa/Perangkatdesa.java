package com.mycompany.pengaduandesaa;

public class Perangkatdesa extends Pengguna {
    private String jabatan;

    public Perangkatdesa(String nik, String nama, String password, String jabatan) {
        super(nik, nama, password);
        this.jabatan = jabatan;
    }

    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }

    @Override
    public String getPeran() {
        return "Perangkat Desa (" + jabatan + ")";
    }
}