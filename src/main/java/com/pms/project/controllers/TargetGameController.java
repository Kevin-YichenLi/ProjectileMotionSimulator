package com.pms.project.controllers;

import com.pms.project.AnimationStatus;
import com.pms.project.models.BaseScene;
import com.pms.project.models.TargetGame;
import com.pms.project.views.TargetGameView;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TargetGameController extends BaseSceneController {
	
    private  TargetGame targetGame = new TargetGame();

    public TargetGameController(Stage primaryStage, BaseScene baseScene, int animationPaneWidth, int animationPaneHeight, Circle[] trails, SimpleObjectProperty<AnimationStatus> status) {
    	super(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails, status);
    }
  

    public TargetGame getTargetGame() {
        return targetGame;
    }

    public void onNewTargetButtonPressed() {
        double newX = Math.random() * 1200;         
        targetGame.setTargetX(newX);  
        targetGame.getTarget().setCenterX(newX);  

        
       
    }

    @Override
    public void onStartButtonPressed() {
        if (status.get() == AnimationStatus.PLAYED) {
            return;
        }

        // Set the animation status to "played"
        status.set(AnimationStatus.PLAYED);
        this.calculateAndSetPhysicalProperties();

        System.out.println(targetGame);
        if (hasMotion) {
            // Create and play animation if there's motion
            System.out.println("has motion");
            createAnimation();
            timeline.play();
        } else {
            // Tell the user there's no motion
            System.out.println("no motion");
        }

    
        if (targetGame.checkHit()) {
      
            System.out.println("Congratulations! You've hit the target!");
        } else {
          
            System.out.println("Try again. The target wasn't hit.");
        }
    }
    @Override
    protected void calculateAndSetPhysicalProperties() {
        if (targetGame.getInitialVelocity() == 0.0 || targetGame.getMass() == 0.0) {
            hasMotion = false;
            return; // no motion taking place
        }

        if (targetGame.getInitialAngle() == 0.0) { // the case of horizontal projectile motion
            if (targetGame.getInitialHeight() == 0.0) {
                hasMotion = false;
                return; // no motion takes place
            }
            targetGame.setInitialY(targetGame.getInitialHeight() + 3);
            targetGame.setMaxHeight(targetGame.getInitialHeight());
            targetGame.setInitialXVelocity(targetGame.getInitialVelocity());
            targetGame.setInitialYVelocity(0);
            double changeInHeight = targetGame.getInitialHeight() * -1;
            targetGame.setChangeInHeight(changeInHeight);
            double time = Math.sqrt(-1 * targetGame.getChangeInHeight() * 2 / targetGame.getGravity());
            targetGame.setTime(time);
            double distance = time * targetGame.getInitialXVelocity();
            targetGame.setDistance(distance);
            targetGame.setFinalXVelocity(targetGame.getInitialXVelocity());
            double finalYVelocity = -1 * targetGame.getGravity() * time;
            targetGame.setFinalYVelocity(finalYVelocity);
            targetGame.setFinalX(targetGame.getInitialX() + distance);
            hasMotion = true;
            isHorizontalProjectileMotion = true;
        } else {
            // the case of normal projectile motion
            targetGame.setInitialY(targetGame.getInitialHeight() + 3);
            double initialXVelocity = targetGame.getInitialVelocity() * Math.cos(Math.toRadians(targetGame.getInitialAngle()));
            targetGame.setInitialXVelocity(initialXVelocity);
            double initialYVelocity = targetGame.getInitialVelocity() * Math.sin(Math.toRadians(targetGame.getInitialAngle()));
            targetGame.setInitialYVelocity(initialYVelocity);
            double changeInHeight = targetGame.getInitialHeight() * -1;
            targetGame.setChangeInHeight(changeInHeight);
            double finalYVelocity = -1 * Math.sqrt(Math.pow(targetGame.getInitialYVelocity(), 2)
                    - 2 * targetGame.getGravity() * targetGame.getChangeInHeight());
            targetGame.setFinalYVelocity(finalYVelocity);
            double time = timeCalculator(-0.5 * targetGame.getGravity(), targetGame.getInitialYVelocity(), -1 * changeInHeight);
            targetGame.setTime(time);
            double maxHeight = targetGame.getInitialHeight() +
                    (targetGame.getInitialYVelocity() * time / 2 - targetGame.getGravity() / 2 * Math.pow((time / 2), 2));
            targetGame.setMaxHeight(maxHeight);
            double distance = time * targetGame.getInitialXVelocity();
            targetGame.setDistance(distance);
            targetGame.setFinalXVelocity(targetGame.getInitialXVelocity());
            targetGame.setFinalX(targetGame.getInitialX() + distance);
            hasMotion = true;
            isHorizontalProjectileMotion = false;
        }
    }

    @Override
    protected void createAnimation() {
        KeyFrame currentFrame;
        double x;
        double y;

        int n = 1000;
        int[] trailIndices = {0, n / 8, n / 8 * 2, n / 8 * 3, n / 8 * 4, n / 8 * 5, n / 8 * 6, n / 8 * 7, n - 1};

        for (int i = 0; i < n; i++) {
            x = (targetGame.getDistance() * i / (n - 1)) + targetGame.getInitialX();
            y = calculateCurrentHeight(targetGame.getTime() * i / (n - 1));
            currentFrame = new KeyFrame(Duration.seconds(targetGame.getTime() * i / (n - 1)),
                    new KeyValue(object.translateXProperty(), x, Interpolator.LINEAR),
                    new KeyValue(object.translateYProperty(), y, Interpolator.LINEAR)
            );

            for (int j = 0; j < trailIndices.length; j++) {
                if (i == trailIndices[j]) {
                    trails[j].setStroke(Color.BLACK);
                    trails[j].setCenterX(x);
                    trails[j].setCenterY(y);
                }
            }
            timeline.getKeyFrames().add(currentFrame);
        }

        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.getKeyFrames().clear();
                status.set(AnimationStatus.FINISHED);
            }
        });
    }

    @Override
    protected double calculateCurrentHeight(double currentTime) {
        double currentChangeInHeight = targetGame.getInitialYVelocity() * currentTime - targetGame.getGravity() / 2 * Math.pow(currentTime, 2);
        return animationPaneHeight - targetGame.getInitialHeight() - currentChangeInHeight - 3;
    }

    @Override
    public void updateMass(double newValue) {
        targetGame.setMass(newValue);
    }

    @Override
    public void updateInitialAngle(double newValue) {
        targetGame.setInitialAngle(newValue);
    }

    @Override
    public void updateInitialHeight(double newValue) {
        targetGame.setInitialHeight(newValue);
    }
    @Override
    public void updateInitialSpeed(double newValue) {
        targetGame.setInitialVelocity(newValue);
    }

    @Override
    public void onEarthButtonPressed() {
        targetGame.setGravity(9.8);
        rearrangeToPreparedStatus();
    }
    @Override
    public void onMoonButtonPressed() {
        targetGame.setGravity(1.62);
        rearrangeToPreparedStatus();
    }
    @Override
    public void onMarsButtonPressed() {
        targetGame.setGravity(3.73);
        rearrangeToPreparedStatus();
    }
    @Override
    public void onJupiterButtonPressed() {
        targetGame.setGravity(24.79);
        rearrangeToPreparedStatus();
    }

    @Override
    public void onRefreshButtonPressed() {
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, targetScene);
    }

    
}
