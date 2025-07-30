package com.example.androiduishowcase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


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
            imageViewDemo.setImageResource(R.drawable.ic_img);
            imageViewDemo.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(imageViewDemo);

        } else if ("RecyclerView".equals(elementName)) {
            demoDescription.setText("RecyclerView efficiently displays a large set of data by recycling views.");

            androidx.recyclerview.widget.RecyclerView recyclerViewDemo = new androidx.recyclerview.widget.RecyclerView(this);

            // Set RecyclerView layout params for proper sizing
            int heightInDp = 300;
            float scale = getResources().getDisplayMetrics().density;
            int heightInPx = (int) (heightInDp * scale + 0.5f);
            LinearLayout.LayoutParams recyclerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    heightInPx);
            recyclerViewDemo.setLayoutParams(recyclerParams);

            // Set LinearLayoutManager
            recyclerViewDemo.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));

            // Sample data
            List<String> sampleItems = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                sampleItems.add("Item " + i);
            }

            // Adapter class (inline here)
            class SimpleAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<SimpleAdapter.ViewHolder> {
                List<String> items;
                SimpleAdapter(List<String> items) {
                    this.items = items;
                }

                class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
                    TextView textView;
                    ViewHolder(TextView v) {
                        super(v);
                        textView = v;
                    }
                }

                @Override
                public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    TextView tv = new TextView(parent.getContext());
                    tv.setPadding(24, 24, 24, 24);
                    tv.setTextSize(18);
                    return new ViewHolder(tv);
                }

                @Override
                public void onBindViewHolder(ViewHolder holder, int position) {
                    holder.textView.setText(items.get(position));
                }

                @Override
                public int getItemCount() {
                    return items.size();
                }
            }

            recyclerViewDemo.setAdapter(new SimpleAdapter(sampleItems));
            demoContentLayout.addView(recyclerViewDemo);



    } else if ("FragmentContainerView".equals(elementName)) {
            demoDescription.setText("FragmentContainerView hosts a Fragment within a layout.");

            // Add FragmentContainerView dynamically
            androidx.fragment.app.FragmentContainerView fragmentContainerView = new androidx.fragment.app.FragmentContainerView(this);

            int heightInDp = 300;
            float scale = getResources().getDisplayMetrics().density;
            int heightInPx = (int) (heightInDp * scale + 0.5f);

            LinearLayout.LayoutParams fcParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    heightInPx);
            fragmentContainerView.setLayoutParams(fcParams);
            fragmentContainerView.setId(View.generateViewId());

            demoContentLayout.addView(fragmentContainerView);

            // Load SimpleDemoFragment dynamically
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(fragmentContainerView.getId(), new SimpleDemoFragment())
                    .commit();



    } else if ("ScrollView".equals(elementName)) {
            demoDescription.setText("ScrollView allows scrolling of contents that don't fit the screen.");

            ScrollView scrollViewDemo = new ScrollView(this);

            // Set a fixed height for ScrollView so scrolling can occur
            int heightInDp = 300; // change this as needed
            float scale = getResources().getDisplayMetrics().density;
            int heightInPx = (int) (heightInDp * scale + 0.5f);
            LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    heightInPx);
            scrollViewDemo.setLayoutParams(scrollParams);

            TextView scrollContent = new TextView(this);
            scrollContent.setText("This is some sample scrollable content.\n\n" +
                    "Added multiple lines to see scrolling effect.\n\n" +
                    "Line1...\nLine2...\nLine3...\nLine4...\nLine5...\nLine6...\nLine7...\nLine8...\nLine9...\nLine10...\n" +
                    "Line11...\nLine12...\nLine13...\nLine14...\nLine15...\nLine16...\nLine17...\nLine18...\nLine19...");
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

        } else if ("TextView".equals(elementName)) {
            demoDescription.setText("A TextView displays static text.");
            TextView textViewDemo = new TextView(this);
            textViewDemo.setText("This is a TextView.");
            textViewDemo.setTextSize(20);
            textViewDemo.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(textViewDemo);

        } else if ("Plain Text".equals(elementName)) {
            demoDescription.setText("Plain Text input allows the user to enter regular text.");
            android.widget.EditText editText = new android.widget.EditText(this);
            editText.setHint("Enter plain text here");
            editText.setSingleLine(true);
            editText.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(editText);

        } else if ("Password".equals(elementName)) {
            demoDescription.setText("Password input hides characters for privacy.");
            android.widget.EditText passwordInput = new android.widget.EditText(this);
            passwordInput.setHint("Enter password");
            passwordInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordInput.setSingleLine(true);
            passwordInput.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(passwordInput);

        } else if ("Password (Numeric)".equals(elementName)) {
            demoDescription.setText("Numeric Password input, suitable for PINs.");
            android.widget.EditText numericPassword = new android.widget.EditText(this);
            numericPassword.setHint("Enter numeric password");
            numericPassword.setInputType(android.text.InputType.TYPE_CLASS_NUMBER |
                    android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            numericPassword.setSingleLine(true);
            numericPassword.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(numericPassword);

        } else if ("E-mail".equals(elementName)) {
            demoDescription.setText("E-mail input optimized for email addresses.");
            android.widget.EditText emailInput = new android.widget.EditText(this);
            emailInput.setHint("Enter email");
            emailInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            emailInput.setSingleLine(true);
            emailInput.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(emailInput);

        } else if ("Phone".equals(elementName)) {
            demoDescription.setText("Phone input optimized for phone numbers.");
            android.widget.EditText phoneInput = new android.widget.EditText(this);
            phoneInput.setHint("Enter phone number");
            phoneInput.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
            phoneInput.setSingleLine(true);
            phoneInput.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(phoneInput);

        } else if ("Postal Address".equals(elementName)) {
            demoDescription.setText("Input field optimized for postal addresses.");
            android.widget.EditText postalAddress = new android.widget.EditText(this);
            postalAddress.setHint("Enter postal address");
            postalAddress.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
            postalAddress.setMinLines(2);
            postalAddress.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(postalAddress);

        } else if ("Multiline Text".equals(elementName)) {
            demoDescription.setText("Multiline text input with multiple lines.");
            android.widget.EditText multilineText = new android.widget.EditText(this);
            multilineText.setHint("Enter multiline text");
            multilineText.setInputType(android.text.InputType.TYPE_CLASS_TEXT |
                    android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            multilineText.setMinLines(3);
            multilineText.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(multilineText);
        }   else if ("Time".equals(elementName)) {
            demoDescription.setText("Time input lets users pick a specific time (uses TimePickerDialog).");
            Button timeButton = new Button(this);
            timeButton.setText("Pick a time");
            timeButton.setOnClickListener(v -> {
                java.util.Calendar c = java.util.Calendar.getInstance();
                int hour = c.get(java.util.Calendar.HOUR_OF_DAY);
                int minute = c.get(java.util.Calendar.MINUTE);
                new android.app.TimePickerDialog(this, (view, hourOfDay, minuteOfHour) -> {
                    timeButton.setText(String.format("Picked: %02d:%02d", hourOfDay, minuteOfHour));
                }, hour, minute, true).show();
            });
            demoContentLayout.addView(timeButton);

        } else if ("Date".equals(elementName)) {
            demoDescription.setText("Date input lets users pick a calendar date (uses DatePickerDialog).");
            Button dateButton = new Button(this);
            dateButton.setText("Pick a date");
            dateButton.setOnClickListener(v -> {
                java.util.Calendar c = java.util.Calendar.getInstance();
                int year = c.get(java.util.Calendar.YEAR);
                int month = c.get(java.util.Calendar.MONTH);
                int day = c.get(java.util.Calendar.DAY_OF_MONTH);
                new android.app.DatePickerDialog(this, (view, y, m, d) -> {
                    dateButton.setText(String.format("Picked: %02d-%02d-%04d", d, m+1, y));
                }, year, month, day).show();
            });
            demoContentLayout.addView(dateButton);

        } else if ("Number".equals(elementName)) {
            demoDescription.setText("Enter a standard numeric value.");
            android.widget.EditText numberInput = new android.widget.EditText(this);
            numberInput.setHint("Enter number");
            numberInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
            numberInput.setSingleLine(true);
            numberInput.setPadding(24,24,24,24);
            demoContentLayout.addView(numberInput);

        } else if ("Number (Signed)".equals(elementName)) {
            demoDescription.setText("Enter a signed (positive or negative) number.");
            android.widget.EditText numberSigned = new android.widget.EditText(this);
            numberSigned.setHint("Enter signed number");
            numberSigned.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_SIGNED);
            numberSigned.setSingleLine(true);
            numberSigned.setPadding(24,24,24,24);
            demoContentLayout.addView(numberSigned);

        } else if ("Number (Decimal)".equals(elementName)) {
            demoDescription.setText("Enter a decimal (floating point) number.");
            android.widget.EditText numberDecimal = new android.widget.EditText(this);
            numberDecimal.setHint("Enter decimal number");
            numberDecimal.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL);
            numberDecimal.setSingleLine(true);
            numberDecimal.setPadding(24,24,24,24);
            demoContentLayout.addView(numberDecimal);

        } else if ("AutoCompleteTextView".equals(elementName)) {
            demoDescription.setText("AutoCompleteTextView offers suggestions from a list as you type.");
            android.widget.AutoCompleteTextView autoComplete = new android.widget.AutoCompleteTextView(this);
            String[] items = {"Apple", "Banana", "Cherry", "Date", "Dragonfruit", "Grape"};
            android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
            autoComplete.setAdapter(adapter);
            autoComplete.setHint("Type a fruit name");
            autoComplete.setPadding(24,24,24,24);
            demoContentLayout.addView(autoComplete);

        } else if ("MultiAutoCompleteText ...".equals(elementName) || "MultiAutoCompleteTextView".equals(elementName)) {
            demoDescription.setText("MultiAutoCompleteTextView suggests and allows multiple entries, separated by commas.");
            android.widget.MultiAutoCompleteTextView multiAuto = new android.widget.MultiAutoCompleteTextView(this);
            String[] items = {"Red", "Green", "Blue", "Yellow", "Orange", "Purple"};
            android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
            multiAuto.setAdapter(adapter);
            multiAuto.setTokenizer(new android.widget.MultiAutoCompleteTextView.CommaTokenizer());
            multiAuto.setHint("Type colors, separated by commas");
            multiAuto.setPadding(24,24,24,24);
            demoContentLayout.addView(multiAuto);

        } else if ("CheckedTextView".equals(elementName)) {
            demoDescription.setText("A CheckedTextView acts as a text label with a checkbox. Tap to toggle.");
            android.widget.CheckedTextView checkedTextView = new android.widget.CheckedTextView(this);
            checkedTextView.setText("CheckedTextView label (tap me)");
            checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
            checkedTextView.setPadding(24,24,24,24);
            checkedTextView.setOnClickListener(v -> {
                boolean checked = !checkedTextView.isChecked();
                checkedTextView.setChecked(checked);
                checkedTextView.setCheckMarkDrawable(checked
                        ? android.R.drawable.checkbox_on_background
                        : android.R.drawable.checkbox_off_background);
            });
            demoContentLayout.addView(checkedTextView);

        } else if ("TextInputLayout".equals(elementName)) {
            demoDescription.setText("TextInputLayout is a Material Design wrapper for EditText with floating hint and error support.");
            // Requires dependency: implementation 'com.google.android.material:material:1.4.0' or later
            com.google.android.material.textfield.TextInputLayout textInputLayout = new com.google.android.material.textfield.TextInputLayout(this);
            textInputLayout.setHint("Name (with floating label)");
            android.widget.EditText editText = new android.widget.EditText(this);
            editText.setPadding(24,24,24,24);
            textInputLayout.addView(editText);
            demoContentLayout.addView(textInputLayout);
        } else if ("Button".equals(elementName)) {
            demoDescription.setText("A standard push-button widget.");

            Button btnDemo = new Button(this);
            btnDemo.setText("Click Me!");
            btnDemo.setOnClickListener(v -> Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(btnDemo);

        } else if ("ImageButton".equals(elementName)) {
            demoDescription.setText("A button with an image instead of text.");

            android.widget.ImageButton imgBtn = new android.widget.ImageButton(this);
            imgBtn.setImageResource(R.drawable.ic_img);  // Use an icon you have for buttons
            imgBtn.setBackgroundColor(Color.TRANSPARENT);
            imgBtn.setOnClickListener(v -> Toast.makeText(this, "ImageButton clicked!", Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(imgBtn);

        } else if ("ChipGroup".equals(elementName)) {
            demoDescription.setText("ChipGroup holds multiple Chip components.");

            com.google.android.material.chip.ChipGroup chipGroup = new com.google.android.material.chip.ChipGroup(this);
            chipGroup.setSingleSelection(false);
            chipGroup.setPadding(24, 24, 24, 24);

            String[] chipLabels = {"Chip One", "Chip Two", "Chip Three"};
            for (String label : chipLabels) {
                com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(this);
                chip.setText(label);
                chip.setClickable(true);
                chip.setCheckable(true);
                chipGroup.addView(chip);
            }

            demoContentLayout.addView(chipGroup);

        } else if ("Chip".equals(elementName)) {
            demoDescription.setText("A compact element that represents an input, attribute, or action.");

            com.google.android.material.chip.Chip chip = new com.google.android.material.chip.Chip(this);
            chip.setText("Example Chip");
            chip.setCheckable(true);
            demoContentLayout.addView(chip);

        } else if ("CheckBox".equals(elementName)) {
            demoDescription.setText("A checkbox allows the user to select or deselect a boolean option.");

            android.widget.CheckBox checkBox = new android.widget.CheckBox(this);
            checkBox.setText("Check me");
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->
                    Toast.makeText(this, "Checkbox is " + (isChecked ? "checked" : "unchecked"), Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(checkBox);

        } else if ("RadioGroup".equals(elementName)) {
            demoDescription.setText("RadioGroup contains multiple RadioButton elements allowing single selection.");

            android.widget.RadioGroup radioGroup = new android.widget.RadioGroup(this);
            radioGroup.setOrientation(android.widget.RadioGroup.VERTICAL);
            String[] options = {"Option 1", "Option 2", "Option 3"};
            for (int i = 0; i < options.length; i++) {
                android.widget.RadioButton radioButton = new android.widget.RadioButton(this);
                radioButton.setText(options[i]);
                radioButton.setId(View.generateViewId());
                radioGroup.addView(radioButton);
            }
            radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                android.widget.RadioButton rb = group.findViewById(checkedId);
                if(rb != null && rb.isChecked()) {
                    Toast.makeText(this, rb.getText() + " selected", Toast.LENGTH_SHORT).show();
                }
            });
            demoContentLayout.addView(radioGroup);

        } else if ("RadioButton".equals(elementName)) {
            demoDescription.setText("A single radio button allowing users to select one option.");

            android.widget.RadioButton radioButton = new android.widget.RadioButton(this);
            radioButton.setText("Single RadioButton");
            radioButton.setOnClickListener(v ->
                    Toast.makeText(this, "RadioButton clicked", Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(radioButton);

        } else if ("ToggleButton".equals(elementName)) {
            demoDescription.setText("ToggleButton switches between two states: checked and unchecked.");

            android.widget.ToggleButton toggleButton = new android.widget.ToggleButton(this);
            toggleButton.setTextOff("OFF");
            toggleButton.setTextOn("ON");
            toggleButton.setChecked(false);
            toggleButton.setOnCheckedChangeListener((buttonView, isChecked) ->
                    Toast.makeText(this, "Toggle is " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(toggleButton);

        } else if ("Switch".equals(elementName)) {
            demoDescription.setText("Switch is a two-state toggle switch widget (already implemented previously).");

            android.widget.Switch switchDemo = new android.widget.Switch(this);
            switchDemo.setText("Toggle me");
            switchDemo.setOnCheckedChangeListener((buttonView, isChecked) ->
                    Toast.makeText(this, "Switch is " + (isChecked ? "ON" : "OFF"), Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(switchDemo);

        } else if ("FloatingActionButton".equals(elementName)) {
            demoDescription.setText("FloatingActionButton represents a primary action in your app.");

            com.google.android.material.floatingactionbutton.FloatingActionButton fab = new com.google.android.material.floatingactionbutton.FloatingActionButton(this);
            fab.setImageResource(android.R.drawable.ic_dialog_email);  // Example icon
            fab.setOnClickListener(v -> Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT).show());

            // Set size and margins for FAB
            LinearLayout.LayoutParams fabParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            fabParams.setMargins(16, 16, 16, 16);
            fab.setLayoutParams(fabParams);

            demoContentLayout.addView(fab);

        }   else if ("ConstraintLayout".equals(elementName)) {
            demoDescription.setText("ConstraintLayout allows flexible and flat layout hierarchies by constraining views.");

            androidx.constraintlayout.widget.ConstraintLayout constraintLayoutDemo = new androidx.constraintlayout.widget.ConstraintLayout(this);
            constraintLayoutDemo.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    400)); // fixed height for demo visibility

            // Add two TextViews with constraints
            TextView text1 = new TextView(this);
            text1.setId(View.generateViewId());
            text1.setText("Top Left");
            text1.setTextSize(16);
            text1.setBackgroundColor(Color.parseColor("#FFCDD2")); // light red
            constraintLayoutDemo.addView(text1);

            TextView text2 = new TextView(this);
            text2.setId(View.generateViewId());
            text2.setText("Bottom Right");
            text2.setTextSize(16);
            text2.setBackgroundColor(Color.parseColor("#C8E6C9")); // light green
            constraintLayoutDemo.addView(text2);

            androidx.constraintlayout.widget.ConstraintSet set = new androidx.constraintlayout.widget.ConstraintSet();
            set.clone(constraintLayoutDemo);

            set.connect(text1.getId(), androidx.constraintlayout.widget.ConstraintSet.TOP, constraintLayoutDemo.getId(), androidx.constraintlayout.widget.ConstraintSet.TOP, 16);
            set.connect(text1.getId(), androidx.constraintlayout.widget.ConstraintSet.LEFT, constraintLayoutDemo.getId(), androidx.constraintlayout.widget.ConstraintSet.LEFT, 16);

            set.connect(text2.getId(), androidx.constraintlayout.widget.ConstraintSet.BOTTOM, constraintLayoutDemo.getId(), androidx.constraintlayout.widget.ConstraintSet.BOTTOM, 16);
            set.connect(text2.getId(), androidx.constraintlayout.widget.ConstraintSet.RIGHT, constraintLayoutDemo.getId(), androidx.constraintlayout.widget.ConstraintSet.RIGHT, 16);

            set.applyTo(constraintLayoutDemo);

            demoContentLayout.addView(constraintLayoutDemo);

        } else if ("LinearLayout (horizontal)".equals(elementName)) {
            demoDescription.setText("LinearLayout arranges its children horizontally when orientation is set to horizontal.");

            LinearLayout linearLayoutH = new LinearLayout(this);
            linearLayoutH.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutH.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 120));
            linearLayoutH.setBackgroundColor(Color.parseColor("#E1F5FE")); // light blue

            // Add few buttons horizontally arranged
            for (int i = 1; i <= 3; i++) {
                Button btn = new Button(this);
                btn.setText("Btn " + i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                params.setMargins(8, 8, 8, 8);
                btn.setLayoutParams(params);
                linearLayoutH.addView(btn);
            }
            demoContentLayout.addView(linearLayoutH);

        } else if ("LinearLayout (vertical)".equals(elementName)) {
            demoDescription.setText("LinearLayout arranges its children vertically when orientation is set to vertical.");

            LinearLayout linearLayoutV = new LinearLayout(this);
            linearLayoutV.setOrientation(LinearLayout.VERTICAL);
            linearLayoutV.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            linearLayoutV.setBackgroundColor(Color.parseColor("#FFF9C4")); // light yellow

            // Add three TextViews stacked vertically
            for (int i = 1; i <= 3; i++) {
                TextView tv = new TextView(this);
                tv.setText("Item " + i);
                tv.setTextSize(16);
                tv.setPadding(16, 16, 16, 16);
                tv.setBackgroundColor(Color.argb(30, 0, 0, 0)); // semi-transparent gray background
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 8, 8, 8);
                tv.setLayoutParams(params);
                linearLayoutV.addView(tv);
            }
            demoContentLayout.addView(linearLayoutV);

        } else if ("FrameLayout".equals(elementName)) {
            demoDescription.setText("FrameLayout displays child views stacked on top of each other.");

            android.widget.FrameLayout frameLayout = new android.widget.FrameLayout(this);
            frameLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 300));
            frameLayout.setBackgroundColor(Color.parseColor("#E6EE9C")); // light lime

            // Add overlapping TextViews
            TextView bottomText = new TextView(this);
            bottomText.setText("Bottom Text");
            bottomText.setTextSize(18);
            bottomText.setPadding(24, 24, 24, 24);
            bottomText.setBackgroundColor(Color.parseColor("#80FF5722")); // semi-transparent deep orange
            frameLayout.addView(bottomText);

            TextView topText = new TextView(this);
            topText.setText("Top Text");
            topText.setTextSize(18);
            topText.setPadding(24, 50, 24, 24);
            topText.setBackgroundColor(Color.parseColor("#807CB342")); // semi-transparent light green
            frameLayout.addView(topText);

            demoContentLayout.addView(frameLayout);

        } else if ("TableLayout".equals(elementName)) {
            demoDescription.setText("TableLayout arranges its children in rows and columns, like an HTML table.");

            android.widget.TableLayout tableLayout = new android.widget.TableLayout(this);
            tableLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tableLayout.setStretchAllColumns(true);

            // Create a few TableRows
            for (int i = 1; i <= 3; i++) {
                android.widget.TableRow row = new android.widget.TableRow(this);
                row.setLayoutParams(new android.widget.TableLayout.LayoutParams(
                        android.widget.TableLayout.LayoutParams.MATCH_PARENT, android.widget.TableLayout.LayoutParams.WRAP_CONTENT));

                for (int j = 1; j <= 3; j++) {
                    TextView cell = new TextView(this);
                    cell.setText("R" + i + "C" + j);
                    cell.setPadding(16, 16, 16, 16);
                    cell.setBackgroundColor(Color.parseColor("#B3E5FC"));
                    row.addView(cell);
                }
                tableLayout.addView(row);
            }
            demoContentLayout.addView(tableLayout);

        } else if ("TableRow".equals(elementName)) {
            demoDescription.setText("TableRow represents a single row inside a TableLayout.");

            android.widget.TableLayout tableLayout = new android.widget.TableLayout(this);
            tableLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tableLayout.setStretchAllColumns(true);

            android.widget.TableRow row = new android.widget.TableRow(this);
            row.setLayoutParams(new android.widget.TableLayout.LayoutParams(
                    android.widget.TableLayout.LayoutParams.MATCH_PARENT, android.widget.TableLayout.LayoutParams.WRAP_CONTENT));

            for (int j = 1; j <= 4; j++) {
                TextView cell = new TextView(this);
                cell.setText("Cell " + j);
                cell.setPadding(16, 16, 16, 16);
                cell.setBackgroundColor(Color.parseColor("#B3E5FC"));
                row.addView(cell);
            }
            tableLayout.addView(row); // add one row only

            demoContentLayout.addView(tableLayout);

        } else if ("Space".equals(elementName)) {
            demoDescription.setText("Space creates an empty spacer view that can be used to arrange components.");

            android.widget.Space space = new android.widget.Space(this);
            space.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 50));
            demoContentLayout.addView(space);
        }


        else {
            demoDescription.setText("Demo not available yet for this UI element.");
            TextView noDemoText = new TextView(this);
            noDemoText.setText("No demo implemented for " + elementName);
            noDemoText.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(noDemoText);
        }
    }
}
