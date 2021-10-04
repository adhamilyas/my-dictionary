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

public class AdapterExplore extends RecyclerView.Adapter<AdapterExplore.ViewHolder> {
    private Context ctx;
    private List<DataWord> words;

    public AdapterExplore(Context context, List<DataWord> words) {
        this.ctx = context;
        this.words = words;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.custom_explore_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtWord.setText(words.get(position).getDataWord());
        holder.btnSaveExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(ctx);
                helper.addFavorites(words.get(position).getDataWord());
               Toast.makeText(ctx, words.get(position) + " successfully added to favoritessssss", Toast.LENGTH_SHORT).show();
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,WordDescription.class);
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
        TextView txtWord;
        Button btnSaveExplore;
        CardView cardView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            btnSaveExplore = itemView.findViewById(R.id.btnSaveExplore);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}
