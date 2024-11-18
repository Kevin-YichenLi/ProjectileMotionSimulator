package com.pms.project.views;

import java.util.Optional;

import com.pms.project.controllers.ThemeController;
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
    // Constructor that accepts a Stage
    public ThemeView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Set vertical and horizontal gaps for the grid
        this.setVgap(10);
        this.setHgap(10);

        // Title labels
        Label backgroundLabel = new Label("Change Background");
        Label fontLabel = new Label("Change Font");
        Label fontSizeLabel = new Label("Change Font Size");

        // Add labels to the GridPane
        this.add(backgroundLabel, 0, 0);
        this.add(fontLabel, 0, 4);
        this.add(fontSizeLabel, 0, 8);

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

        // Create checkboxes for Font Size
        CheckBox size10 = new CheckBox("10");
        CheckBox size12 = new CheckBox("12");
        CheckBox size14 = new CheckBox("14");

        // Add Font Size checkboxes to the GridPane
        this.add(size10, 1, 9);
        this.add(size12, 1, 10);
        this.add(size14, 1, 11);

        // Logic for allowing only one checkbox selection for Background
        lightBlue.setOnAction(event -> {
            if (lightBlue.isSelected()) {
                darkBlue.setSelected(false);
                sunset.setSelected(false);
            }
        });

        darkBlue.setOnAction(event -> {
            if (darkBlue.isSelected()) {
                lightBlue.setSelected(false);
                sunset.setSelected(false);
            }
        });

        sunset.setOnAction(event -> {
            if (sunset.isSelected()) {
                lightBlue.setSelected(false);
                darkBlue.setSelected(false);
            }
        });

        // Logic for allowing only one checkbox selection for Font
        times.setOnAction(event -> {
            if (times.isSelected()) {
                calibri.setSelected(false);
                comicSans.setSelected(false);
                applyFont("times-font");
            }
        });

        calibri.setOnAction(event -> {
            if (calibri.isSelected()) {
                times.setSelected(false);
                comicSans.setSelected(false);
                applyFont("calibri-font");
            }
        });

        comicSans.setOnAction(event -> {
            if (comicSans.isSelected()) {
                times.setSelected(false);
                calibri.setSelected(false);
                applyFont("comic-sans-font");
            }
        });

        // Logic for allowing only one checkbox selection for Font Size
        size10.setOnAction(event -> {
            if (size10.isSelected()) {
                size12.setSelected(false);
                size14.setSelected(false);
                applyFontSize(10);
            }
        });

        size12.setOnAction(event -> {
            if (size12.isSelected()) {
                size10.setSelected(false);
                size14.setSelected(false);
                applyFontSize(12);
            }
        });

        size14.setOnAction(event -> {
            if (size14.isSelected()) {
                size10.setSelected(false);
                size12.setSelected(false);
                applyFontSize(14);
            }
        });

        // Create Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> goBack());

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> goToBaseScene());

        // Add the Back and Confirm Buttons to the GridPane
        this.add(backButton, 0, 12);
        this.add(confirmButton, 1, 12); 
    }

    // Method to apply font class to the scene root element
    private void applyFont(String fontClass) {
        // Create a new BaseSceneView (or reuse if necessary)
        BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
        
        // Create a new Scene with BaseSceneView and apply the style sheet
        Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);
        
        // Apply the selected font class
        scene.getRoot().getStyleClass().clear();  // Remove any previously set styles
        scene.getRoot().getStyleClass().add(fontClass); // Add the selected font class

        // Apply the theme stylesheet
        scene.getStylesheets().add(getClass().getResource("/com/pms/project/views/theme.css").toExternalForm());

        // Set the scene for the primaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to apply font size to the scene
    private void applyFontSize(int size) {
        Scene currentScene = primaryStage.getScene();
        if (currentScene != null) {
            currentScene.getRoot().setStyle("-fx-font-size: " + size + "px;");
        }
    }

    public void goToBaseScene() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
            Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);
            scene.getRoot().getStyleClass().add("default-font");
            util.switchScene(primaryStage, scene);
        }
    }

    // Method to handle going back to the main scene
    public void goBack() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            MainView mainView = new MainView(primaryStage);
            Scene scene = new Scene(mainView, MainView.stageWidth, MainView.stageHeight);
            util.switchScene(primaryStage, scene);
        }
    }
}
