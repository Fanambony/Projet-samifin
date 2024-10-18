package com.example.gestionrh.utils;

public class FormatUtil {
    public static String formatMontant(Double montant) {
        return String.format("%,.0f", montant).replace(",", " ").trim() + " Ar";
    }
}
