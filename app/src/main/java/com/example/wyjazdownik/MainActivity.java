package com.example.wyjazdownik;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TripList> tripLists;
    private TemplateAdapter templateAdapter;
    //private RecyclerView recyclerView;
    private Button addTripListButton;

    private View recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.schemes);
        addTripListButton = findViewById(R.id.addScheme);

        tripLists = loadTripLists();

        templateAdapter = new TemplateAdapter(tripLists, this::openTripListActivity);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(templateAdapter);

        addTripListButton.setOnClickListener(v -> addNewTripList(this.getCurrentFocus()));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addNewTripList(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Template");

        final EditText input = new EditText(this);
        input.setHint("Nazwa listy");
        builder.setView(input);

        builder.setPositiveButton("Dodaj", (dialog, which) -> {
            String templateName = input.getText().toString().trim();
            if (!templateName.isEmpty()) {
                TripList newTripList = new TripList(templateName, new ArrayList<>());
                tripLists.add(newTripList);
                saveTripLists();
                templateAdapter.updateTripLists(tripLists);
            }
        });

        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void saveTripLists() {
        SharedPreferences prefs = getSharedPreferences("templates", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(tripLists);
        editor.putString("trip_list", json);
        editor.apply();
    }

    ArrayList<TripList> loadTripLists() {
        SharedPreferences prefs = getSharedPreferences("templates", MODE_PRIVATE);
        String json = prefs.getString("trip_list", null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<TripList>>() {}.getType();
            return  gson.fromJson(json, type);
        } else {
            return new ArrayList<>();
        }
    }

    private void openTripListActivity(TripList list) {
        Intent intent = new Intent(this, TemplateActivity.class);
        intent.putExtra("Nazwa", list.getName());
        startActivity(intent);
    }
}