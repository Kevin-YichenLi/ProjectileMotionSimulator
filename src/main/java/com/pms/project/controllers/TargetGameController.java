package com.pms.project.controllers;

import com.pms.project.AnimationStatus;
import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.SimpleObjectProperty;
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
   

    public TargetGameController(TargetGame targetGame, TargetGameView targetGameView, SimpleObjectProperty<AnimationStatus> status) {
        super(targetGameView.getPrimaryStage(), targetGame, 800, 600, new Circle[5], status);
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
    protected void calculateAndSetPhysicalProperties() {
        // Force the baseScene to use TargetGame-specific calculations
        if (targetGame.getInitialVelocity() == 0.0 || targetGame.getMass() == 0.0) {
            hasMotion = false;
            return; // No motion takes place
        }

        if (targetGame.getInitialAngle() == 0.0) { // Horizontal projectile motion
            if (targetGame.getInitialHeight() == 0.0) {
                hasMotion = false;
                return; // No motion takes place
            }
            targetGame.setInitialY(targetGame.getInitialHeight() + 3);
            targetGame.setMaxHeight(targetGame.getInitialHeight());
            targetGame.setInitialXVelocity(targetGame.getInitialVelocity());
            targetGame.setInitialYVelocity(0);
            double changeInHeight = targetGame.getInitialHeight() * -1;
            targetGame.setChangeInHeight(changeInHeight);
            double time = Math.sqrt(-1 * changeInHeight * 2 / targetGame.getGravity());
            targetGame.setTime(time);
            double distance = time * targetGame.getInitialXVelocity();
            targetGame.setDistance(distance);
            targetGame.setFinalXVelocity(targetGame.getInitialXVelocity());
            double finalYVelocity = -1 * targetGame.getGravity() * time;
            targetGame.setFinalYVelocity(finalYVelocity);
            targetGame.setFinalX(targetGame.getInitialX() + distance);
            hasMotion = true;
        } else {
            // Normal projectile motion
            targetGame.setInitialY(targetGame.getInitialHeight() + 3);
            double initialXVelocity = targetGame.getInitialVelocity() * Math.cos(Math.toRadians(targetGame.getInitialAngle()));
            targetGame.setInitialXVelocity(initialXVelocity);
            double initialYVelocity = targetGame.getInitialVelocity() * Math.sin(Math.toRadians(targetGame.getInitialAngle()));
            targetGame.setInitialYVelocity(initialYVelocity);
            double changeInHeight = targetGame.getInitialHeight() * -1;
            targetGame.setChangeInHeight(changeInHeight);
            double finalYVelocity = -1 * Math.sqrt(Math.pow(initialYVelocity, 2)
                    - 2 * targetGame.getGravity() * changeInHeight);
            targetGame.setFinalYVelocity(finalYVelocity);
            double time = timeCalculator(-0.5 * targetGame.getGravity(), initialYVelocity, -1 * changeInHeight);
            targetGame.setTime(time);
            double maxHeight = targetGame.getInitialHeight()
                    + (initialYVelocity * time / 2 - targetGame.getGravity() / 2 * Math.pow((time / 2), 2));
            targetGame.setMaxHeight(maxHeight);
            double distance = time * initialXVelocity;
            targetGame.setDistance(distance);
            targetGame.setFinalXVelocity(initialXVelocity);
            targetGame.setFinalX(targetGame.getInitialX() + distance);
            hasMotion = true;
        }
    }
   
    
    
    
    @Override
    public void onRefreshButtonPressed() {
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, targetScene);
    }
}
