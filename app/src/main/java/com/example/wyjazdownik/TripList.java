package com.example.wyjazdownik;

import java.util.ArrayList;

public class TripList {
    private String name;
    private ArrayList<Item> items;

    public TripList(String name, ArrayList<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
