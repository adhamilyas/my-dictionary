package com.example.mydictionary2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterWordDescription extends RecyclerView.Adapter<AdapterWordDescription.ViewHolder>{

    private Context ctx;
    private List<DataDefinition> definitions;

    public AdapterWordDescription(Context context, List<DataDefinition> definitions) {
        this.ctx = context;
        this.definitions = definitions;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.custom_description_layout, parent,false);
        return new AdapterWordDescription.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.txtType.setText("Type : " + definitions.get(position).type);
        holder.txtDefinition.setText(definitions.get(position).getDefinition());

        if(definitions.get(position).getImage_url() != null){
            Picasso.get().load(definitions.get(position).getImage_url()).into(holder.imgWord);
        }
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtType;
        TextView txtDefinition;
        ImageView imgWord;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txtType);
            txtDefinition = itemView.findViewById(R.id.txtDefinition);
            imgWord = itemView.findViewById(R.id.imgWord);
        }
    }

}
