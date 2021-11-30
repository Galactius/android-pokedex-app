package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private final LinkedList<String> mPokemonNameList = new LinkedList<>();
    private final LinkedList<String> mPokemonTypeList = new LinkedList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processData();
    }

    protected void processData()
    {
        int numOfPKMNs = 10; //number of pokemons to process
        for(int curPKMN = 0; curPKMN < numOfPKMNs; curPKMN++)
        {
            //mPokemonNameList.add( INSERT PKMN NAME FROM API);
            //mPokemonTypeList.add( INSERT PKMN TYPES FROM API AS CONCAT STR);
        }
    }

}