package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pokedexapp.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detailedViewActivity extends AppCompatActivity
{
    public TextView mName;
    public TextView mType;
    public TextView mAbility;
    public String mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        mName = findViewById(R.id.nameText);
        mType = findViewById(R.id.typeText);
        mAbility = findViewById(R.id.abilText);

         Intent intent = getIntent();
         mName.setText(intent.getStringExtra("name"));
         mType.setText(intent.getStringExtra("type"));
         mAbility.setText(intent.getStringExtra("abil"));
         mImage = intent.getStringExtra("img");
    }

    public void launchImageActivity(View view)
    {
        Intent imageIntent = new Intent(this, secondaryActivity.class);
        imageIntent.putExtra("img", mImage);
        startActivity(imageIntent);
    }

}