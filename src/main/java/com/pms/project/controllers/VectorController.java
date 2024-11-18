package com.pms.project.controllers;

import com.pms.project.models.BaseScene;
import com.pms.project.utils.Util;
import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class VectorController extends BaseSceneController {
    
    // Add any new properties specific to this subclass
    private String customFeature;

    // Constructor
    public VectorController(Stage primaryStage, BaseScene baseScene, int animationPaneWidth, int animationPaneHeight, Circle[] trails, String customFeature) {
        super(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails);
        this.customFeature = customFeature;
    }

    // Getter and Setter for the new custom feature
    public String getCustomFeature() {
        return customFeature;
    }

    public void setCustomFeature(String customFeature) {
        this.customFeature = customFeature;
    }

    // Example of overriding a method from BaseSceneController
    @Override
    public void onStartButtonPressed() {
        // You can call the parent method using super
        super.onStartButtonPressed();

        // Additional functionality for AdvancedSceneController
        System.out.println("Custom feature: " + customFeature);
        // You could add custom animations or modify properties here
    }

    // Example of a new method specific to the AdvancedSceneController
    public void enableSpecialEffect() {
        System.out.println("Special effect enabled with custom feature: " + customFeature);
        // Logic for special effects goes here, for example, changing the color of the object.
        getObject().setFill(Color.RED);
    }

    @Override
    public void onBackToMainPressed() {
        // Optionally override any method from the base class, for example, the back button logic
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Call the superclass method to switch to the main view
            super.onBackToMainPressed();
        }
    }
}
