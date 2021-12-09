package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class secondaryActivity extends AppCompatActivity
{
    public ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        mImage = findViewById(R.id.pokeImg);

        Intent intent = getIntent();
        String path = intent.getStringExtra("img");

        Log.d("PATH", path);
        Uri imgUri = Uri.parse(path);

        mImage.setImageURI(imgUri);
    }
}