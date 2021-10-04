package com.example.mydictionary2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.ViewHolder> {

    private Context ctx;
    private List<String> words;

    public AdapterFavorites(Context ctx, List<String> words) {
        this.ctx = ctx;
        this.words = words;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.custom_favorites_item, parent,false);
        return new AdapterFavorites.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtWordFavorites.setText(words.get(position));
        holder.btnDeleteFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper handler = new DatabaseHelper(ctx);
                handler.deleteFavorites(words.get(position));
                Toast.makeText(ctx, words.get(position) + " has been removed from favorites", Toast.LENGTH_SHORT).show();
                words.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.cvFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,WordDescriptionFavorites.class);
                intent.putExtra("word",words.get(position));
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtWordFavorites;
        Button btnDeleteFavorites;
        CardView cvFavorites;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtWordFavorites = itemView.findViewById(R.id.txtWordFavorites);
            btnDeleteFavorites = itemView.findViewById(R.id.btnDeleteFavorites);
            cvFavorites = itemView.findViewById(R.id.cvFavorites);
        }
    }
}
