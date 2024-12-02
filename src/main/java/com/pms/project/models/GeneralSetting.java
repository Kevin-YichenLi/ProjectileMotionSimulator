package com.pms.project.models;

import com.pms.project.Language;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class GeneralSetting {
    private static DoubleProperty volume = new SimpleDoubleProperty(50);
    private static Locale currentLocale = Locale.ENGLISH;
    private static SimpleObjectProperty<Locale> currentLocaleProperty = new SimpleObjectProperty<>();
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.messages", currentLocale);

    public static DoubleProperty volumeProperty() {
        return volume;
    }

    public static double getVolume() {
        return volume.doubleValue();
    }

    public static void setVolume(double newValue) {
        volume.set(newValue);
    }

    public static void setLocale(Locale locale) {
        currentLocale = locale;
        resourceBundle = ResourceBundle.getBundle("i18n.messages", currentLocale);
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static String getString(String key) {
        return resourceBundle.getString(key);
    }
}
