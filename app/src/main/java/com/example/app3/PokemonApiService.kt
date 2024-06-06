package com.example.app3

import android.os.AsyncTask
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class FetchPokemonTask(private val callback: (List<String>) -> Unit) : AsyncTask<Void, Void, List<String>>() {

    //Fetch en tâche de fond
    override fun doInBackground(vararg params: Void?): List<String> {
        return fetchPokemonNames()
    }

    // Méthode onPostExecute qui est appelée après l'exécution de doInBackground
    override fun onPostExecute(result: List<String>) {
        super.onPostExecute(result)
        // Appelle la callback avec le résultat obtenu
        callback(result)
    }

    //Fonction qui fait un GET à l'api de la liste des pokémons
    private fun fetchPokemonNames(): List<String> {
        val url = URL("https://pokeapi.co/api/v2/pokemon?limit=100")
        //Instancie une méthode de connection
        val urlConnection = url.openConnection() as HttpURLConnection
        //Stocke les noms des pokémons
        val pokemonNames = mutableListOf<String>()

        try {
            // Lit la réponse ligne par ligne et l'ajoute à StringBuilder
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val response = StringBuilder()
            var line: String?

            // Lit la réponse ligne par ligne et l'ajoute à StringBuilder
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }

            // Convertit la réponse en objet JSON
            val jsonObject = JSONObject(response.toString())

            // Obtient le tableau "results" du JSON contenant les noms des Pokémon
            val results = jsonObject.getJSONArray("results")

            for (i in 0 until results.length()) {
                val pokemon = results.getJSONObject(i)
                val name = pokemon.getString("name")
                pokemonNames.add(name)
            }
        } finally {
            urlConnection.disconnect()
        }

        return pokemonNames
    }
}
