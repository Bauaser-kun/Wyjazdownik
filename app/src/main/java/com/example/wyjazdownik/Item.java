package com.example.wyjazdownik;

public class Item {
    private String name;
    private int count;

    public Item(int count, String name) {
        this.count = count;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
