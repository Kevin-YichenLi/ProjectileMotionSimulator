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
    private TargetGameController controller;  

    public TargetGameView(Stage primaryStage) {
        super(primaryStage);

        
        this.controller = new TargetGameController(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails, status);

       
        TargetGame targetGame = controller.getTargetGame();
        Circle target = targetGame.getTarget();  

        // Set initial position of the target
        target.setCenterX(targetGame.getTargetX());
        target.setCenterY(targetGame.getTargetY());

        // Create the button for a new target
        newTargetButton = new Button("New Target");
        newTargetButton.setOnAction(event -> controller.onNewTargetButtonPressed());
     

        ((Pane) this.getCenter()).getChildren().addAll(target, newTargetButton);

        targetGame.getTarget().setLayoutX(200);  
        targetGame.getTarget().setLayoutY(200);  

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
  
    
  

    

   
}
