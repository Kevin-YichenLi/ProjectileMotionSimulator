package com.pms.project.controllers;

import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import javafx.stage.Stage;

import java.util.Locale;

public class GeneralSettingController {
    private GeneralSetting generalSetting;
    private Stage primaryStage;
    private Util util;

    public GeneralSettingController(GeneralSetting generalSetting, Stage primaryStage) {
        this.generalSetting = generalSetting;
        this.primaryStage = primaryStage;
        util = new Util(primaryStage);
    }

    public void onVolumeChanged(double newValue) {
        GeneralSetting.setVolume(newValue);
    }

    public void onLanguageChanged(String language) {
        if (language.equalsIgnoreCase("French") || language.equalsIgnoreCase("Français")) {
            GeneralSetting.setLocale(Locale.FRENCH);
        } else {
            GeneralSetting.setLocale(Locale.ENGLISH);
        }
    }
}
