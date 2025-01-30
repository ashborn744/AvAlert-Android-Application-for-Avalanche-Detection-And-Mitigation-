package com.example.avalert1;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.io.IOException;
import java.io.InputStream;

public class ImagePrediction extends AppCompatActivity implements RoboflowAPIHandler.OnAPIResponseListener {
    private CardView selectImage, predict;
    private boolean avalancheDetected;
    TextView predicted_txt;
    ImageView predicted_img;
    private Toolbar toolbar;
    private ImageButton back_btn;
    private ImageView displayImg;
    private static final int GALLERY_REQUEST_CODE = 1000;
    private Bitmap selectedImage; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_prediction);

        selectImage = findViewById(R.id.selectImg);
        predict = findViewById(R.id.predict);
        displayImg = findViewById(R.id.displayImage);
        ImageView predicted_img = findViewById(R.id.Predicted_img);
        TextView predicted_txt = findViewById(R.id.Predicted_txt);

        back_btn = findViewById(R.id.back_arrow);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title in the center
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Image Prediction");

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_GET_CONTENT);
                iGallery.setType("image/*");
                startActivityForResult(iGallery, GALLERY_REQUEST_CODE);
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImage != null) {
                    // Call the AsyncTask to perform API request
                    RoboflowAPIHandler apiHandler = new RoboflowAPIHandler(ImagePrediction.this);
                    apiHandler.execute(selectedImage);
                } else {
                    showToast("Please select an image first.");
                }
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE) {
                // Display the selected image
                displayImg.setImageURI(data.getData());
                ImageView predicted_img = findViewById(R.id.Predicted_img);
                TextView predicted_txt = findViewById(R.id.Predicted_txt);

                // Get the bitmap from the selected image URI
                selectedImage = getBitmapFromUri(data.getData());
                // Reset values
                predicted_txt.setText("Nothing Yet!");
                predicted_img.setImageResource(R.drawable.danger_placeholder);
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAPIResponse(Bitmap originalImage, Bitmap modifiedImage) {
        if (originalImage != null && modifiedImage != null) {
            displayImg.setImageBitmap(modifiedImage);

            // Handle the API response as needed
        } else {
            showToast("Failed to process the image. Please try again.");
        }
    }

    @Override
    public void onAvalancheDetectionResult(boolean isAvalancheDetected) {
        ImageView predicted_img = findViewById(R.id.Predicted_img);
        TextView predicted_txt = findViewById(R.id.Predicted_txt);




        // Handle the avalanche detection result
        if (isAvalancheDetected) {
            predicted_txt.setText("Avalanche Detected!");
            predicted_img.setImageResource(R.drawable.high);
            Log.d("AvalancheDetectionResult", "Avalanche Detected");

        } else {
            predicted_txt.setText("No Avalanche Detected!");
            predicted_img.setImageResource(R.drawable.low);
            Log.d("AvalancheDetectionResult", "No Avalanche Detected");

        }
    }

}
