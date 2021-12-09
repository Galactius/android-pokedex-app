package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pokedexapp.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detailedViewActivity extends AppCompatActivity
{
    public TextView mName;
    public TextView mType;
    public TextView mDesc;
    public TextView mAbility;
    public ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        mName = findViewById(R.id.name);
        mType = findViewById(R.id.typeText);
        //mDesc = findViewById(R.id.descText);
        //mAbility = findViewById(R.id.abilText);
        //mImage = findViewById(R.id.img);

         Intent intent = getIntent();
         mName.setText(intent.getStringExtra("name"));
         mType.setText(intent.getStringExtra("type"));
         //mDesc.setText(intent.getStringExtra("desc"));
         //mAbility.setText(intent.getStringExtra("abil"));

         //mImage.setImageResource(...);



    }
}