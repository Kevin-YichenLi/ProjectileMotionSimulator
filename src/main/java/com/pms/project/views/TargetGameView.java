package com.pms.project.views;

import com.pms.project.controllers.TargetGameController;
import com.pms.project.models.TargetGame;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TargetGameView extends BaseSceneView {
    private Button newTargetButton;
    private Circle target;
    private Label feedbackLabel;  // Add a label to show feedback

    private TargetGameController controller;

    public TargetGameView(Stage primaryStage) {
        super(primaryStage);

        TargetGame targetGame = new TargetGame();
        this.controller = new TargetGameController(targetGame, this, status);

        // Create and set up target circle
        target = new Circle(20, Color.RED);
        target.setCenterX(controller.getTargetGame().getTargetX());
        target.setCenterY(controller.getTargetGame().getTargetY());

        // Create the button for a new target
        newTargetButton = new Button("New Target");
        newTargetButton.setOnAction(event -> controller.onNewTargetButtonPressed());
        
      

        
        // Create the feedback label
        feedbackLabel = new Label();
        feedbackLabel.setLayoutX(350);  
        feedbackLabel.setLayoutY(600);  
        feedbackLabel.setTextFill(Color.BLACK);  

        ((Pane) this.getCenter()).getChildren().addAll(target, newTargetButton, feedbackLabel);

        target.setLayoutX(200);  
        target.setLayoutY(200);  

        newTargetButton.setLayoutX(5);  
        newTargetButton.setLayoutY(350);  
        newTargetButton.setStyle("-fx-background-color: #f4cccc; -fx-text-fill: black; -fx-border-color: #b45454;");
        
        
    }
   

   public Region createStartAndStopButton() {
        HBox container = new HBox(20);

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> controller.onStartButtonPressed());

        Button refreshButton = new Button();
        refreshButton.setOnAction(event -> controller.onRefreshButtonPressed());
        Image refreshImage = new Image(this.getClass().getResource("/images/refresh.png").toExternalForm());
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setPreserveRatio(true);
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        refreshButton.setGraphic(refreshImageView);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> controller.onStopButtonPressed());

        container.getChildren().addAll(startButton, refreshButton, stopButton);
        return container;
    }

    
    
    public Button getNewTargetButton() {
        return newTargetButton;
    }

    public Circle getTarget() {
        return target;
    }

    public Label getFeedbackLabel() {
        return feedbackLabel;  
    }
}
