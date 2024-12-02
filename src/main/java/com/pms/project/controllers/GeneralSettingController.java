package com.pms.project.controllers;

import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import javafx.stage.Stage;

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
}
