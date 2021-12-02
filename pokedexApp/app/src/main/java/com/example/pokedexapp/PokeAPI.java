package com.example.pokedexapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeAPI extends AsyncTask<String, Void, String>
{

    PokeAPI()
    {

    }

    protected String getPokeInfo(String query) throws IOException
    {
        //Main API URL
        String apiURL = "https://pokeapi.co/api/v2/";
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
        return null;
    }
}