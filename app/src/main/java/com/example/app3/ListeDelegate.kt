package com.example.app3

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import coil.load


class ListeDelegate(context: Context, id: Int) : ArrayAdapter<String>(context, id) {

    //Stocke les noms des pokémons
    private val pokemonNames = mutableListOf<String>()

    //Stocke les images des pokémons
    private val pokemonImages = mutableListOf<String>()

    //Fonction qui retourne la taille de pokemonNames
    override fun getCount(): Int {
        return pokemonNames.size
    }

    //Fournit une vue pour chaque élément de la liste
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cell = convertView ?: LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        val input = convertView ?: LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false)
        val titleTextView = cell.findViewById<TextView>(R.id.title)
        val imageView = cell.findViewById<ImageView>(R.id.leftside)
        val pokemonName = pokemonNames[position]

        titleTextView.text = pokemonName

        val imageUrl  = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${position+1}.png"
        imageView.load(imageUrl)

        cell.setOnClickListener {
            val context = parent.context
            val intent = Intent(context,PokemonInfos::class.java)
            intent.putExtra("POKEMON_NAME", pokemonName)
            intent.putExtra("POKEMON_IMAGE", imageUrl)
            context.startActivity(intent)
        }

        return cell
    }

    fun fetchPokemonNames() {
        FetchPokemonTask { result ->
            pokemonNames.clear()
            pokemonNames.addAll(result)
            notifyDataSetChanged()
        }.execute()
    }
}
