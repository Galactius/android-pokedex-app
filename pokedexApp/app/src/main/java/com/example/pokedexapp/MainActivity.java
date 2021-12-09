//////////////////////////
///////Nate Diaz//////////
///////Daniel Perez///////
///////Khang Bui//////////
//////////////////////////

package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private final LinkedList<String> mPokemonNumberList = new LinkedList<String>();
    private final LinkedList<String> mPokemonNameList = new LinkedList<>();
    private final LinkedList<String> mPokemonTypeList = new LinkedList<>();
    private final LinkedList<String> mPokemonAbilityList = new LinkedList<>();
    private final LinkedList<Uri> mPokemonImageList = new LinkedList<>();
    private final LinkedList<String> mPokemonLargeImageList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private pokedexAdapter mAdapter;

    String name = null;
    String type = null;
    String abil = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 1; i <= 10; i++)
        {
            mPokemonNumberList.add(String.valueOf(i));
            PokeAPI api = new PokeAPI();
            api.execute(String.valueOf(i));

        }
    }

    protected void processData(String name, String type, String abil)
    {
        this.name = name;
        this.type = type;
        this.abil = abil;

        mPokemonNameList.add(name.substring(0, 1).toUpperCase() + name.substring(1));

        mPokemonTypeList.add(type.substring(0, 1).toUpperCase() + type.substring(1));

        mPokemonAbilityList.add(abil.substring(0, 1).toUpperCase() + abil.substring(1));

        String imgNum = String.valueOf(mPokemonNameList.size());
        Log.d("imgNumTest", String.valueOf(mPokemonNameList.size()));

        Uri sprite = Uri.parse("android.resource://com.example.pokedexapp/drawable/a" + imgNum);
        mPokemonImageList.add(sprite);

        String path = "android.resource://com.example.pokedexapp/drawable/b" + imgNum;
        mPokemonLargeImageList.add(path);

        AdapterSetup();
    }

    private void AdapterSetup()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new pokedexAdapter(this, mPokemonNumberList, mPokemonNameList, mPokemonTypeList, mPokemonAbilityList, mPokemonImageList, mPokemonLargeImageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    class PokeAPI extends AsyncTask<String, Void, String>
    {
        protected String getPokeInfo(String query) throws IOException
        {
            //Main API URL
            String apiURL = "https://pokeapi.co/api/v2/pokemon/";
            apiURL += query; // Adds the specific shit we need to look up in the api to the URL

            //Connects API
            URL requestURL = new URL(apiURL);
            HttpURLConnection urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Receives Response
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //Creates a String with the Response
            StringBuilder builder = new StringBuilder();
            String line; //sends response back as string, might change to make it easier???

            while((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append("\n");
            }

            String jsonString = builder.toString();
            Log.d("PokeAPIJsonString", jsonString);

            return jsonString;
        }

        @Override
        protected String doInBackground(String... strings)
        {
            String jsonString = null;

            try
            {
                jsonString = getPokeInfo(strings[0]);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            return jsonString;
        }

        protected void onPostExecute(String s) //s comes from doInBackground
        {
            super.onPostExecute(s);

            JSONObject jsonObject;
            JSONArray abilArray;
            JSONArray typesArray;

            try
            {
                jsonObject = new JSONObject(s);

                //gets name
                name = jsonObject.getString("name");
                Log.d("NameAPICallTest", name);

                //gets first type
                typesArray = jsonObject.getJSONArray("types");
                JSONObject types = typesArray.getJSONObject(0);
                JSONObject type1 = types.getJSONObject("type");
                type = type1.getString("name");
                Log.d("TypeAPICallTest", type);

                //gets first ability
                abilArray = jsonObject.getJSONArray("abilities");
                JSONObject abilities = abilArray.getJSONObject(0);
                JSONObject abil1 = abilities.getJSONObject("ability");
                abil = abil1.getString("name");
                Log.d("AbilityAPICallTest", abil);

                //sends to processData
                MainActivity.this.processData(name, type, abil);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}


