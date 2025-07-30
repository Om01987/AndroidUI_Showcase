package com.example.androiduishowcase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    List<UiCategory> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge layout
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        // Apply window insets padding to root view with id 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare category data
        categoryList = new ArrayList<>();
        categoryList.add(new UiCategory("Common", R.drawable.ic_common));
        categoryList.add(new UiCategory("Text", R.drawable.ic_text));
        categoryList.add(new UiCategory("Buttons", R.drawable.ic_buttons));
        categoryList.add(new UiCategory("Widgets", R.drawable.ic_widgets));
        categoryList.add(new UiCategory("Layouts", R.drawable.ic_layouts));
        categoryList.add(new UiCategory("Containers", R.drawable.ic_containers));
        categoryList.add(new UiCategory("Helpers", R.drawable.ic_helpers));
        categoryList.add(new UiCategory("Google", R.drawable.ic_google));
        categoryList.add(new UiCategory("Legacy", R.drawable.ic_legacy));


        // add more categories here if you want

        // Setup adapter with click listener
        adapter = new CategoryAdapter(this, categoryList, category -> {
            Intent intent = new Intent(MainActivity.this, ElementsActivity.class);
            intent.putExtra("categoryName", category.getName());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}

