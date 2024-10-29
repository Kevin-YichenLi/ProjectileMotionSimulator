package com.pms.project.views;

import com.pms.project.controllers.AnimationController;
import com.pms.project.utils.Util;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AnimationView extends GridPane {
    private Util util = new Util();
    private Stage primaryStage;
    private AnimationController animationController;

    public AnimationView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        animationController = new AnimationController(this, primaryStage);

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
        rotateButton.setOnAction(event -> animationController.rotate(90));

        // Add the Back and Confirm Buttons to the GridPane
        this.add(backButton, 0, 12);
        this.add(confirmButton, 1, 12);
        this.add(zoomInButton, 2, 12);
        this.add(zoomOutButton, 3, 12);
        this.add(rotateButton, 4, 12);
    }
}
