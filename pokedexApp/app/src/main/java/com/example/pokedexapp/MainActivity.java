package com.example.pokedexapp;

//import com.example.pokedexapp.PokeAPI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

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
    private final LinkedList<String> mPokemonDescList = new LinkedList<>();
    private final LinkedList<ImageView> mPokemonImageList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private pokedexAdapter mAdapter;
    private Context context;

    String num = null;
    String name;
    String type = null;
    String abil = null;
    String desc = null;
    String imgURL = null;

    private int numOfPKMNs = 10;

//    private final PokeAPI api = new PokeAPI("1");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //processData();

        //testing

        num = "1";
        PokeAPI api = new PokeAPI();
        api.execute(num);

        Intent detailIntent = new Intent(this, detailedViewActivity.class);
        detailIntent.putExtra("name", mPokemonNameList);

    }

    protected void processData(String result)
    {
        name = result;
        Log.d("processDataREACHtest", name);

        mPokemonNameList.add(name);
        Log.d("ListAddTest", mPokemonNameList.get(0));

        mPokemonNumberList.add("1");

        CALL_TEST();
    }

    private void CALL_TEST()
    {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new pokedexAdapter(this, mPokemonNumberList, mPokemonNameList);
        mRecyclerView.setAdapter(mAdapter);
    }

    class PokeAPI extends AsyncTask<String, Void, String>
    {
        //temp

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

            //Recieves Respone
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
            num = null;
            //name = null;
            type = null;
            abil = null;
            desc = null;
            imgURL = null;
            JSONObject jsonObject = null;
            JSONArray itemsArray = null;
            JSONArray MainTypeArray = null;
            JSONArray typesArray = null;
            int i = 0;

            try
            {
                jsonObject = new JSONObject(s);


//              itemsArray = jsonObject.getJSONArray("items"); // chooses what array in API to look in
//
//                while(i < itemsArray.length()) //there's a && == null but we'll see if i need it
//                {
//                    //JSONObject pokemon = itemsArray.getJSONObject(i); //gets items from array in API listed in itemsArray
//                    //JSONObject that gets some info from above
//                    //JSONObject names = pokemon.getJSONObject("name");
//
//
//                    i++;
//                }


                //gets name
                name = jsonObject.getString("name");
                Log.d("NameAPICallTest", name);

                //gets first type
                typesArray = jsonObject.getJSONArray("types");
                JSONObject types = typesArray.getJSONObject(0);
                JSONObject type1 = types.getJSONObject("type");
                type = type1.getString("name");
                Log.d("TypeAPICallTest", type);




                MainActivity.this.processData(name);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}


