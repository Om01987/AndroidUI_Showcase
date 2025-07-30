package com.example.androiduishowcase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        TextView demoTitle = findViewById(R.id.demoTitle);
        TextView demoDescription = findViewById(R.id.demoDescription);

        // This is the inner LinearLayout inside ScrollView where dynamic demo Views will be added
        LinearLayout demoContentLayout = findViewById(R.id.demoContentLayout);

        // Clear any previous demo Views if reopening this Activity or from back stack
        demoContentLayout.removeAllViews();

        // Get passed data
        String categoryName = getIntent().getStringExtra("categoryName");
        String elementName = getIntent().getStringExtra("elementName");

        // Set the demo title at the top
        if (categoryName != null && elementName != null) {
            demoTitle.setText(categoryName + " > " + elementName);
        } else if (elementName != null) {
            demoTitle.setText(elementName);
        } else {
            demoTitle.setText("Demo");
        }

        // Dynamically create demo widgets for "Common" category elements
        if ("TextView".equals(elementName)) {
            demoDescription.setText("A TextView displays read-only text to the user. It's one of the most commonly used Android views.");
            TextView textViewDemo = new TextView(this);
            textViewDemo.setText("This is a sample TextView");
            textViewDemo.setTextSize(20);
            textViewDemo.setTextColor(Color.rgb(33, 150, 243));  // Material Blue 500
            textViewDemo.setPadding(24, 24, 24, 24);
            textViewDemo.setGravity(Gravity.CENTER);
            demoContentLayout.addView(textViewDemo);

        } else if ("Button".equals(elementName)) {
            demoDescription.setText("A Button can be pressed or clicked by the user to perform an action.");
            Button buttonDemo = new Button(this);
            buttonDemo.setText("Click Me!");
            buttonDemo.setOnClickListener(v -> Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(buttonDemo);

        } else if ("ImageView".equals(elementName)) {
            demoDescription.setText("An ImageView displays images or icons.");
            ImageView imageViewDemo = new ImageView(this);
            imageViewDemo.setImageResource(R.drawable.ic_common);  // Replace with your actual drawable resource
            imageViewDemo.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(imageViewDemo);

        } else if ("RecyclerView".equals(elementName)) {
            demoDescription.setText("RecyclerView efficiently displays a large set of data by recycling views.");
            TextView placeholder = new TextView(this);
            placeholder.setText("RecyclerView demo coming soon!");
            placeholder.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(placeholder);

        } else if ("FragmentContainerView".equals(elementName)) {
            demoDescription.setText("FragmentContainerView is a container for Fragments.");
            TextView placeholder = new TextView(this);
            placeholder.setText("FragmentContainerView demo coming soon!");
            placeholder.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(placeholder);

        } else if ("ScrollView".equals(elementName)) {
            demoDescription.setText("ScrollView allows scrolling of contents that don't fit the screen.");
            ScrollView scrollViewDemo = new ScrollView(this);
            TextView scrollContent = new TextView(this);
            scrollContent.setText("This is some sample scrollable content.\n\n" +
                    "Add multiple lines to see scrolling effect.\n\n" +
                    "Line...\nLine...\nLine...\nLine...\nLine...\nLine...\nLine...");
            scrollContent.setTextSize(18);
            scrollContent.setPadding(24, 24, 24, 24);
            scrollViewDemo.addView(scrollContent);
            demoContentLayout.addView(scrollViewDemo);

        } else if ("Switch".equals(elementName)) {
            demoDescription.setText("A Switch is a two-state toggle switch widget.");
            Switch switchDemo = new Switch(this);
            switchDemo.setText("Toggle me");
            switchDemo.setPadding(24, 24, 24, 24);
            switchDemo.setOnCheckedChangeListener((buttonView, isChecked) ->
                    Toast.makeText(this, "Switch is " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(switchDemo);

        } else {
            demoDescription.setText("Demo not available yet for this UI element.");
            TextView noDemoText = new TextView(this);
            noDemoText.setText("No demo implemented for " + elementName);
            noDemoText.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(noDemoText);
        }
    }
}
