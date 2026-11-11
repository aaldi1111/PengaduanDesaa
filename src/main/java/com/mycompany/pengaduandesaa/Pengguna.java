package com.mycompany.pengaduandesaa;

public class Pengguna {
    private String nik;
    private String nama;
    private String password;

    public Pengguna(String nik, String nama, String password) {
        this.nik = nik;
        this.nama = nama;
        this.password = password;
    }

    public String getNik() { return nik; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public void setPassword(String password) { this.password = password; }

    public boolean cekPassword(String input) {
        return password.equals(input);
    }

    // Di-override oleh subclass
    public String getPeran() {
        return "Pengguna";
    }

    public void tampilkanInfo() {
        System.out.println("Nama : " + nama);
        System.out.println("NIK  : " + nik);
        System.out.println("Peran: " + getPeran());
    }
}