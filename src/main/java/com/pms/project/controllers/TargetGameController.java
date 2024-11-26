package com.pms.project.controllers;

import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TargetGameController extends BaseSceneController {
    private TargetGame targetGame;
    private TargetGameView targetGameView;
    private Util util;
    private boolean hasMotion;
   

    public TargetGameController(TargetGame targetGame, TargetGameView targetGameView) {
        super(targetGameView.getPrimaryStage(), targetGame, 800, 600, new Circle[5]);
        this.targetGame = targetGame;
        this.targetGameView = targetGameView;
        this.util = new Util(primaryStage);
    }

    public TargetGame getTargetGame() {
        return targetGame;
    }

    public void onNewTargetButtonPressed() {
        double newX = Math.random() * 1200;
        targetGame.setTargetX(newX);
        targetGameView.getTarget().setCenterX(newX);
    }
    
    
    @Override
    public void onStartButtonPressed() {
        calculateAndSetPhysicalProperties();

        if (hasMotion) {
        	createAnimation();
            timeline.play();
            System.out.println("Final X: " + targetGame.getFinalX());
            System.out.println("Target X: " + targetGame.getTargetX());
            System.out.println("Target Radius: " + targetGame.getTargetRadius());

           
            if (targetGame.checkHit()) {
                System.out.println("Congratulations! You hit the target!");
            } else {
                System.out.println("You missed the target. Try again!");
            }
        } else {
            System.out.println("No motion detected.");
        }
    }

    
    @Override
    public void onRefreshButtonPressed() {
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, targetScene);
    }
}
