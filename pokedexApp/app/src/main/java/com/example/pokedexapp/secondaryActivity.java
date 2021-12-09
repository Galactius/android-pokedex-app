package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class secondaryActivity extends AppCompatActivity
{
    public ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        //mImage = findViewById(R.id.pkmnImg);

        Intent intent = getIntent();
        //intent.getExtra(...);
        //mImage.setImageResource(...);
    }
}