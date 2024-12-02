package com.pms.project.controllers;

import com.pms.project.AnimationStatus;

import com.pms.project.models.GeneralSetting;
import com.pms.project.models.TargetGame;
import com.pms.project.utils.Util;

import com.pms.project.views.MainView;
import com.pms.project.views.TargetGameView;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class TargetGameController {
    protected ObjectProperty<AnimationStatus> status;
    protected boolean hasMotion = false;
    protected boolean isHorizontalProjectileMotion;
    protected TargetGame baseScene;
  
    protected Stage primaryStage;
    protected Util util = new Util(primaryStage);
    // Animation related fields
    protected Circle object;
    protected Timeline timeline;
    protected int animationPaneWidth;
    protected int animationPaneHeight;
    protected Scale scaleTransform= new Scale(1.1, 0.9);
    protected Circle[] trails;
    public Label feedbackLabel = new Label() ;

    public TargetGameController(Stage primaryStage, TargetGame baseScene, int animationPaneWidth,
                               int animationPaneHeight, Circle[] trails, SimpleObjectProperty<AnimationStatus> status) {
        this.status = status;
        timeline = new Timeline();
        this.primaryStage = primaryStage;
        this.baseScene = baseScene;
        this.animationPaneHeight = animationPaneHeight;
        this.animationPaneWidth = animationPaneWidth;
        this.trails = trails;
        createObject();
    }
 	public TargetGame getTargetGame() {
        return baseScene;
    }

    public void onNewTargetButtonPressed() {
        double newX = Math.random() * 1200;         
        baseScene.setTargetX(newX);  
        baseScene.getTarget().setCenterX(newX);  
    }

	

    protected void createObject() {
        object = new Circle(3);
        object.setFill(Color.BLACK);
        object.setTranslateX(baseScene.getInitialX());
        object.setTranslateY(animationPaneHeight - 3);
    }

    public void onBackToMainPressed() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle(GeneralSetting.getString("label.confirmationTitle"));
        confirmationAlert.setContentText(GeneralSetting.getString("text.confirmation.toMain"));
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            util.switchScene(primaryStage, new Scene(new MainView(primaryStage), MainView.stageWidth, MainView.stageHeight));
        }
    }

    public void onInitialHeightValueChanged(Double newValue, Rectangle base) {
        updateInitialHeight(newValue);
        base.setHeight(newValue);
        base.setY(animationPaneHeight - base.getHeight());
        object.setTranslateY(animationPaneHeight - baseScene.getInitialHeight() - 3);
        rearrangeToPreparedStatus();
    }

    public void onMassValueChanged(Double newValue) {
        updateMass(newValue);
        rearrangeToPreparedStatus();
    }

    public void onInitialSpeedValueChanged(Number newValue) {
        updateInitialSpeed(newValue.doubleValue());
        rearrangeToPreparedStatus();
    }

    public void onInitialAngleValueChanged(Double newValue) {
        updateInitialAngle(newValue);
        rearrangeToPreparedStatus();
    }

    protected void rearrangeToPreparedStatus() {
        setTrailsInvisible();
        status.set(AnimationStatus.PREPARED);
        object.setTranslateX(baseScene.getInitialX());
        object.setTranslateY(animationPaneHeight - baseScene.getInitialHeight() - 3);
    }

    protected void setTrailsInvisible() {
        for (int i = 0; i < trails.length; i++) {
            trails[i].setStroke(Color.TRANSPARENT);
        }
    }

    public void updateMass(double newValue) {
        baseScene.setMass(newValue);
    }

    public void updateInitialAngle(double newValue) {
        baseScene.setInitialAngle(newValue);
    }

    public void updateInitialHeight(double newValue) {
        baseScene.setInitialHeight(newValue);
    }

    public void updateInitialSpeed(double newValue) {
        baseScene.setInitialVelocity(newValue);
    }

    public void onEarthButtonPressed() {
        baseScene.setGravity(9.8);
        rearrangeToPreparedStatus();
    }

    public void onMoonButtonPressed() {
        baseScene.setGravity(1.62);
        rearrangeToPreparedStatus();
    }

    public void onMarsButtonPressed() {
        baseScene.setGravity(3.73);
        rearrangeToPreparedStatus();
    }

    public void onJupiterButtonPressed() {
        baseScene.setGravity(24.79);
        rearrangeToPreparedStatus();
    }

    public boolean hasMotion() {
        return hasMotion;
    }

    public void setHasMotion(boolean hasMotion) {
        this.hasMotion = hasMotion;
    }

    /**
     * This method would use formulas to calculate other properties of projectile motion such as time,
     * final Y, max height, etc.
     */
    protected void calculateAndSetPhysicalProperties() {
        if (baseScene.getInitialVelocity() == 0.0 || baseScene.getMass() == 0.0) {
            hasMotion = false;
            return; // no motion taking place
        }

        if (baseScene.getInitialAngle() == 0.0) { // the case of horizontal projectile motion
            if (baseScene.getInitialHeight() == 0.0) {
                hasMotion = false;
                return; // no motion takes place
            }
            baseScene.setInitialY(baseScene.getInitialHeight() + 3);
            baseScene.setMaxHeight(baseScene.getInitialHeight());
            baseScene.setInitialXVelocity(baseScene.getInitialVelocity());
            baseScene.setInitialYVelocity(0);
            double changeInHeight = baseScene.getInitialHeight() * -1;
            baseScene.setChangeInHeight(changeInHeight);
            double time = Math.sqrt(-1 * baseScene.getChangeInHeight() * 2 / baseScene.getGravity());
            baseScene.setTime(time);
            double distance = time * baseScene.getInitialXVelocity();
            baseScene.setDistance(distance);
            baseScene.setFinalXVelocity(baseScene.getInitialXVelocity());
            double finalYVelocity = -1 * baseScene.getGravity() * time;
            baseScene.setFinalYVelocity(finalYVelocity);
            baseScene.setFinalX(baseScene.getInitialX() + distance);
            hasMotion = true;
            isHorizontalProjectileMotion = true;
        } else {
            // the case of normal projectile motion
            baseScene.setInitialY(baseScene.getInitialHeight() + 3);
            double initialXVelocity = baseScene.getInitialVelocity() * Math.cos(Math.toRadians(baseScene.getInitialAngle()));
            baseScene.setInitialXVelocity(initialXVelocity);
            double initialYVelocity = baseScene.getInitialVelocity() * Math.sin(Math.toRadians(baseScene.getInitialAngle()));
            baseScene.setInitialYVelocity(initialYVelocity);
            double changeInHeight = baseScene.getInitialHeight() * -1;
            baseScene.setChangeInHeight(changeInHeight);
            double finalYVelocity = -1 * Math.sqrt(Math.pow(baseScene.getInitialYVelocity(), 2)
                    - 2 * baseScene.getGravity() * baseScene.getChangeInHeight());
            baseScene.setFinalYVelocity(finalYVelocity);
            double time = timeCalculator(-0.5 * baseScene.getGravity(), baseScene.getInitialYVelocity(), -1 * changeInHeight);
            baseScene.setTime(time);
            double maxHeight = baseScene.getInitialHeight() +
                    (baseScene.getInitialYVelocity() * time / 2 - baseScene.getGravity() / 2 * Math.pow((time / 2), 2));
            baseScene.setMaxHeight(maxHeight);
            double distance = time * baseScene.getInitialXVelocity();
            baseScene.setDistance(distance);
            baseScene.setFinalXVelocity(baseScene.getInitialXVelocity());
            baseScene.setFinalX(baseScene.getInitialX() + distance);
            hasMotion = true;
            isHorizontalProjectileMotion = false;
        }
    }

    /**
     * calculate the time by solving a quadratic function ax^2 + bx + c
     *
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c coefficient of the constant
     * @return the value of time
     */
    protected double timeCalculator(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant == 0) {
            double root = -1 * b / (2 * a);
            return root;
        } else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);

            if (root1 > 0) {
                return root1;
            }
            return root2;
        }
    }

    public void onStartButtonPressed() {
        if (status.get() == AnimationStatus.PLAYED) {
            return;
        }

        // Set the animation status to "played"
        status.set(AnimationStatus.PLAYED);
        this.calculateAndSetPhysicalProperties();

        System.out.println(baseScene);
        if (hasMotion) {
            // Create and play animation if there's motion
            System.out.println("has motion");
            createAnimation();
            timeline.play();
        } else {
            // Tell the user there's no motion
            System.out.println("no motion");
        }

    
        if (checkHit()) {
        	feedbackLabel.setLayoutX(750);
        	feedbackLabel.setLayoutY(150);
        	feedbackLabel.setText(GeneralSetting.getString("label.hit"));
        } else {
        	feedbackLabel.setLayoutX(750);
        	feedbackLabel.setLayoutY(150);
            feedbackLabel.setText(GeneralSetting.getString("label.missed"));
        }
    }
    protected void createAnimation() {
        KeyFrame currentFrame;
        double x;
        double y;

        int n = 1000;
        int[] trailIndices = {0, n / 8, n / 8 * 2, n / 8 * 3, n / 8 * 4, n / 8 * 5, n / 8 * 6, n / 8 * 7, n - 1};

        for (int i = 0; i < n; i++) {
            x = (baseScene.getDistance() * i / (n - 1)) + baseScene.getInitialX();
            y = calculateCurrentHeight(baseScene.getTime() * i / (n - 1));
            currentFrame = new KeyFrame(Duration.seconds(baseScene.getTime() * i / (n - 1)),
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

    protected double calculateCurrentHeight(double currentTime) {
        double currentChangeInHeight = baseScene.getInitialYVelocity() * currentTime - baseScene.getGravity() / 2 * Math.pow(currentTime, 2);
        return animationPaneHeight - baseScene.getInitialHeight() - currentChangeInHeight - 3;
    }

    public void onStopButtonPressed() {
        if (status.get() != AnimationStatus.PLAYED) {
            return;
        }

        status.set(AnimationStatus.STOPPED);

        timeline.pause();
    }

    public void onRefreshButtonPressed() {
        TargetGameView newTargetGameView = new TargetGameView(primaryStage);
        Scene targetScene = new Scene(newTargetGameView, MainView.stageWidth, MainView.stageHeight);
        util.switchScene(primaryStage, targetScene);
    }

    public Circle getObject() {
        return object;
    }
   

    public boolean checkHit() {
        double adjustedTargetX = baseScene.getTargetX() + 50; 
       
        if (baseScene.getFinalX() >= adjustedTargetX - baseScene.getTargetRadius() && baseScene.getFinalX() <= adjustedTargetX + baseScene.getTargetRadius()) {
            baseScene.setHit(true);
            return true;
        }
        System.out.println(adjustedTargetX);
        baseScene.setHit(false);
        return false;
    }
}
