package com.example.kasrtapp.model


data class DataItem(
    val nama_depan: String,
    val nama_belakang: String,
    val email: String,
    val alamat: String,
    val total_iuran_individu: Int,
    val pengeluaran_iuran_warga: Int,
    val pemanfaatan_iuran: String,
    val gambar: String
)
