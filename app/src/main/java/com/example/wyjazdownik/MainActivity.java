package com.example.wyjazdownik;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    EditText input;
    ImageView add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        input = findViewById(R.id.inputItem);
        add = findViewById(R.id.addItem);
        items = new ArrayList<>();
        items.add("something");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = items.get(i);
                makeToast(name);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                makeToast("Przytrzymano dłużej na:" + items.get(i));
                return false;
            }
        });

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();

                if (text == null || text.length() == 0) {
                    makeToast("dodaj przedmiot.");

                } else {
                    addItem(text);
                    input.setText("");
                    makeToast("Dodano do listy: " + text);
                }
            }
        });
    }

    public void addItem(String item) {
        items.add(item);
        adapter.notifyDataSetChanged();
    }

    Toast t;

    private void makeToast(String s) {
        if (t != null) {
            t.cancel();
        }

        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}