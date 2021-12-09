package com.example.pokedexapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
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

    private LinkedList<String> mPokemonNumberList;
    private LinkedList<String> mPokemonNameList;
    private LinkedList<String> mPokemonTypeList;
    private LinkedList<String> mPokemonAbilityList;
    private LinkedList<Uri> mPokemonImageList;
    private LinkedList<String> mPokemonLargeImageList;

    public pokedexAdapter(Context context, LinkedList<String> numList, LinkedList<String> nameList, LinkedList<String> typeList,
                                                                                    LinkedList<String> abilList, LinkedList<Uri> imgList, LinkedList<String> LIMG)
    {
        mInflater = LayoutInflater.from(context);
        mPokemonNumberList = numList;
        mPokemonNameList = nameList;
        mPokemonTypeList = typeList;
        mPokemonAbilityList = abilList;
        this.context = context;
        mPokemonImageList = imgList;
        mPokemonLargeImageList = LIMG;
    }

    @NonNull
    @Override
    public pokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mItemView = mInflater.inflate(R.layout.pokedexitem, parent, false);

        return new pokedexViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull pokedexViewHolder holder, int position)
    {
        String mCurrentName = mPokemonNameList.get(position).substring(0, 1).toUpperCase() + mPokemonNameList.get(position).substring(1);
        String mCurrentNumber = "Pokemon #" + mPokemonNumberList.get(position);
        Uri mCurrentImage = mPokemonImageList.get(position);

        holder.mNameView.setText(mCurrentName);
        holder.mNumberView.setText(mCurrentNumber);
        holder.mImageView.setImageURI(mCurrentImage);
    }

    @Override
    public int getItemCount() {
        return mPokemonNameList.size();
    }

    class pokedexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mNameView, mNumberView;
        private ImageView mImageView;

        public pokedexViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mNameView = itemView.findViewById(R.id.name);
            mNumberView = itemView.findViewById(R.id.number);
            mImageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent = new Intent(context, detailedViewActivity.class);
            Log.d("AdapterPositionTest", mPokemonNameList.get(getAdapterPosition()));

            intent.putExtra("name", mPokemonNameList.get(getAdapterPosition()));
            intent.putExtra("type", mPokemonTypeList.get(getAdapterPosition()));
            intent.putExtra("abil", mPokemonAbilityList.get(getAdapterPosition()));
            intent.putExtra("img", mPokemonLargeImageList.get(getAdapterPosition()));

            context.startActivity(intent);
        }
    }
}
