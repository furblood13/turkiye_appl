package com.example.turkiye_appv01.view

import android.os.AsyncTask
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turkiye_appv01.R
import com.example.turkiye_appv01.Sehir
import com.example.turkiye_appv01.adapter.SehirAdapter
import com.example.turkiye_appv01.databinding.ActivityCityInfoBinding
import com.example.turkiye_appv01.databinding.ActivityCitysBinding
import com.example.turkiye_appv01.db.SehirDatabase

class CitysActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCitysBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var sehirAdapter: SehirAdapter
    private lateinit var sehirlist: List<Sehir>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCitysBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = SehirDatabase.getInstance(this)
        FetchSehirTask(db).execute()
    }

    private fun enableEdgeToEdge() {
        // Implement edge-to-edge behavior if necessary
    }

    inner class FetchSehirTask(private val db: SehirDatabase) : AsyncTask<Void, Void, List<Sehir>>() {
        override fun doInBackground(vararg params: Void?): List<Sehir> {
            return db.sehirdao.getAll()
        }

        override fun onPostExecute(result: List<Sehir>?) {
            if (result != null) {
                sehirlist = result
                sehirAdapter = SehirAdapter(sehirlist)
                recyclerView.adapter = sehirAdapter
            }
        }
    }
}