package com.example.avalert1;

import static com.example.avalert1.AvalancheCity.getDangerLevelByCityName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CardView ImagePrediction,Statistics,Alerts;
    private EditText searchBar;
    private ImageButton searchIcon;
    private ImageButton closeBtn;
    private Toolbar toolbar;

    String toSendCityName;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_CITY_NAME = "cityName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImagePrediction=findViewById(R.id.btn_ImagePrediction);
        Statistics=findViewById(R.id.btn_Statistics);
        Alerts=findViewById(R.id.btn_alerts);
        // Initialize views
        searchBar = findViewById(R.id.searchBar);
        searchIcon = findViewById(R.id.searchIcon);
        closeBtn = findViewById(R.id.closeBtn);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        toSendCityName = prefs.getString(KEY_CITY_NAME, "");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title in the center
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Avalerts");


        // Set click listener for the search icon
        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearch();
            }
        });

        // Alternatively, you can also set an action listener for the search bar itself
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handleSearch();
                    return true;
                }
                return false;
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearch();
            }
        });

        // Add a text change listener to show/hide the close button based on the input
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Show/hide close button based on whether there is text in the search bar
                closeBtn.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed after text changes
            }
        });

        ImagePrediction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.avalert1.ImagePrediction.class);
                startActivity(intent);
            }
        });
        Statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If toSendCityName is not null or empty, proceed to Statistics activity
                if (toSendCityName != null && !toSendCityName.isEmpty()) {
                    Log.d("MainActivity", "Sending CityName to Statistics: " + toSendCityName);
                    Intent intent = new Intent(MainActivity.this, com.example.avalert1.Statistics.class);
                    intent.putExtra("CityName", toSendCityName);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Alerts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, com.example.avalert1.Alerts.class);
                startActivity(intent);
            }
        });
    }
    private void clearSearch() {
        // Clear the text in the search bar
        searchBar.getText().clear();

        // Hide the close button
        closeBtn.setVisibility(View.INVISIBLE);
    }
    private void handleSearch() {
        // Get the text from the search bar
        String cityName = searchBar.getText().toString().trim();

        // Check if the search is not empty
        if (!cityName.isEmpty()) {
            // Save the city name to SharedPreferences
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_CITY_NAME, cityName);
            editor.apply();

            // Update the variable
            toSendCityName = cityName;

        } else {
            // Show a toast or perform any other action for an empty search
            Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
        }
    }
}