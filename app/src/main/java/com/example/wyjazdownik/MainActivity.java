package com.example.wyjazdownik;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<TripList> tripLists;
    private TemplateAdapter templateAdapter;
    private RecyclerView recyclerView;
    private Button addTripListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.schemes);
        addTripListButton = findViewById(R.id.addScheme);

        tripLists = templateAdapter.loadTripLists();

        templateAdapter = new TemplateAdapter(tripLists, tripList -> {
            Intent intent = new Intent(MainActivity.this, TemplateActivity.class);
            intent.putExtra("Nazwa", tripList.getName());
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(templateAdapter);

        addTripListButton.setOnClickListener(v -> templateAdapter.addNewTripList(this));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}