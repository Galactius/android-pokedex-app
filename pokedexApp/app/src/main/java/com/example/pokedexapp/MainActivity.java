package com.example.pokedexapp;

import com.example.pokedexapp.PokeAPI;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private final LinkedList<Integer> mPokemonNumberList = new LinkedList<>();
    private final LinkedList<String> mPokemonNameList = new LinkedList<>();
    private final LinkedList<String> mPokemonTypeList = new LinkedList<>();
    private final LinkedList<String> mPokemonAbilityList = new LinkedList<>();
    private final LinkedList<String> mPokemonDescList = new LinkedList<>();
    private final LinkedList<ImageView> mPokemonImageList = new LinkedList<>();

    private int numOfPKMNs = 10;

    private final PokeAPI api = new PokeAPI("1");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processData();
    }

    protected void processData()
    {
        numOfPKMNs = 10; //number of pokemons to process
//        for(int curPKMN = 0; curPKMN < numOfPKMNs; curPKMN++)
//        {
//            //mPokemonNameList.add( INSERT PKMN NAME FROM API);
//            //mPokemonTypeList.add( INSERT PKMN TYPES FROM API AS CONCAT STR);
//        }

        //test api call
        String name = api.getName();
        Log.d("API_TEST", "name = " + name);
    }

}