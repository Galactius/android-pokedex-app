package com.example.pokedexapp;

import com.example.pokedexapp.PokeAPI;
import androidx.appcompat.app.AppCompatActivity;

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
    private final LinkedList<Integer> mPokemonNumberList = new LinkedList<>();
    private final LinkedList<String> mPokemonNameList = new LinkedList<>();
    private final LinkedList<String> mPokemonTypeList = new LinkedList<>();
    private final LinkedList<String> mPokemonAbilityList = new LinkedList<>();
    private final LinkedList<String> mPokemonDescList = new LinkedList<>();
    private final LinkedList<ImageView> mPokemonImageList = new LinkedList<>();

    private int numOfPKMNs = 10;

//    private final PokeAPI api = new PokeAPI("1");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        processData();

        //testing

        String num = "1";
        PokeAPI api = new PokeAPI(num);
        api.execute(num);
        String name = api.getName();
        Log.d("API_TEST", "name = " + name);

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
//        String name = api.getName();
//        Log.d("API_TEST", "name = " + name);


    }
}


class PokeAPI extends AsyncTask<String, Void, String>
{
    //temp
    private String PokeNum;

    PokeAPI(String num)
    {
        //num = PokeNum;
    }

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
        Integer num = null;
        String name = null;
        String type = null;
        String abil = null;
        String desc = null;
        String imgURL = null;
        JSONObject jsonObject = null;
        JSONArray itemsArray = null;
        int i = 0;

        try
        {
            jsonObject = new JSONObject(s);
            itemsArray = jsonObject.getJSONArray("items"); // chooses what array in API to look in

            while(i < itemsArray.length()) //there's a && == null but we'll see if i need it
            {
                //JSONObject pokemon = itemsArray.getJSONObject(i); //gets items from array in API listed in itemsArray
                //JSONObject that gets some info from above
                //JSONObject names = pokemon.getJSONObject("name");


                i++;
            }

            name = jsonObject.getString("name");


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getName()
    {
        return PokeNum;
    }
}