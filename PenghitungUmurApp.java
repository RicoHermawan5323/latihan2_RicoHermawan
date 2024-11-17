package com.example.java;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class PenghitungUmurApp extends JFrame {
    private JSpinner spinnerTanggalLahir;
    private JTextField fieldUmur, fieldUlangTahunBerikut;
    private JButton tombolHitung;

    public PenghitungUmurApp() {
        setTitle("Penghitung Umur");
        setSize(550, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelUtama = new JPanel(new GridLayout(4, 2, 15, 15));
        panelUtama.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        JLabel labelUmur = new JLabel("Umur (Tahun, Bulan, Hari):");
        JLabel labelUlangTahunBerikut = new JLabel("Ulang Tahun Berikutnya:");

        spinnerTanggalLahir = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerTanggalLahir, "yyyy-MM-dd");
        spinnerTanggalLahir.setEditor(editor);

        fieldUmur = new JTextField();
        fieldUmur.setEditable(false);
        fieldUmur.setFont(new Font("SansSerif", Font.PLAIN, 14));

        fieldUlangTahunBerikut = new JTextField();
        fieldUlangTahunBerikut.setEditable(false);
        fieldUlangTahunBerikut.setFont(new Font("SansSerif", Font.PLAIN, 14));

        tombolHitung = new JButton("Hitung");
        tombolHitung.setFont(new Font("SansSerif", Font.BOLD, 16));

        panelUtama.add(labelTanggalLahir);
        panelUtama.add(spinnerTanggalLahir);
        panelUtama.add(labelUmur);
        panelUtama.add(fieldUmur);
        panelUtama.add(labelUlangTahunBerikut);
        panelUtama.add(fieldUlangTahunBerikut);
        panelUtama.add(tombolHitung);

        add(panelUtama, BorderLayout.CENTER);

        tombolHitung.addActionListener(e -> hitungUmur());
    }

    private void hitungUmur() {
        Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();

        Calendar lahir = Calendar.getInstance();
        lahir.setTime(tanggalLahir);

        Calendar sekarang = Calendar.getInstance();

        int tahun = sekarang.get(Calendar.YEAR) - lahir.get(Calendar.YEAR);
        int bulan = sekarang.get(Calendar.MONTH) - lahir.get(Calendar.MONTH);
        int hari = sekarang.get(Calendar.DAY_OF_MONTH) - lahir.get(Calendar.DAY_OF_MONTH);

        if (bulan < 0) {
            tahun--;
            bulan += 12;
        }
        if (hari < 0) {
            bulan--;
            hari += sekarang.getActualMaximum(Calendar.DAY_OF_MONTH);
        }

        fieldUmur.setText(tahun + " tahun, " + bulan + " bulan, " + hari + " hari");

        Calendar ulangTahunBerikut = (Calendar) lahir.clone();
        ulangTahunBerikut.set(Calendar.YEAR, sekarang.get(Calendar.YEAR));
        if (ulangTahunBerikut.before(sekarang)) {
            ulangTahunBerikut.add(Calendar.YEAR, 1);
        }

        fieldUlangTahunBerikut.setText(ulangTahunBerikut.getTime().toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PenghitungUmurApp app = new PenghitungUmurApp();
            app.setVisible(true);
        });
    }
}
