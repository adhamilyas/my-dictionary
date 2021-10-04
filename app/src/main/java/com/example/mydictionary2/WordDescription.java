package com.example.mydictionary2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WordDescription extends AppCompatActivity {

    RecyclerView rvDescription;
    Button btnSaveDescription;
    TextView txtWordDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_description);

        rvDescription = findViewById(R.id.rvDescription);
        btnSaveDescription = findViewById(R.id.btnSaveDescription);
        txtWordDescription = findViewById(R.id.txtWordDescription);

        DataWord definitions = getIntent().getParcelableExtra("word");
        AdapterWordDescription adapter = new AdapterWordDescription(WordDescription.this,definitions.getDefinitions());
        LinearLayoutManager layoutManager = new LinearLayoutManager(WordDescription.this);
        txtWordDescription.setText("Word: " + definitions.getDataWord());
        rvDescription.setLayoutManager(layoutManager);
        rvDescription.setAdapter(adapter);

        btnSaveDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper helper = new DatabaseHelper(WordDescription.this);
                helper.addFavorites(definitions.getDataWord());
                Toast.makeText(WordDescription.this, definitions.getDataWord() + " successfully added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }
}