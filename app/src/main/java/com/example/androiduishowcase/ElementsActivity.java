package com.example.androiduishowcase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ElementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_elements);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get category name from Intent
        String categoryName = getIntent().getStringExtra("categoryName");

        // Show the category name as title
        TextView categoryTitle = findViewById(R.id.categoryTitle);
        if (categoryTitle != null && categoryName != null) {
            categoryTitle.setText("Category: " + categoryName);
        }

        // Prepare list of elements depending on selected category
        List<UiElement> elementList = new ArrayList<>();
        if ("Common".equals(categoryName)) {
            elementList.add(new UiElement("TextView"));
            elementList.add(new UiElement("Button"));
            elementList.add(new UiElement("ImageView"));
            elementList.add(new UiElement("RecyclerView"));
            elementList.add(new UiElement("FragmentContainerView"));
            elementList.add(new UiElement("ScrollView"));
            elementList.add(new UiElement("Switch"));
        } else if ("Text".equals(categoryName)) {
            elementList.add(new UiElement("TextView"));
            elementList.add(new UiElement("Plain Text"));
            elementList.add(new UiElement("Password"));
            elementList.add(new UiElement("Password (Numeric)"));
            elementList.add(new UiElement("E-mail"));
            elementList.add(new UiElement("Phone"));
            elementList.add(new UiElement("Postal Address"));
            elementList.add(new UiElement("Multiline Text"));
            elementList.add(new UiElement("Time"));
            elementList.add(new UiElement("Date"));
            elementList.add(new UiElement("Number"));
            elementList.add(new UiElement("Number (Signed)"));
            elementList.add(new UiElement("Number (Decimal)"));
            elementList.add(new UiElement("AutoCompleteTextView"));
            elementList.add(new UiElement("MultiAutoCompleteText ..."));
            elementList.add(new UiElement("CheckedTextView"));
            elementList.add(new UiElement("TextInputLayout"));
        } else if ("Buttons".equals(categoryName)) {
            elementList.add(new UiElement("Button"));
            elementList.add(new UiElement("ImageButton"));
            elementList.add(new UiElement("ChipGroup"));
            elementList.add(new UiElement("Chip"));
            elementList.add(new UiElement("CheckBox"));
            elementList.add(new UiElement("RadioGroup"));
            elementList.add(new UiElement("RadioButton"));
            elementList.add(new UiElement("ToggleButton"));
            elementList.add(new UiElement("Switch"));
            elementList.add(new UiElement("FloatingActionButton"));
        } else if ("Widgets".equals(categoryName)) {
            elementList.add(new UiElement("View"));
            elementList.add(new UiElement("ImageView"));
            elementList.add(new UiElement("WebView"));
            elementList.add(new UiElement("VideoView"));
            elementList.add(new UiElement("CalendarView"));
            elementList.add(new UiElement("TextClock"));
            elementList.add(new UiElement("ProgressBar"));
            elementList.add(new UiElement("ProgressBar (Horizontal)"));
            elementList.add(new UiElement("SeekBar"));
            elementList.add(new UiElement("SeekBar (Discrete)"));
            elementList.add(new UiElement("RatingBar"));
            elementList.add(new UiElement("SearchView"));
            elementList.add(new UiElement("TextureView"));
            elementList.add(new UiElement("SurfaceView"));
            elementList.add(new UiElement("Horizontal Divider"));
            elementList.add(new UiElement("Vertical Divider"));
        } else if ("Layouts".equals(categoryName)) {
            elementList.add(new UiElement("ConstraintLayout"));
            elementList.add(new UiElement("LinearLayout (horizontal)"));
            elementList.add(new UiElement("LinearLayout (vertical)"));
            elementList.add(new UiElement("FrameLayout"));
            elementList.add(new UiElement("TableLayout"));
            elementList.add(new UiElement("TableRow"));
            elementList.add(new UiElement("Space"));
        } else if ("Containers".equals(categoryName)) {
            elementList.add(new UiElement("Spinner"));
            elementList.add(new UiElement("RecyclerView"));
            elementList.add(new UiElement("ScrollView"));
            elementList.add(new UiElement("HorizontalScrollView"));
            elementList.add(new UiElement("NestedScrollView"));
            elementList.add(new UiElement("ViewPager2"));
            elementList.add(new UiElement("CardView"));
            elementList.add(new UiElement("AppBarLayout"));
            elementList.add(new UiElement("BottomAppBar"));
            elementList.add(new UiElement("NavigationView"));
            elementList.add(new UiElement("BottomNavigationView"));
            elementList.add(new UiElement("Toolbar"));
            elementList.add(new UiElement("MaterialToolbar"));
            elementList.add(new UiElement("TabLayout"));
            elementList.add(new UiElement("TabItem"));
            elementList.add(new UiElement("ViewStub"));
            elementList.add(new UiElement("ViewAnimator"));
            elementList.add(new UiElement("ViewSwitcher"));
            elementList.add(new UiElement("<include>"));
            elementList.add(new UiElement("FragmentContainerView"));
            elementList.add(new UiElement("NavHostFragment"));
            elementList.add(new UiElement("<view>"));
            elementList.add(new UiElement("<requestFocus>"));
        } else if ("Helpers".equals(categoryName)) {
            elementList.add(new UiElement("Group"));
            elementList.add(new UiElement("Barrier (Horizontal)"));
            elementList.add(new UiElement("Barrier (Vertical)"));
            elementList.add(new UiElement("Flow"));
            elementList.add(new UiElement("Guideline (Horizontal)"));
            elementList.add(new UiElement("Guideline (Vertical)"));
            elementList.add(new UiElement("Layer"));
            elementList.add(new UiElement("ImageFilterView"));
            elementList.add(new UiElement("ImageFilterButton"));
            elementList.add(new UiElement("MockView"));
        } else if ("Google".equals(categoryName)) {
            elementList.add(new UiElement("AdView"));
            elementList.add(new UiElement("MapView"));
        } else if ("Legacy".equals(categoryName)) {
            elementList.add(new UiElement("GridLayout"));
            elementList.add(new UiElement("ListView"));
            elementList.add(new UiElement("TabHost"));
            elementList.add(new UiElement("RelativeLayout"));
            elementList.add(new UiElement("GridView"));
        } // You can add else block for empty or unknown category handling if needed

        RecyclerView elementsRecyclerView = findViewById(R.id.elementsRecyclerView);
        elementsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Updated ElementAdapter constructor to accept click listener
        ElementAdapter elementAdapter = new ElementAdapter(this, elementList, element -> {
            // On element item click, start DemoActivity passing element & category names
            Intent demoIntent = new Intent(ElementsActivity.this, DemoActivity.class);
            demoIntent.putExtra("elementName", element.getName());
            demoIntent.putExtra("categoryName", categoryName);
            startActivity(demoIntent);
        });

        elementsRecyclerView.setAdapter(elementAdapter);
    }
}
