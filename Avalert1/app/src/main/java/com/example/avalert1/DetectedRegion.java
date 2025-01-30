package com.example.avalert1;

// DetectedRegion.java
import android.graphics.PointF;

import java.util.List;

public class DetectedRegion {
    private String className;
    private double confidence;
    private List<PointF> points;

    public DetectedRegion(String className, double confidence, List<PointF> points) {
        this.className = className;
        this.confidence = confidence;
        this.points = points;
    }

    public String getClassName() {
        return className;
    }

    public double getConfidence() {
        return confidence;
    }

    public List<PointF> getPoints() {
        return points;
    }
}
