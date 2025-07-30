package com.example.androiduishowcase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.appbar.AppBarLayout;

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
        }   else if ("View".equals(elementName)) {
            demoDescription.setText("View is the base class for all UI components. Here is a simple colored view.");
            View simpleView = new View(this);
            simpleView.setBackgroundColor(Color.parseColor("#FFBB86FC"));  // purple
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 150);
            params.setMargins(24, 24, 24, 24);
            simpleView.setLayoutParams(params);
            demoContentLayout.addView(simpleView);

        } else if ("WebView".equals(elementName)) {
            demoDescription.setText("WebView displays web pages inside your app.");
            android.webkit.WebView webView = new android.webkit.WebView(this);
            int heightInDp = 300;
            float scale = getResources().getDisplayMetrics().density;
            int heightInPx = (int) (heightInDp * scale + 0.5f);
            LinearLayout.LayoutParams webViewParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    heightInPx);
            webView.setLayoutParams(webViewParams);
            webView.loadUrl("https://www.example.com");
            demoContentLayout.addView(webView);

        } else if ("VideoView".equals(elementName)) {
            demoDescription.setText("VideoView plays video files or streams.");
            android.widget.VideoView videoView = new android.widget.VideoView(this);
            LinearLayout.LayoutParams videoParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 300);
            videoParams.setMargins(24, 24, 24, 24);
            videoView.setLayoutParams(videoParams);
            // For demo: You'd need to set video URI or resource here
            demoContentLayout.addView(videoView);

        } else if ("CalendarView".equals(elementName)) {
            demoDescription.setText("CalendarView shows a calendar widget for selecting dates.");
            android.widget.CalendarView calendarView = new android.widget.CalendarView(this);
            demoContentLayout.addView(calendarView);

        } else if ("TextClock".equals(elementName)) {
            demoDescription.setText("TextClock displays the current time formatted.");
            android.widget.TextClock textClock = new android.widget.TextClock(this);
            textClock.setTextSize(24);
            demoContentLayout.addView(textClock);

        } else if ("ProgressBar".equals(elementName)) {
            demoDescription.setText("ProgressBar shows progress of an operation.");
            android.widget.ProgressBar progressBar = new android.widget.ProgressBar(this);
            demoContentLayout.addView(progressBar);

        } else if ("ProgressBar (Horizontal)".equals(elementName)) {
            demoDescription.setText("Horizontal ProgressBar shows progress as a horizontal bar.");
            android.widget.ProgressBar horizontalProgressBar = new android.widget.ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 48);
            params.setMargins(24, 24, 24, 24);
            horizontalProgressBar.setLayoutParams(params);
            horizontalProgressBar.setProgress(50);  // Example 50% progress
            demoContentLayout.addView(horizontalProgressBar);

        } else if ("SeekBar".equals(elementName)) {
            demoDescription.setText("SeekBar allows users to select a value by sliding a thumb.");
            android.widget.SeekBar seekBar = new android.widget.SeekBar(this);
            demoContentLayout.addView(seekBar);

        } else if ("SeekBar (Discrete)".equals(elementName)) {
            demoDescription.setText("Discrete SeekBar with fixed step increments.");
            android.widget.SeekBar seekBarDiscrete = new android.widget.SeekBar(this);
            seekBarDiscrete.setMax(10);
            seekBarDiscrete.setKeyProgressIncrement(1);
            demoContentLayout.addView(seekBarDiscrete);

        } else if ("RatingBar".equals(elementName)) {
            demoDescription.setText("RatingBar lets users give a star rating.");
            android.widget.RatingBar ratingBar = new android.widget.RatingBar(this);
            demoContentLayout.addView(ratingBar);

        } else if ("SearchView".equals(elementName)) {
            demoDescription.setText("SearchView provides a UI for search queries.");
            android.widget.SearchView searchView = new android.widget.SearchView(this);
            searchView.setQueryHint("Search here...");
            demoContentLayout.addView(searchView);

        } else if ("TextureView".equals(elementName)) {
            demoDescription.setText("TextureView displays content streams like videos or camera preview.");
            // Placeholder: Usually requires complex setup, show a placeholder
            TextView placeholder = new TextView(this);
            placeholder.setText("TextureView demo placeholder");
            placeholder.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(placeholder);

        } else if ("SurfaceView".equals(elementName)) {
            demoDescription.setText("SurfaceView is used for high-performance drawing.");
            // Placeholder: Typically requires native drawing, show a placeholder
            TextView placeholder = new TextView(this);
            placeholder.setText("SurfaceView demo placeholder");
            placeholder.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(placeholder);

        } else if ("Horizontal Divider".equals(elementName)) {
            demoDescription.setText("A horizontal divider is a thin line separating views.");
            View divider = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 2);
            params.setMargins(24, 24, 24, 24);
            divider.setLayoutParams(params);
            divider.setBackgroundColor(Color.LTGRAY);
            demoContentLayout.addView(divider);

        } else if ("Vertical Divider".equals(elementName)) {
            demoDescription.setText("A vertical divider is a thin line used to separate items horizontally.");
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 200)); // fixed height

            View leftView = new View(this);
            leftView.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            leftView.setBackgroundColor(Color.parseColor("#FFCDD2"));

            View divider = new View(this);
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(2, LinearLayout.LayoutParams.MATCH_PARENT);
            dividerParams.setMargins(12, 0, 12, 0);
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(Color.LTGRAY);

            View rightView = new View(this);
            rightView.setLayoutParams(new LinearLayout.LayoutParams(
                    0, LinearLayout.LayoutParams.MATCH_PARENT, 1f));
            rightView.setBackgroundColor(Color.parseColor("#BBDEFB"));

            horizontalLayout.addView(leftView);
            horizontalLayout.addView(divider);
            horizontalLayout.addView(rightView);

            demoContentLayout.addView(horizontalLayout);
        }   else if ("Spinner".equals(elementName)) {
            demoDescription.setText("Spinner provides a dropdown menu to select an option.");

            android.widget.Spinner spinner = new android.widget.Spinner(this);
            String[] options = {"Option 1", "Option 2", "Option 3"};
            android.widget.ArrayAdapter<String> adapter = new android.widget.ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, options);
            spinner.setAdapter(adapter);
            demoContentLayout.addView(spinner);

        } else if ("HorizontalScrollView".equals(elementName)) {
            demoDescription.setText("HorizontalScrollView allows scrolling content horizontally.");

            android.widget.HorizontalScrollView hsv = new android.widget.HorizontalScrollView(this);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(24, 24, 24, 24);

            for (int i = 1; i <= 10; i++) {
                Button button = new Button(this);
                button.setText("Btn " + i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(8, 0, 8, 0);
                button.setLayoutParams(params);
                linearLayout.addView(button);
            }
            hsv.addView(linearLayout);
            demoContentLayout.addView(hsv);

        } else if ("NestedScrollView".equals(elementName)) {
            demoDescription.setText("NestedScrollView is a ScrollView that supports nested scrolling.");

            androidx.core.widget.NestedScrollView nestedScrollView = new androidx.core.widget.NestedScrollView(this);
            nestedScrollView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 300));
            TextView textView = new TextView(this);
            textView.setPadding(24, 24, 24, 24);
            StringBuilder longText = new StringBuilder();
            for (int i = 1; i <= 30; i++) {
                longText.append("Line ").append(i).append("\n");
            }
            textView.setText(longText.toString());
            nestedScrollView.addView(textView);
            demoContentLayout.addView(nestedScrollView);

        } else if ("ViewPager2".equals(elementName)) {
            demoDescription.setText("ViewPager2 allows horizontal paging through pages of content.");

            androidx.viewpager2.widget.ViewPager2 viewPager2 = new androidx.viewpager2.widget.ViewPager2(this);
            viewPager2.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 300));

            java.util.List<String> pages = java.util.Arrays.asList("Page 1", "Page 2", "Page 3");
            androidx.recyclerview.widget.RecyclerView.Adapter adapter = new androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
                @Override
                public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
                    TextView tv = new TextView(parent.getContext());
                    tv.setTextSize(24);
                    tv.setGravity(Gravity.CENTER);
                    tv.setLayoutParams(new android.widget.LinearLayout.LayoutParams(
                            android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                            android.widget.LinearLayout.LayoutParams.MATCH_PARENT));
                    return new androidx.recyclerview.widget.RecyclerView.ViewHolder(tv) {};
                }

                @Override
                public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
                    ((TextView) holder.itemView).setText(pages.get(position));
                }

                @Override
                public int getItemCount() {
                    return pages.size();
                }
            };

            viewPager2.setAdapter(adapter);
            demoContentLayout.addView(viewPager2);

        } else if ("CardView".equals(elementName)) {
            demoDescription.setText("CardView provides a card-like container with rounded corners and shadow.");

            com.google.android.material.card.MaterialCardView cardView = new com.google.android.material.card.MaterialCardView(this);
            cardView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 200));
            cardView.setRadius(16f);
            cardView.setCardElevation(8f);
            cardView.setContentPadding(24, 24, 24, 24);

            TextView cardText = new TextView(this);
            cardText.setText("This is a CardView");
            cardText.setTextSize(18);
            cardView.addView(cardText);

            demoContentLayout.addView(cardView);

        } else if ("AppBarLayout".equals(elementName)) {
            demoDescription.setText("AppBarLayout is a vertical layout for app bars.");

            com.google.android.material.appbar.AppBarLayout appBarLayout = new com.google.android.material.appbar.AppBarLayout(this);
            appBarLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 200));
            appBarLayout.setBackgroundColor(Color.parseColor("#6200EE"));

            TextView appBarText = new TextView(this);
            appBarText.setText("AppBarLayout (purple bar)");
            appBarText.setTextColor(Color.WHITE);
            appBarText.setTextSize(20);
            appBarText.setGravity(Gravity.CENTER);
            appBarText.setLayoutParams(new AppBarLayout.LayoutParams(
                    AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.MATCH_PARENT));

            appBarLayout.addView(appBarText);
            demoContentLayout.addView(appBarLayout);

        } else if ("BottomAppBar".equals(elementName)) {
            demoDescription.setText("BottomAppBar provides a bar at the bottom of the screen.");

            com.google.android.material.bottomappbar.BottomAppBar bottomAppBar = new com.google.android.material.bottomappbar.BottomAppBar(this);
            bottomAppBar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 100));

            demoContentLayout.addView(bottomAppBar);

        } else if ("NavigationView".equals(elementName)) {
            demoDescription.setText("NavigationView provides a standard navigation drawer menu.");

            com.google.android.material.navigation.NavigationView navigationView = new com.google.android.material.navigation.NavigationView(this);
            navigationView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 400));

            // For demo, add menu programmatically or mock here (usually use XML menus)
            // Here just add a placeholder TextView
            TextView navText = new TextView(this);
            navText.setText("NavigationView Demo Placeholder");
            navText.setGravity(Gravity.CENTER);
            navText.setTextSize(18);
            navigationView.addView(navText);

            demoContentLayout.addView(navigationView);

        } else if ("BottomNavigationView".equals(elementName)) {
            demoDescription.setText("BottomNavigationView provides bottom navigation bar.");

            com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = new com.google.android.material.bottomnavigation.BottomNavigationView(this);
            bottomNavigationView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            // Setup menu programmatically or ignore for demo
            // For demo, add a TextView child
            TextView bottomNavText = new TextView(this);
            bottomNavText.setText("BottomNavigationView Demo");
            bottomNavText.setGravity(Gravity.CENTER);
            bottomNavText.setTextSize(18);
            bottomNavigationView.addView(bottomNavText);

            demoContentLayout.addView(bottomNavigationView);

        } else if ("Toolbar".equals(elementName) || "MaterialToolbar".equals(elementName)) {
            demoDescription.setText("Toolbar is a customizable action bar.");

            androidx.appcompat.widget.Toolbar toolbar = new androidx.appcompat.widget.Toolbar(this);
            toolbar.setTitle("Demo Toolbar");
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setBackgroundColor(Color.parseColor("#6200EE"));
            toolbar.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            demoContentLayout.addView(toolbar);

        } else if ("TabLayout".equals(elementName)) {
            demoDescription.setText("TabLayout shows horizontal tabs for switching views.");

            com.google.android.material.tabs.TabLayout tabLayout = new com.google.android.material.tabs.TabLayout(this);
            tabLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
            tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
            tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));

            demoContentLayout.addView(tabLayout);

        } else if ("TabItem".equals(elementName)) {
            demoDescription.setText("TabItem defines a tab inside TabLayout.");

            TextView info = new TextView(this);
            info.setText("TabItem is typically used in XML with TabLayout and does not have runtime UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("ViewStub".equals(elementName)) {
            demoDescription.setText("ViewStub is a lightweight invisible view that can be inflated later.");

            TextView info = new TextView(this);
            info.setText("ViewStub demo placeholder - works by inflating it programmatically.");
            info.setPadding(24, 24, 24, 24);

            demoContentLayout.addView(info);

        } else if ("ViewAnimator".equals(elementName)) {
            demoDescription.setText("ViewAnimator switches between child views with animations.");

            android.widget.ViewAnimator viewAnimator = new android.widget.ViewAnimator(this);
            viewAnimator.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 200));

            TextView firstView = new TextView(this);
            firstView.setText("View 1");
            firstView.setGravity(Gravity.CENTER);
            firstView.setTextSize(20);
            firstView.setBackgroundColor(Color.parseColor("#AED581"));

            TextView secondView = new TextView(this);
            secondView.setText("View 2");
            secondView.setGravity(Gravity.CENTER);
            secondView.setTextSize(20);
            secondView.setBackgroundColor(Color.parseColor("#81D4FA"));

            viewAnimator.addView(firstView);
            viewAnimator.addView(secondView);

            demoContentLayout.addView(viewAnimator);

        } else if ("ViewSwitcher".equals(elementName)) {
            demoDescription.setText("ViewSwitcher is a specialized ViewAnimator with two child views.");

            android.widget.ViewSwitcher viewSwitcher = new android.widget.ViewSwitcher(this);
            viewSwitcher.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 200));

            Button btn1 = new Button(this);
            btn1.setText("View 1");
            btn1.setOnClickListener(v -> viewSwitcher.showNext());

            Button btn2 = new Button(this);
            btn2.setText("View 2");
            btn2.setOnClickListener(v -> viewSwitcher.showPrevious());

            viewSwitcher.addView(btn1);
            viewSwitcher.addView(btn2);

            demoContentLayout.addView(viewSwitcher);

        } else if ("<include>".equals(elementName) || "<view>".equals(elementName)
                || "<requestFocus>".equals(elementName)) {
            demoDescription.setText(elementName + " is an XML tag generally used for layout inclusion or focus control, no direct UI demo.");
            TextView info = new TextView(this);
            info.setText(elementName + " does not have an explicit UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("NavHostFragment".equals(elementName)) {
            demoDescription.setText("NavHostFragment hosts navigation graphs for navigation components.");
            TextView info = new TextView(this);
            info.setText("NavHostFragment demo placeholder - used in navigation architecture.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);
        }   else if ("Group".equals(elementName)) {
            demoDescription.setText("Group is a helper object to apply visibility and transformations on multiple views.");
            TextView info = new TextView(this);
            info.setText("Group is non-visual; no direct UI representation.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Barrier (Horizontal)".equals(elementName)) {
            demoDescription.setText("Horizontal Barrier places constraints dynamically based on referenced views horizontally.");
            TextView info = new TextView(this);
            info.setText("Barrier is a helper for ConstraintLayout; not visible on UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Barrier (Vertical)".equals(elementName)) {
            demoDescription.setText("Vertical Barrier places constraints dynamically based on referenced views vertically.");
            TextView info = new TextView(this);
            info.setText("Barrier is a helper for ConstraintLayout; not visible on UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Flow".equals(elementName)) {
            demoDescription.setText("Flow helper positions views in a flow, wrapping content like a flexbox.");
            TextView info = new TextView(this);
            info.setText("Flow helper in ConstraintLayout is not a visual view.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Guideline (Horizontal)".equals(elementName)) {
            demoDescription.setText("Horizontal Guideline is an invisible line to help align views in ConstraintLayout.");
            TextView info = new TextView(this);
            info.setText("Guidelines are invisible helpers, no direct UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Guideline (Vertical)".equals(elementName)) {
            demoDescription.setText("Vertical Guideline is an invisible line for alignment in ConstraintLayout.");
            TextView info = new TextView(this);
            info.setText("Guidelines are invisible helpers, no UI elements.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("Layer".equals(elementName)) {
            demoDescription.setText("Layer groups multiple views and applies transformations as a single entity.");
            TextView info = new TextView(this);
            info.setText("Layer is a helper, invisible in UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);

        } else if ("ImageFilterView".equals(elementName)) {
            demoDescription.setText("ImageFilterView is a helper for advanced image filters. Demo uses ImageView + color filter.");
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_img); // your image resource
            imageView.setColorFilter(android.graphics.Color.argb(128, 255, 0, 0)); // red filter
            imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
            demoContentLayout.addView(imageView);
        }
        else if ("ImageFilterButton".equals(elementName)) {
            demoDescription.setText("ImageFilterButton is an advanced helper. Demo uses ImageButton + color filter.");
            ImageButton imageButton = new ImageButton(this);
            imageButton.setImageResource(R.drawable.ic_buttons); // your image resource
            imageButton.setColorFilter(android.graphics.Color.argb(128, 0, 0, 255)); // blue filter
            imageButton.setBackgroundColor(android.graphics.Color.TRANSPARENT);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            imageButton.setOnClickListener(v -> Toast.makeText(this, "ImageButton clicked", Toast.LENGTH_SHORT).show());
            demoContentLayout.addView(imageButton);
        }
        else if ("MockView".equals(elementName)) {
            demoDescription.setText("MockView is used for design-time placeholders and holds no runtime UI.");
            TextView info = new TextView(this);
            info.setText("MockView is only visible in the layout editor, no runtime UI.");
            info.setPadding(24, 24, 24, 24);
            demoContentLayout.addView(info);
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
