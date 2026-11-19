package com.mycompany.pengaduandesaa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private int nomorBerikut = 1;
    private final ObservableList<String> daftarLaporan = FXCollections.observableArrayList();

    public void start(Stage stage) {
        Label judul = new Label("Form Pengaduan & Aspirasi Warga");
        judul.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField tfNik = new TextField();
        tfNik.setPromptText("NIK (angka)");
        TextField tfNama = new TextField();
        tfNama.setPromptText("Nama lengkap");
        ComboBox<String> cbDusun = new ComboBox<>(
                FXCollections.observableArrayList("Kedaton", "Pahlawan"));
        cbDusun.setPromptText("Pilih dusun");
        ComboBox<String> cbKategori = new ComboBox<>(FXCollections.observableArrayList(
                "Infrastruktur", "Kebersihan", "Keamanan", "Aspirasi/Lainnya"));
        cbKategori.setPromptText("Pilih kategori");
        TextArea taIsi = new TextArea();
        taIsi.setPromptText("Tulis pengaduan atau aspirasi...");
        taIsi.setPrefRowCount(3);
        taIsi.setWrapText(true);

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        ColumnConstraints kolomLabel = new ColumnConstraints();
        kolomLabel.setMinWidth(75);
        ColumnConstraints kolomIsi = new ColumnConstraints();
        kolomIsi.setHgrow(Priority.ALWAYS);
        form.getColumnConstraints().addAll(kolomLabel, kolomIsi);
        form.add(new Label("NIK"), 0, 0);
        form.add(tfNik, 1, 0);
        form.add(new Label("Nama"), 0, 1);
        form.add(tfNama, 1, 1);
        form.add(new Label("Dusun"), 0, 2);
        form.add(cbDusun, 1, 2);
        form.add(new Label("Kategori"), 0, 3);
        form.add(cbKategori, 1, 3);
        form.add(new Label("Deskripsi"), 0, 4);
        form.add(taIsi, 1, 4);

        Button btnKirim = new Button("Kirim Laporan");
        Button btnReset = new Button("Reset");
        HBox tombol = new HBox(10, btnKirim, btnReset);
        tombol.setAlignment(Pos.CENTER_RIGHT);

        Label lblDaftar = new Label("Laporan yang sudah dikirim:");
        ListView<String> listView = new ListView<>(daftarLaporan);
        listView.setPrefHeight(150);

        
        btnKirim.setOnAction(e -> {
            String nik = tfNik.getText().trim();
            String nama = tfNama.getText().trim();
            String dusun = cbDusun.getValue();
            String kategori = cbKategori.getValue();
            String isi = taIsi.getText().trim();


            if (nik.isEmpty() || nama.isEmpty() || dusun == null || kategori == null || isi.isEmpty()) {
                tampilAlert(Alert.AlertType.WARNING, "Data belum lengkap", "Semua kolom wajib diisi.");
                return;
            }
            try {
                Long.parseLong(nik);
            } catch (NumberFormatException ex) {
                tampilAlert(Alert.AlertType.ERROR, "Format salah", "NIK harus berupa angka.");
                return;
            }

            
            Warga warga = new Warga(nik, nama, "-", dusun);
            Pengaduan p = new Pengaduan(nomorBerikut++, warga.getNik(), warga.getDusun(), kategori, isi);

            daftarLaporan.add("#" + p.getId() + " | " + warga.getNama() + " | " + p.getKategori()
                    + " | " + p.getStatus());
            tampilAlert(Alert.AlertType.INFORMATION, "Berhasil",
                    "Laporan #" + p.getId() + " terkirim.\nStatus: " + p.getStatus());
            kosongkanForm(tfNik, tfNama, cbDusun, cbKategori, taIsi);
        });

        btnReset.setOnAction(e -> kosongkanForm(tfNik, tfNama, cbDusun, cbKategori, taIsi));

        VBox root = new VBox(12, judul, form, tombol, lblDaftar, listView);
        root.setPadding(new Insets(20));
        stage.setTitle("Sistem Pengaduan Masyarakat Desa");
        stage.setScene(new Scene(root, 520, 560));
        stage.show();
    }

    private void kosongkanForm(TextField nik, TextField nama, ComboBox<String> dusun,
                               ComboBox<String> kategori, TextArea isi) {
        nik.clear();
        nama.clear();
        dusun.setValue(null);
        kategori.setValue(null);
        isi.clear();
    }

    private void tampilAlert(Alert.AlertType tipe, String judul, String pesan) {
        Alert a = new Alert(tipe);
        a.setTitle(judul);
        a.setHeaderText(null);
        a.setContentText(pesan);
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}   