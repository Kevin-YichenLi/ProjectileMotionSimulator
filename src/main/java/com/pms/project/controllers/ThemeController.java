package com.pms.project.controllers;

import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThemeController {
    private Stage primaryStage;
    private String selectedBackground = "default-background";
    private String selectedFont = "default-font";
    private int selectedFontSize = 12;

    public ThemeController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Preview theme changes (background, font, font-size)
    public void previewTheme(String theme) {
        if (theme.startsWith("background-")) {
            selectedBackground = theme;
        } else if (theme.startsWith("font-")) {
            selectedFont = theme;
        }
    }

    public void previewFontSize(int size) {
        selectedFontSize = size;
    }

    // Apply confirmed theme changes
    public void applyConfirmedTheme() {
        BaseSceneView baseSceneView = new BaseSceneView(primaryStage);

        // Create a new Scene with BaseSceneView
        Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);

        // Apply selected background, font, and font size
        scene.getRoot().getStyleClass().clear();
        scene.getRoot().getStyleClass().add(selectedBackground);
        scene.getRoot().getStyleClass().add(selectedFont);
        scene.getRoot().setStyle("-fx-font-size: " + selectedFontSize + "px;");

        // Apply the theme stylesheet
        scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

        // Set the scene for the primaryStage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
