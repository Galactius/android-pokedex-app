package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detailedViewActivity extends AppCompatActivity
{
    public TextView mType;
    public TextView mDesc;
    public TextView mAbility;
    public ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

//         mType = findViewById(R.id.typeText);
//         mDesc = findViewById(R.id.descText);
//         mAbility = findViewById(R.id.abilText);
//         mImage = findViewById(R.id.img);
//
         Intent intent = getIntent();
         mType.setText(intent.getStringExtra("type"));
         mDesc.setText(intent.getStringExtra("desc"));
         mAbility.setText(intent.getStringExtra("abil"));

         //mImage.setImageResource(...);



    }
}