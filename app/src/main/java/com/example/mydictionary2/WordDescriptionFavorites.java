package com.example.mydictionary2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WordDescriptionFavorites extends AppCompatActivity {

    RecyclerView rvDescriptionFavorites;
    TextView txtWordDescriptionFavorites;
    InterfaceAPI interfaceAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_description_favorites);

        rvDescriptionFavorites = findViewById(R.id.rvDescriptionFavorites);
        txtWordDescriptionFavorites = findViewById(R.id.txtWordDescriptionFavorites);

        String definitions = getIntent().getStringExtra("word");
        interfaceAPI = ClientAPI.getClient().create(InterfaceAPI.class);

        Call<List<DataWord>> wordCall = interfaceAPI.getWord(definitions);
        wordCall.enqueue(new Callback<List<DataWord>>() {
            @Override
            public void onResponse(Call<List<DataWord>> call, Response<List<DataWord>> response) {
                if(response.isSuccessful()){

                    List<DataWord> words = response.body();
                    AdapterWordDescription adapter = new AdapterWordDescription(WordDescriptionFavorites.this,words.get(0).getDefinitions());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WordDescriptionFavorites.this);

                    txtWordDescriptionFavorites.setText("Word: " + definitions);
                    rvDescriptionFavorites.setAdapter(adapter);
                    rvDescriptionFavorites.setLayoutManager(layoutManager);
                }
                else{
                    Toast.makeText(WordDescriptionFavorites.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataWord>> call, Throwable t) {
                Toast.makeText(WordDescriptionFavorites.this, "Something wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}