package com.pms.project.views;

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

    // Constructor that accepts a Stage and Util
    public ThemeView(Stage primaryStage, Util util) {
        this.primaryStage = primaryStage;
        this.util = util;

        // Set vertical and horizontal gaps for the grid
        this.setVgap(10);
        this.setHgap(10);

        // Title labels
        Label backgroundLabel = new Label("Change Background");
        Label fontLabel = new Label("Change Font");

        // Add labels to the GridPane
        this.add(backgroundLabel, 0, 0);
        this.add(fontLabel, 0, 4);

        // Create checkboxes for Background
        CheckBox lightBlue = new CheckBox("Light blue");
        CheckBox darkBlue = new CheckBox("Dark blue");
        CheckBox sunset = new CheckBox("Sunset");

        // Add Background checkboxes to the GridPane
        this.add(lightBlue, 1, 1);
        this.add(darkBlue, 1, 2);
        this.add(sunset, 1, 3);

        // Create checkboxes for Font
        CheckBox times = new CheckBox("Times");
        CheckBox calibri = new CheckBox("Calibri");
        CheckBox comicSans = new CheckBox("Comic Sans");

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
                util.previewBackground("background-light-blue");
            }
        });

        darkBlue.setOnAction(event -> {
            if (darkBlue.isSelected()) {
                lightBlue.setSelected(false);
                sunset.setSelected(false);
                // Preview dark blue background
                util.previewBackground("background-dark-blue");
            }
        });

        sunset.setOnAction(event -> {
            if (sunset.isSelected()) {
                lightBlue.setSelected(false);
                darkBlue.setSelected(false);
                // Preview sunset background
                util.previewBackground("background-sunset");
            }
        });

        // Logic for allowing only one checkbox selection for Font
        times.setOnAction(event -> {
            if (times.isSelected()) {
                calibri.setSelected(false);
                comicSans.setSelected(false);
                // Set the font preview but do not apply yet
                util.previewFont("times-font");
            }
        });

        calibri.setOnAction(event -> {
            if (calibri.isSelected()) {
                times.setSelected(false);
                comicSans.setSelected(false);
                // Set the font preview but do not apply yet
                util.previewFont("calibri-font");
            }
        });

        comicSans.setOnAction(event -> {
            if (comicSans.isSelected()) {
                times.setSelected(false);
                calibri.setSelected(false);
                // Set the font preview but do not apply yet
                util.previewFont("comic-sans-font");
            }
        });

        // Create Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> util.goBack(primaryStage));

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> util.goToBaseScene(primaryStage));
        
        Button targetButton = new Button("Target");
        targetButton.setOnAction(event -> util.goToTargetScene(primaryStage));

        // Add the Back and Confirm Buttons to the GridPane
        this.add(backButton, 0, 8);
        this.add(confirmButton, 1, 8); 
        this.add(targetButton, 1, 9);
    }
}