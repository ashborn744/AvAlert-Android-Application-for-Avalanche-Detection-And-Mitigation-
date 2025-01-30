package com.example.avalert1;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.avalert1.DetectionDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RoboflowAPIHandler extends AsyncTask<Bitmap, Void, Boolean> {
    private static final String ROBOFLOW_API_KEY = "SBIbVPTRqT5saAFpMx7u"; // Replace with your Roboflow API Key
    private static final String MODEL_ENDPOINT = "avalert/1"; // Replace with your model endpoint

    private OnAPIResponseListener listener;
    private Bitmap[] bitmaps; // Store bitmaps locally

    public interface OnAPIResponseListener {
        void onAPIResponse(Bitmap originalImage, Bitmap modifiedImage);

        void onAvalancheDetectionResult(boolean isAvalancheDetected);
    }

    public RoboflowAPIHandler(OnAPIResponseListener listener) {
        this.listener = listener;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected Boolean doInBackground(Bitmap... bitmaps) {
        this.bitmaps = bitmaps; // Store bitmaps locally

        Bitmap imageBitmap = bitmaps[0];

        // Convert bitmap to Base64-encoded string
        String encodedFile = bitmapToBase64(imageBitmap);

        String imageName = "image_" + System.currentTimeMillis() + ".jpg";
        String uploadURL = "https://outline.roboflow.com/" + MODEL_ENDPOINT + "?api_key=" + ROBOFLOW_API_KEY
                + "&name=" + imageName;

        // Http Request
        HttpURLConnection connection = null;
        try {
            // Configure connection to URL
            URL url = new URL(uploadURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", Integer.toString(encodedFile.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            // Send request
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(encodedFile);
            }

            // Get Response
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the predictions from the response and check if avalanche is detected
            boolean isAvalancheDetected = parsePredictions(response.toString());

            // Draw detected regions on the image
            Bitmap modifiedImage = DetectionDrawer.drawDetectedRegions(imageBitmap, response.toString());

            // Notify the listener with the API response and modified image
            if (listener != null) {
                listener.onAPIResponse(bitmaps[0], modifiedImage);
                listener.onAvalancheDetectionResult(isAvalancheDetected);
            }

            return isAvalancheDetected;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false if there is an error
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private boolean parsePredictions(String response) {
        try {
            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response);

            // Check if the key name for predictions matches the actual API response
            // Update "predictions" to the correct key name used by the Roboflow API
            JSONArray predictions = jsonResponse.getJSONArray("predictions");

            // Check if the predictions array is empty
            // If it's empty, there are zero predictions, and avalanche is not detected
            return predictions.length() > 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false; // Default to false if parsing fails or no avalanche is detected
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
