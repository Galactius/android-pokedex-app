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

    private LinkedList<String> mPokemonNumberList = new LinkedList<>();
    private LinkedList<String> mPokemonNameList = new LinkedList<>();
    private LinkedList<String> mPokemonTypeList = new LinkedList<>();
    private LinkedList<String> mPokemonAbilityList = new LinkedList<>();
    private LinkedList<String> mPokemonDescList = new LinkedList<>();
    //private LinkedList<ImageView> mPokemonImageList = new LinkedList<>();

    public pokedexAdapter(Context context, LinkedList<String> numList, LinkedList<String> nameList, LinkedList<String> typeList,
                                                                                    LinkedList<String> abilList, LinkedList<String> descList)
    {
        mInflater = LayoutInflater.from(context);
        mPokemonNumberList = numList;
        mPokemonNameList = nameList;
        mPokemonTypeList = typeList;
        mPokemonAbilityList = abilList;
        mPokemonDescList = descList;
        //mPokemonImageList = imgList;
    }

    @NonNull
    @Override
    public pokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.pokedexitem, parent, false);
        return new pokedexViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull pokedexViewHolder holder, int position)
    {
        String mCurrentName = mPokemonNameList.get(position).toString();
        String mCurrentNumber = mPokemonNumberList.get(position).toString();
        holder.mNameView.setText(mCurrentName);
        holder.mNumberView.setText(mCurrentNumber);
    }

    @Override
    public int getItemCount() {
        return mPokemonNameList.size();
    }

    class pokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mNameView, mNumberView;
        private pokedexAdapter adapter;

        public pokedexViewHolder(@NonNull View itemView, pokedexAdapter adapter)
        {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.name);
            mNumberView = (TextView) itemView.findViewById(R.id.number);

            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, detailedViewActivity.class);
            intent.putExtra("name", mPokemonNameList);
            intent.putExtra("type", mPokemonTypeList);
            //intent.putExtra("abil", mPokemonAbilityList);
            //intent.putExtra("desc", mPokemonDescList);
            context.startActivity(intent);
        }
    }

}
