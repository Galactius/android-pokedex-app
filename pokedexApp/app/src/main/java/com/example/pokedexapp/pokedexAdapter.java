package com.example.pokedexapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class pokedexAdapter extends RecyclerView.Adapter<pokedexAdapter.pokedexViewHolder>
{
    private LayoutInflater mInflater;
    private Context context;

    private LinkedList<Integer> mPokemonNumberList = new LinkedList<>();
    private LinkedList<String> mPokemonNameList = new LinkedList<>();
    private LinkedList<String> mPokemonTypeList = new LinkedList<>();
    private LinkedList<String> mPokemonAbilityList = new LinkedList<>();
    private LinkedList<String> mPokemonDescList = new LinkedList<>();
    private LinkedList<ImageView> mPokemonImageList = new LinkedList<>();

    public pokedexAdapter(Context context, LinkedList<Integer> numList, LinkedList<String> nameList,
                          LinkedList<String> typeList, LinkedList<String> abilityList, LinkedList<String> descList, LinkedList<ImageView> imageList)
    {
        mInflater = LayoutInflater.from(context);
        mPokemonNumberList = numList;
        mPokemonNameList = nameList;
        mPokemonTypeList = typeList;
        mPokemonAbilityList = abilityList;
        mPokemonDescList = descList;
        mPokemonImageList = imageList;
    }

    @NonNull
    @Override
    public pokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull pokedexViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class pokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mNameView, mNumberView;
        private ImageView mImageView;
        private pokedexAdapter adapter;

        public pokedexViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.name);
            //mNumberView = (TextView) itemView.findViewById()
            mImageView = (ImageView) itemView.findViewById(R.id.image);

            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, detailedViewActivity.class);
            context.startActivity(intent);
        }
    }

}
