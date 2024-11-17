package com.pms.project.models;

import java.util.prefs.Preferences;

public class GeneralSetting {
    private static Preferences prefs = Preferences.userNodeForPackage(GeneralSetting.class);

    public static String get(String key, String defaultValue) {
        return prefs.get(key, defaultValue);
    }

    public static void set(String key, String value) {
        prefs.put(key, value);
    }
}
