package com.pms.project.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class GeneralSetting {
    private static DoubleProperty volume = new SimpleDoubleProperty(50);
    private String language;

    public static DoubleProperty volumeProperty() {
        return volume;
    }

    public static double getVolume() {
        return volume.doubleValue();
    }

    public static void setVolume(double newValue) {
        volume.set(newValue);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
