package com.example.androiduishowcase;

public class UiCategory {
    private String name;
    private int iconResId;

    public UiCategory(String name, int iconResId) {
        this.name = name;
        this.iconResId = iconResId;
    }

    public String getName() {
        return name;
    }

    public int getIconResId() {
        return iconResId;
    }
}

