package com.example.marvelapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvelapp.API.MarvelApi;
import com.example.marvelapp.DetailedViewAtivity;
import com.example.marvelapp.Models.Marvel;
import com.example.marvelapp.R;

import java.util.ArrayList;
import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.viewHolder> implements Filterable {
    Context context;
    List<Marvel> marvelList;
    List<Marvel> backup;


    public MarvelAdapter(Context context, List<Marvel> marvelList) {
        this.context = context;
        this.marvelList = marvelList;
        backup = new ArrayList<>(marvelList);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.sample_marvel_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        Marvel marvel = marvelList.get(position);
        holder.nameTextView.setText(marvel.getName());
        holder.teamTextView.setText(marvel.getTeam());
        holder.createdByTextView.setText(marvel.getCreatedBy());
        holder.firstAppearanceTextView.setText(marvel.getFirstAppearance());
        Glide.with(context).load(marvel.getImageUrl()).into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailedViewAtivity.class);
                intent.putExtra("name",marvelList.get(position).getName());
                intent.putExtra("realname",marvelList.get(position).getRealName());
                intent.putExtra("team",marvelList.get(position).getTeam());
                intent.putExtra("firstappearance",marvelList.get(position).getFirstAppearance());
                intent.putExtra("createdby",marvelList.get(position).getCreatedBy());
                intent.putExtra("publisher",marvelList.get(position).getPublisher());
                intent.putExtra("imageurl",marvelList.get(position).getImageUrl());
                intent.putExtra("bio",marvelList.get(position).getBio());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return marvelList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Marvel> filteredData = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredData.addAll(backup);
                notifyDataSetChanged();
            } else {
                for (Marvel marvel : marvelList) {
                    if (marvel.getName().toString().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredData.add(marvel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            marvelList.clear();
            marvelList.addAll((List<Marvel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class viewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameTextView;
        public TextView teamTextView;
        public TextView firstAppearanceTextView;
        public TextView createdByTextView;
        public CardView cardView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            imageView = itemView.findViewById(R.id.image);
            nameTextView = itemView.findViewById(R.id.name);
            teamTextView = itemView.findViewById(R.id.team);
            firstAppearanceTextView = itemView.findViewById(R.id.first_appearance);
            createdByTextView = itemView.findViewById(R.id.created_by);

        }
    }
}
