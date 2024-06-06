package com.example.app3

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.os.AsyncTask
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get the list in listView by id
        val table = findViewById<ListView>(R.id.liste)
        val adapter = ListeDelegate(this, R.layout.listitem)
        table.adapter = adapter
        // Fetch Pokémon names
        adapter.fetchPokemonNames()

    }
}

