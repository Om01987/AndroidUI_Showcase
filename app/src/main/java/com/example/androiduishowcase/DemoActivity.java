package com.example.androiduishowcase;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        TextView demoTitle = findViewById(R.id.demoTitle);
        TextView demoDescription = findViewById(R.id.demoDescription);

        // Get data passed from ElementsActivity
        String categoryName = getIntent().getStringExtra("categoryName");
        String elementName = getIntent().getStringExtra("elementName");

        // Display simple title indicating which demo is shown
        if (categoryName != null && elementName != null) {
            demoTitle.setText(categoryName + " > " + elementName);
            demoDescription.setText("This screen will showcase the demo for " + elementName + ".");
        } else if (elementName != null) {
            demoTitle.setText(elementName);
            demoDescription.setText("This screen will showcase the demo for " + elementName + ".");
        } else {
            demoTitle.setText("Demo");
            demoDescription.setText("Demo content goes here.");
        }

        // TODO: Here you can dynamically inflate views or fragments to showcase UI element demos.
        // For now, this is a placeholder screen.
    }
}
