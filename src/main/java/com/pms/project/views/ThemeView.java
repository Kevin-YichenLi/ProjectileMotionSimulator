package com.pms.project.views;

import com.pms.project.controllers.ThemeController;
import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ThemeView extends GridPane {
   
    private Stage primaryStage;
    private Util util;
    private ThemeController themeController;


    // Constructor that accepts a Stage and Util
    public ThemeView(Stage primaryStage, Util util, ThemeController themeController) {
        this.primaryStage = primaryStage;
        this.util = util;
        this.themeController = themeController;
        // Set vertical and horizontal gaps for the grid
        this.setVgap(10);
        this.setHgap(10);

        // Title labels
        Label backgroundLabel = new Label(GeneralSetting.getString("menuItem.themeSetting.label.background"));
        Label fontLabel = new Label(GeneralSetting.getString("menuItem.themeSetting.label.font"));

        // Add labels to the GridPane
        this.add(backgroundLabel, 0, 0);
        this.add(fontLabel, 0, 4);

        // Create checkboxes for Background
        CheckBox lightBlue = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.lightBlue"));
        CheckBox darkBlue = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.darkBlue"));
        CheckBox sunset = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.sunset"));

        // Add Background checkboxes to the GridPane
        this.add(lightBlue, 1, 1);
        this.add(darkBlue, 1, 2);
        this.add(sunset, 1, 3);

        // Create checkboxes for Font
        CheckBox times = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.times"));
        CheckBox calibri = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.calibri"));
        CheckBox comicSans = new CheckBox(GeneralSetting.getString("menuItem.themeSetting.label.comicSans"));

        // Add Font checkboxes to the GridPane
        this.add(times, 1, 5);
        this.add(calibri, 1, 6);
        this.add(comicSans, 1, 7);

        // Logic for allowing only one checkbox selection for Background
        lightBlue.setOnAction(event -> {
            if (lightBlue.isSelected()) {
                darkBlue.setSelected(false);
                sunset.setSelected(false);
                // Preview light blue background
                themeController.previewBackground("background-light-blue");
            }
        });

        darkBlue.setOnAction(event -> {
            if (darkBlue.isSelected()) {
                lightBlue.setSelected(false);
                sunset.setSelected(false);
                // Preview dark blue background
                themeController.previewBackground("background-dark-blue");
            }
        });

        sunset.setOnAction(event -> {
            if (sunset.isSelected()) {
                lightBlue.setSelected(false);
                darkBlue.setSelected(false);
                // Preview sunset background
                themeController.previewBackground("background-sunset");
            }
        });

        // Logic for allowing only one checkbox selection for Font
        times.setOnAction(event -> {
            if (times.isSelected()) {
                calibri.setSelected(false);
                comicSans.setSelected(false);
                // Set the font preview but do not apply yet
                themeController.previewFont("times-font");
            }
        });

        calibri.setOnAction(event -> {
            if (calibri.isSelected()) {
                times.setSelected(false);
                comicSans.setSelected(false);
                // Set the font preview but do not apply yet
                themeController.previewFont("calibri-font");
            }
        });

        comicSans.setOnAction(event -> {
            if (comicSans.isSelected()) {
                times.setSelected(false);
                calibri.setSelected(false);
                // Set the font preview but do not apply yet
                themeController.previewFont("comic-sans-font");
            }
        });

        // Create Back Button
        Button backButton = new Button(GeneralSetting.getString("button.back"));
        backButton.setOnAction(event -> util.goBack(primaryStage));

        // Create Confirm Button
        Button simulationButton = new Button(GeneralSetting.getString("button.simulation"));
        simulationButton.setOnAction(event -> themeController.goToSimulationScene(primaryStage));
        
        Button targetButton = new Button(GeneralSetting.getString("button.targetGame"));
        targetButton.setOnAction(event -> themeController.goToTargetScene(primaryStage));
        
        Button vectorButton = new Button(GeneralSetting.getString("button.vector"));
        vectorButton.setOnAction(event -> themeController.goToVectorScene(primaryStage));
        

        // Add the Back and Confirm Buttons to the GridPane
        this.add(backButton, 0, 8);
        this.add(simulationButton, 1, 9); 
        this.add(targetButton, 1, 8);
        this.add(vectorButton, 1, 10);
    }
}