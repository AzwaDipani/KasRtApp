package com.example.kasrtapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kasrtapp.model.ResponseUser
import com.example.kasrtapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LaporanActivity : AppCompatActivity() {

    private lateinit var adapter: PemanfaatanAdpater
    private lateinit var rv_laporan: RecyclerView
    private lateinit var jumlahIuranBulananTextView: TextView
    private lateinit var totalIuranTextView: TextView
    private lateinit var pengeluaranTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan)

        rv_laporan = findViewById(R.id.rv_laporan)
        jumlahIuranBulananTextView = findViewById(R.id.jumlahIuranBulananTextView)
        totalIuranTextView = findViewById(R.id.totalIuranTextView)
        pengeluaranTextView = findViewById(R.id.pengeluaranTextView)

        adapter = PemanfaatanAdpater(mutableListOf())

        rv_laporan.layoutManager = LinearLayoutManager(this)
        rv_laporan.adapter = adapter

        getPemanfaatan()
    }

    private fun getPemanfaatan() {
        val apiService = ApiConfig.getApiService()
        val client = apiService.getPemanfaatan()

        client.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(call: Call<ResponseUser>, response: Response<ResponseUser>) {
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data
                    if (dataArray != null) {
                        var totalIuranBulanan = 0
                        for (dataItem in dataArray) {
                            totalIuranBulanan += dataItem.total_iuran_individu
                        }
                        var totalPengeluaran = 0
                        for(dataItem in dataArray) {
                            totalPengeluaran += dataItem.pengeluaran_iuran_warga
                        }
                        var rekapIuran = totalIuranBulanan - totalPengeluaran
                        jumlahIuranBulananTextView.text = "1. Jumlah Iuran Bulanan : $totalIuranBulanan"
                        pengeluaranTextView.text = "3. Total Pengeluaran : $totalPengeluaran"
                        totalIuranTextView.text = "4. Rekap Total Iuran : $rekapIuran"


                        adapter.setPemanfaatan(dataArray)
                    } else {
                        Toast.makeText(this@LaporanActivity, "Data not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LaporanActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) {
                Toast.makeText(this@LaporanActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}
