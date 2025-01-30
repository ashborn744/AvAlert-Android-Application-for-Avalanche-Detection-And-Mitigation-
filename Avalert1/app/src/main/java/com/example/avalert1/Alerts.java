package com.example.avalert1;
import static com.example.avalert1.AvalancheCity.getDangerLevelByCityName;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.Button;
import android.widget.TextView;


import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class Alerts extends AppCompatActivity {

    Toolbar toolbar;
    private EditText searchBar;
    private ImageButton searchIcon,back_btn;
    private ImageView dangerImageView;
    private TextView alertCityTextView;
    private TextView alertDescTextView;
    private TextView alertTitle;
    private ImageButton closeBtn;


    // Static strings for alert messages
    private static final String HIGH_ALERT_MESSAGE =  "When snowflakes start doing cartwheels, it's time to reconsider that mountain picnic. High avalanche risk – even the snow angels are scared!" ;
    private static final String MODERATE_ALERT_MESSAGE = "The mountains are having a mood swing – a moderate avalanche risk. It's like nature's way of saying, 'I might, or I might not. Who knows?'";
    private static final String LOW_ALERT_MESSAGE = "Snow behaving? It's on its best behavior! Low avalanche risk – the snowflakes are just practicing their synchronized falling routine.";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title in the center
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Alerts");

        // Set a custom back arrow click listener
//        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Initialize views
        searchBar = findViewById(R.id.searchBar);
        searchIcon = findViewById(R.id.searchIcon);
        closeBtn = findViewById(R.id.closeBtn);
        dangerImageView = findViewById(R.id.Alert_img);
        alertCityTextView = findViewById(R.id.Alert_city);
        alertDescTextView = findViewById(R.id.Alert_desc);
        alertTitle = findViewById(R.id.Alert_Title);


        back_btn = findViewById(R.id.back_arrow);


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

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
            // Fetch danger level by city name
            String dangerLevel = getDangerLevelByCityName(cityName);

            alertCityTextView.setText(cityName);

            // Update UI based on the danger level
            updateUI(dangerLevel);


        } else {
            // Show a toast or perform any other action for an empty search
            Toast.makeText(this, "Please enter a city name", Toast.LENGTH_SHORT).show();
        }



    }

    private void updateUI(String dangerLevel) {

        // Display danger level image
        int dangerImageResource = getDangerImageResource(dangerLevel);
        dangerImageView.setImageResource(dangerImageResource);

        if (dangerLevel != null ){String alertTitle_str = dangerLevel + " Avalanche Risk";
        alertTitle.setText(alertTitle_str);
        }
        else {
            alertTitle.setText("No Data Available");
            Toast.makeText(this, "Please Enter A Valid City Name", Toast.LENGTH_SHORT).show();


        }


        // Display corresponding alert message
        String alertMessage = getAlertMessage(dangerLevel);
        alertDescTextView.setText(alertMessage);

    }

    private int getDangerImageResource(String dangerLevel) {
        if ("High".equalsIgnoreCase(dangerLevel)) {
            return R.drawable.high;
        } else if ("Moderate".equalsIgnoreCase(dangerLevel)) {
            return R.drawable.moderate;
        } else if ("Low".equalsIgnoreCase(dangerLevel)){
            return R.drawable.low;
        }
        else {
            return R.drawable.danger_placeholder;
        }
    }

    private String getAlertMessage(String dangerLevel) {
        if ("High".equalsIgnoreCase(dangerLevel)) {
            return HIGH_ALERT_MESSAGE;
        } else if ("Moderate".equalsIgnoreCase(dangerLevel)) {
            return MODERATE_ALERT_MESSAGE;
        } else if ("Low".equalsIgnoreCase(dangerLevel)) {
            return LOW_ALERT_MESSAGE;
        } else {
            return "No data Available";
        }
    }
}