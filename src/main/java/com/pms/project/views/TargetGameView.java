package com.pms.project.views;

import com.pms.project.controllers.TargetGameController;
import com.pms.project.models.TargetGame;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TargetGameView extends BaseSceneView {
    private Button newTargetButton;
    private Circle target;
    private Circle projectile;
    private TargetGameController controller;
    private Label feedbackLabel;  // Add a label to show feedback

    public TargetGameView(Stage primaryStage) {
        super(primaryStage);

        
        TargetGame targetGame = new TargetGame();

       
        projectile = new Circle(5, Color.BLACK);
        projectile.setCenterX(400);  // Set initial position
        projectile.setCenterY(550);  // Set initial position

        // Create the controller and pass the model to it
        this.controller = new TargetGameController(targetGame, this);

        // Create and set up target circle
        target = new Circle(20, Color.RED);
        target.setCenterX(controller.getTargetGame().getTargetX());
        target.setCenterY(controller.getTargetGame().getTargetY());

        // Create the button for a new target
        newTargetButton = new Button("New Target");
        newTargetButton.setOnAction(event -> controller.onNewTargetButtonPressed());

        // Create the feedback label (initially empty)
        feedbackLabel = new Label();
        feedbackLabel.setLayoutX(350);  // Position the label
        feedbackLabel.setLayoutY(600);  // Position the label below the target
        feedbackLabel.setTextFill(Color.BLACK);  // Set text color

        // Add the target, projectile, button, and feedback label to the center pane of the base scene
        ((Pane) this.getCenter()).getChildren().addAll(target, projectile, newTargetButton, feedbackLabel);

        // Position the new elements (target, projectile, and button)
        target.setLayoutX(200);  // Set target X position
        target.setLayoutY(200);  // Set target Y position
        System.out.println("Target position: " + target.getLayoutX() + ", " + target.getLayoutY());

        projectile.setLayoutX(400);  // Set projectile X position
        projectile.setLayoutY(550);  // Set projectile Y position
        System.out.println("Projectile position: " + projectile.getLayoutX() + ", " + projectile.getLayoutY());

        newTargetButton.setLayoutX(5); // Position button
        newTargetButton.setLayoutY(350); // Position button below the projectile
        newTargetButton.setStyle("-fx-background-color: #f4cccc; -fx-text-fill: black; -fx-border-color: #b45454;");
        
        // Set up event listener for the projectile after it has been initialized
        projectile.setOnMouseClicked(event -> controller.onProjectileShot(event));
    }

    public Button getNewTargetButton() {
        return newTargetButton;
    }

    public Circle getTarget() {
        return target;
    }

    public Circle getProjectile() {
        return projectile;
    }

    public Label getFeedbackLabel() {
        return feedbackLabel;  
    }
}
