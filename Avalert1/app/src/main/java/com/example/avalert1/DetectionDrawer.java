package com.example.avalert1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetectionDrawer {

    private static final int[] OUTLINE_COLORS = {Color.parseColor("#ff0000"), Color.parseColor("#a200ff"), Color.parseColor("#00f7ff")};
    private static final int[] FILL_COLORS = {Color.argb(30, 255, 0, 0), Color.argb(30, 162, 0, 255), Color.argb(30, 0, 242, 255)}; // 178 is 70% transparency

   // private static final int[] FILL_COLORS = {Color.parseColor("#88f7ea"), Color.parseColor("#e36264"), Color.parseColor("#6a95f7")};
    private static final int[] TEXT_BACKGROUND_COLORS = {Color.WHITE, Color.WHITE, Color.WHITE}; // Background color for text
    private static final int[] TEXT_COLORS = {Color.BLACK, Color.BLACK, Color.BLACK}; // Text color

    public static Bitmap drawDetectedRegions(Bitmap originalImage, String jsonResponse) {
        List<List<DetectedRegion>> detectedRegionsList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray predictionsArray = jsonObject.getJSONArray("predictions");

            for (int i = 0; i < predictionsArray.length(); i++) {
                JSONObject predictionObject = predictionsArray.getJSONObject(i);

                if (predictionObject.has("class") && predictionObject.has("points")) { // Null checks
                    String className = predictionObject.getString("class");
                    double confidence = predictionObject.getDouble("confidence");

                    JSONArray pointsArray = predictionObject.getJSONArray("points");
                    List<PointF> points = new ArrayList<>();
                    for (int j = 0; j < pointsArray.length(); j++) {
                        JSONObject pointObject = pointsArray.getJSONObject(j);
                        if (pointObject.has("x") && pointObject.has("y")) { // Nested object checks
                            float x = (float) pointObject.getDouble("x");
                            float y = (float) pointObject.getDouble("y");
                            points.add(new PointF(x, y));
                        } else {
                            Log.e("DetectionDrawer", "Missing point data in prediction " + i + ", point " + j);
                        }
                    }

                    DetectedRegion region = new DetectedRegion(className, confidence, points);

                    int index = getDetectedRegionListIndex(detectedRegionsList, className);
                    if (index != -1) {
                        detectedRegionsList.get(index).add(region);
                    } else {
                        List<DetectedRegion> newClassList = new ArrayList<>();
                        newClassList.add(region);
                        detectedRegionsList.add(newClassList);
                    }
                } else {
                    Log.e("DetectionDrawer", "Missing prediction data at index " + i);
                }
            }
        } catch (JSONException e) {
            Log.e("DetectionDrawer", "Error parsing JSON: ", e);
        }

        Bitmap modifiedImage = originalImage.copy(originalImage.getConfig(), true);

        Canvas canvas = new Canvas(modifiedImage);
        Paint outlinePaint = new Paint();
        Paint fillPaint = new Paint();
        Paint textPaint = new Paint();
        Paint textBackgroundPaint = new Paint();

        for (List<DetectedRegion> detectedRegions : detectedRegionsList) {
            DetectedRegion firstRegion = detectedRegions.get(0);
            int classIndex = getClassIndex(firstRegion.getClassName());

            outlinePaint.setColor(OUTLINE_COLORS[classIndex]);
            outlinePaint.setStyle(Paint.Style.STROKE);
            outlinePaint.setStrokeWidth(5);

            fillPaint.setColor(FILL_COLORS[classIndex]);
            fillPaint.setStyle(Paint.Style.FILL);

            textBackgroundPaint.setColor(TEXT_BACKGROUND_COLORS[classIndex]); // Background color
            textBackgroundPaint.setStyle(Paint.Style.FILL);

            textPaint.setColor(TEXT_COLORS[classIndex]);
            textPaint.setTextSize(24);

            for (DetectedRegion region : detectedRegions) {
                // Draw outline
                Path path = new Path();
                List<PointF> points = region.getPoints();
                if (points.size() > 0) {
                    path.moveTo(points.get(0).x, points.get(0).y);
                    for (int i = 1; i < points.size(); i++) {
                        path.lineTo(points.get(i).x, points.get(i).y);
                    }
                    path.close();
                    canvas.drawPath(path, fillPaint);
                    canvas.drawPath(path, outlinePaint);

                    // Draw text
                    String text = region.getClassName() + " - " + String.format(Locale.getDefault(), "%.2f", region.getConfidence());
                    PointF centerPoint = calculateCenterPoint(points);

                    float textWidth = textPaint.measureText(text);
                    float textHeight = textPaint.getTextSize();
                    float textBackgroundLeft = centerPoint.x - textWidth / 2;
                    float textBackgroundTop = centerPoint.y - textHeight / 2;
                    float textBackgroundRight = centerPoint.x + textWidth / 2;
                    float textBackgroundBottom = centerPoint.y + textHeight / 2;

                    // Draw background
                    canvas.drawRect(textBackgroundLeft - 5, textBackgroundTop - 5, textBackgroundRight + 5, textBackgroundBottom + 5, textBackgroundPaint);

                    // Draw text
                    canvas.drawText(text, textBackgroundLeft, textBackgroundBottom, textPaint);
                }
            }
        }

        return modifiedImage;
    }

    private static PointF calculateCenterPoint(List<PointF> points) {
        float sumX = 0;
        float sumY = 0;
        for (PointF point : points) {
            sumX += point.x;
            sumY += point.y;
        }
        return new PointF(sumX / points.size(), sumY / points.size());
    }

    private static int getDetectedRegionListIndex(List<List<DetectedRegion>> detectedRegionsList, String className) {
        for (int i = 0; i < detectedRegionsList.size(); i++) {
            List<DetectedRegion> classList = detectedRegionsList.get(i);
            if (!classList.isEmpty() && classList.get(0).getClassName().equals(className)) {
                return i;
            }
        }
        return -1;
    }

    private static int getClassIndex(String className) {
        // Add or modify class names and corresponding indices as needed
        switch (className) {
            case "glide":
                return 0;
            case "slab":
                return 1;
            case "loose":
                return 2;
            default:
                return 0; // Default to the first class color
        }
    }
}
