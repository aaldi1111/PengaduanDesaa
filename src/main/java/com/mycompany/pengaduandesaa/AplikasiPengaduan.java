package com.mycompany.pengaduandesaa;

import java.util.ArrayList;
import java.util.Scanner;

public class AplikasiPengaduan {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Pengguna> daftarPengguna = new ArrayList<>();
    static ArrayList<Pengaduan> daftarPengaduan = new ArrayList<>();
    static int nomorBerikut = 1;

    public static void main(String[] args) {
        daftarPengguna.add(new Warga("1101", "Budi ", "111", "Pahlawan"));
        daftarPengguna.add(new Warga("1102", "Cecep ", "112", "Kedaton"));
        daftarPengguna.add(new Kepaladusun("1201", "Pak Asep", "122", "Pahlawan"));
        daftarPengguna.add(new Kepaladusun("1202", "Pak Yanto", "123", "Kedaton"));
        daftarPengguna.add(new Perangkatdesa("1991", "Pak Sukri", "199", "Sekretaris Desa"));

        System.out.println("=== SISTEM PENGADUAN & ASPIRASI MASYARAKAT DESA ===");
        boolean jalan = true;
        while (jalan) {
            System.out.println("\n1. Login");
            System.out.println("2. Daftar Warga Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    Pengguna user = login();
                    if (user != null) {
                        if (user instanceof Warga) menuWarga((Warga) user);
                        else if (user instanceof Kepaladusun) menuKepaladusun((Kepaladusun) user);
                        else if (user instanceof Perangkatdesa) menuPerangkat((Perangkatdesa) user);
                    }
                    break;
                case "2":
                    daftarWarga();
                    break;
                case "0":
                    jalan = false;
                    System.out.println("Terima kasih.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static Pengguna login() {
        System.out.print("NIK      : ");
        String nik = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();
        for (Pengguna p : daftarPengguna) {
            if (p.getNik().equals(nik) && p.cekPassword(pass)) {
                System.out.println("\nLogin berhasil. Selamat datang, " + p.getNama() + " (" + p.getPeran() + ")");
                return p;
            }
        }
        System.out.println("NIK atau password salah.");
        return null;
    }

    static void daftarWarga() {
        System.out.print("NIK      : ");
        String nik = sc.nextLine();
        for (Pengguna p : daftarPengguna) {
            if (p.getNik().equals(nik)) {
                System.out.println("NIK sudah terdaftar.");
                return;
            }
        }
        System.out.print("Nama     : ");
        String nama = sc.nextLine();
        System.out.print("Password : ");
        String pass = sc.nextLine();
        System.out.print("Dusun    : ");
        String dusun = sc.nextLine();
        daftarPengguna.add(new Warga(nik, nama, pass, dusun));
        System.out.println("Pendaftaran berhasil, silakan login.");
    }

    static void menuWarga(Warga w) {
        boolean aktif = true;
        while (aktif) {
            System.out.println("MENU WARGA");
            System.out.println("1. Buat Pengaduan/Aspirasi");
            System.out.println("2. Lihat Riwayat & Status Laporan");
            System.out.println("3. Profil Saya");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    buatPengaduan(w);
                    break;
                case "2":
                    boolean ada = false;
                    for (Pengaduan p : daftarPengaduan) {
                        if (p.getNikPelapor().equals(w.getNik())) {
                            p.tampilkan();
                            ada = true;
                        }
                    }
                    if (!ada) System.out.println("Belum ada laporan.");
                    break;
                case "3":
                    w.tampilkanInfo();
                    break;
                case "0":
                    aktif = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void buatPengaduan(Warga w) {
        System.out.println("Kategori: 1) Infrastruktur  2) Kebersihan  3) Keamanan  4) Aspirasi/Lainnya");
        System.out.print("Pilih kategori: ");
        String k = sc.nextLine();
        String kategori;
        switch (k) {
            case "1": kategori = "Infrastruktur"; break;
            case "2": kategori = "Kebersihan"; break;
            case "3": kategori = "Keamanan"; break;
            default:  kategori = "Aspirasi/Lainnya";
        }
        System.out.print("Deskripsi: ");
        String isi = sc.nextLine();
        Pengaduan p = new Pengaduan(nomorBerikut++, w.getNik(), w.getDusun(), kategori, isi);
        daftarPengaduan.add(p);
        System.out.println("Laporan terkirim dengan ID #" + p.getId() + ". Menunggu verifikasi Kepala Dusun.");
    }

    static void menuKepaladusun(Kepaladusun kd) {
        boolean aktif = true;
        while (aktif) {
            System.out.println("\n--- MENU KEPALA DUSUN (" + kd.getDusun() + ") ---");
            System.out.println("1. Lihat Laporan Menunggu Verifikasi");
            System.out.println("2. Verifikasi Laporan");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    boolean ada = false;
                    for (Pengaduan p : daftarPengaduan) {
                        if (p.getDusun().equals(kd.getDusun()) && p.getStatus().equals(Pengaduan.MENUNGGU)) {
                            p.tampilkan();
                            ada = true;
                        }
                    }
                    if (!ada) System.out.println("Tidak ada laporan yang menunggu.");
                    break;
                case "2":
                    verifikasi(kd);
                    break;
                case "0":
                    aktif = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void verifikasi(Kepaladusun kd) {
        System.out.print("ID laporan: ");
        int id = bacaAngka();
        Pengaduan p = cariPengaduan(id);
        if (p == null || !p.getDusun().equals(kd.getDusun()) || !p.getStatus().equals(Pengaduan.MENUNGGU)) {
            System.out.println("Laporan tidak ditemukan atau bukan wewenang Anda.");
            return;
        }
        p.tampilkan();
        System.out.print("Setujui? (y/n): ");
        String jawab = sc.nextLine();
        if (jawab.equalsIgnoreCase("y")) {
            p.setStatus(Pengaduan.DIVERIFIKASI);
            p.setCatatan("Diverifikasi oleh " + kd.getNama());
            System.out.println("Laporan diverifikasi dan diteruskan ke perangkat desa.");
        } else {
            System.out.print("Alasan penolakan: ");
            p.setStatus(Pengaduan.DITOLAK);
            p.setCatatan("Ditolak: " + sc.nextLine());
            System.out.println("Laporan ditolak.");
        }
    }

    static void menuPerangkat(Perangkatdesa pd) {
        boolean aktif = true;
        while (aktif) {
            System.out.println("MENU PERANGKAT DESA");
            System.out.println("1. Lihat Semua Laporan");
            System.out.println("2. Update Status Penanganan");
            System.out.println("3. Rekap per Status");
            System.out.println("0. Logout");
            System.out.print("Pilih: ");
            String pilih = sc.nextLine();

            switch (pilih) {
                case "1":
                    if (daftarPengaduan.isEmpty()) System.out.println("Belum ada laporan.");
                    for (Pengaduan p : daftarPengaduan) p.tampilkan();
                    break;
                case "2":
                    updateStatus();
                    break;
                case "3":
                    rekap();
                    break;
                case "0":
                    aktif = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    static void updateStatus() {
        System.out.print("ID laporan: ");
        int id = bacaAngka();
        Pengaduan p = cariPengaduan(id);
        if (p == null) {
            System.out.println("Laporan tidak ditemukan.");
            return;
        }
        String s = p.getStatus();
        if (!s.equals(Pengaduan.DIVERIFIKASI) && !s.equals(Pengaduan.DITANGANI)) {
            System.out.println("Laporan belum diverifikasi Kepala Dusun / sudah selesai.");
            return;
        }
        System.out.print("Status baru (1 = Sedang Ditangani, 2 = Selesai): ");
        String pilih = sc.nextLine();
        System.out.print("Catatan penanganan: ");
        String cat = sc.nextLine();
        p.setStatus(pilih.equals("2") ? Pengaduan.SELESAI : Pengaduan.DITANGANI);
        p.setCatatan(cat);
        System.out.println("Status diperbarui: " + p.getStatus());
    }

    static void rekap() {
        String[] semua = {Pengaduan.MENUNGGU, Pengaduan.DIVERIFIKASI, Pengaduan.DITOLAK,
                          Pengaduan.DITANGANI, Pengaduan.SELESAI};
        for (String st : semua) {
            int jumlah = 0;
            for (Pengaduan p : daftarPengaduan) {
                if (p.getStatus().equals(st)) jumlah++;
            }
            System.out.println(st + ": " + jumlah);
        }
    }

    static Pengaduan cariPengaduan(int id) {
        for (Pengaduan p : daftarPengaduan) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    static int bacaAngka() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}