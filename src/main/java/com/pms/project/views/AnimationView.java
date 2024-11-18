package com.pms.project.views;

import com.pms.project.controllers.AnimationController;
import com.pms.project.utils.Util;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AnimationView extends Pane {
    
    private Stage primaryStage;
  
    private AnimationController animationController;

    public AnimationView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        animationController = new AnimationController(primaryStage);

        // Create Back Button
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> animationController.goBack());

        // Create Confirm Button
        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(event -> animationController.goToBaseScene());

        // Create Zoom In Button
        Button zoomInButton = new Button("Zoom In");
        zoomInButton.setOnAction(event -> animationController.zoom(1.1));

        // Create Zoom Out Button
        Button zoomOutButton = new Button("Zoom Out");
        zoomOutButton.setOnAction(event -> animationController.zoom(0.9));

        // Create Rotate Button
        Button rotateButton = new Button("Rotate");
        rotateButton.setOnAction(event -> animationController.rotate());

        // Add the Back and Confirm Buttons to the GridPane
        backButton.setLayoutX(20);
        backButton.setLayoutY(900);

        confirmButton.setLayoutX(1700);
        confirmButton.setLayoutY(900);

        zoomInButton.setLayoutX(0);
        zoomInButton.setLayoutY(0);

        zoomOutButton.setLayoutX(70);
        zoomOutButton.setLayoutY(0);

        rotateButton.setLayoutX(150);
        rotateButton.setLayoutY(0);

        this.getChildren().addAll(backButton,confirmButton,zoomInButton,zoomOutButton,rotateButton);
    }
}
