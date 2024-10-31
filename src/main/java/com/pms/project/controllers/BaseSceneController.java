package com.pms.project.controllers;

import com.pms.project.models.BaseScene;
import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Optional;

public class BaseSceneController {
    private boolean hasMotion = false;
    private BaseScene baseScene;
    private Util util = new Util();
    private Stage primaryStage;
    // Animation related fields
    private Circle object;
    private Timeline timeline;
    private KeyFrame[] keyFrames = new KeyFrame[9];
    private int animationPaneWidth;
    private int animationPaneHeight;

    public BaseSceneController(Stage primaryStage, BaseScene baseScene, int animationPaneWidth, int animationPaneHeight) {
        object = new Circle(3);
        object.setFill(Color.BLACK);
        timeline = new Timeline();
        this.primaryStage = primaryStage;
        this.baseScene = baseScene;
        this.animationPaneHeight = animationPaneHeight;
        this.animationPaneWidth = animationPaneWidth;
    }

    public void onBackToMainPressed() {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            util.switchScene(primaryStage, new Scene(new MainView(primaryStage), MainView.stageWidth, MainView.stageHeight));
        }
    }

    public void updateMass(double newValue) {
        baseScene.setMass(newValue);
        calculateAndSetPhysicalProperties();
    }

    public void updateInitialAngle(double newValue) {
        baseScene.setInitialAngle(newValue);
        calculateAndSetPhysicalProperties();
    }

    public void updateInitialHeight(double newValue) {
        baseScene.setInitialHeight(newValue);
        calculateAndSetPhysicalProperties();
    }

    public void updateInitialSpeed(double newValue) {
        baseScene.setInitialVelocity(newValue);
        calculateAndSetPhysicalProperties();
    }

    public void onEarthButtonPressed() {
        baseScene.setGravity(9.8);
        calculateAndSetPhysicalProperties();
    }

    public void onMoonButtonPressed() {
        baseScene.setGravity(1.62);
        calculateAndSetPhysicalProperties();
    }

    public void onMarsButtonPressed() {
        baseScene.setGravity(3.73);
        calculateAndSetPhysicalProperties();
    }

    public void onJupiterButtonPressed() {
        baseScene.setGravity(24.79);
        calculateAndSetPhysicalProperties();
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
        baseScene.setInitialX(3); // the radius of the circle object
        baseScene.setFinalY(0);

        if (baseScene.getInitialVelocity() == 0.0 || baseScene.getMass() == 0.0) {
            hasMotion = false;
            return; // no motion taking place
        }

        if (baseScene.getInitialAngle() == 0.0) { // the case of horizontal projectile motion
            if (baseScene.getInitialHeight() == 0.0) {
                hasMotion = false;
                return; // no motion takes place
            }
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
            createAnimation();
        } else {
            // the case of normal projectile motion
            double initialXVelocity = baseScene.getInitialVelocity() * Math.cos(baseScene.getInitialAngle());
            baseScene.setInitialXVelocity(initialXVelocity);
            double initialYVelocity = baseScene.getInitialVelocity() * Math.sin(baseScene.getInitialAngle());
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
            createAnimation();
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

            if (root1 >= 0) {
                return root1;
            }
            return root2;
        }
    }

    public void onStartButtonPressed() {
        if (hasMotion) {
            timeline.play();
        } else {
            // tell the user that there's no action taking place
        }
    }

    protected void createAnimation() {
        KeyFrame initialFrame = new KeyFrame(Duration.seconds(0), event -> {
            object.setTranslateX(3);
            object.setTranslateY(3);
        });

        KeyFrame finalFrame = new KeyFrame(Duration.seconds(3), event -> {
            object.setTranslateX(300);
            object.setTranslateY(300);
        });

        timeline.getKeyFrames().addAll(initialFrame, finalFrame);
    }

    public Circle getObject() {
        return object;
    }
}
