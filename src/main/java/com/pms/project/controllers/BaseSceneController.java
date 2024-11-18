package com.pms.project.controllers;

import com.pms.project.models.BaseScene;
import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.util.List;
import java.util.Optional;

public class BaseSceneController {
    private boolean hasMotion = false;
    private boolean isHorizontalProjectileMotion;
    private BaseScene baseScene;
  
    private Stage primaryStage;
    private Util util = new Util(primaryStage);
    // Animation related fields
    private Circle object;
    private Timeline timeline;
    private KeyFrame[] keyFrames = new KeyFrame[9];
    private int animationPaneWidth;
    private int animationPaneHeight;
    private Circle[] trails;

    public BaseSceneController(Stage primaryStage, BaseScene baseScene, int animationPaneWidth, int animationPaneHeight, Circle[] trails) {
        object = new Circle(3);
        object.setFill(Color.BLACK);
        timeline = new Timeline();
        this.primaryStage = primaryStage;
        this.baseScene = baseScene;
        this.animationPaneHeight = animationPaneHeight;
        this.animationPaneWidth = animationPaneWidth;
        this.trails = trails;
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
    }

    public void onMoonButtonPressed() {
        baseScene.setGravity(1.62);
    }

    public void onMarsButtonPressed() {
        baseScene.setGravity(3.73);
    }

    public void onJupiterButtonPressed() {
        baseScene.setGravity(24.79);
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
            baseScene.setInitialY(baseScene.getInitialHeight());
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
        calculateAndSetPhysicalProperties();
        if (hasMotion) {
            System.out.println("has motion");
            System.out.println(baseScene);
            createAnimation();
            timeline.play();
        } else {
            // tell the user that there's no action taking place
            System.out.println("no motion");
        }
    }

    protected void createAnimation() {
        KeyFrame currentFrame;
        KeyValue xValue;
        KeyValue yValue;
        double x;
        double y;

        for (int i = 0; i < 1000; i++) {
            x = baseScene.getDistance() * i / 999;
            y = calculateCurrentHeight(baseScene.getTime() * i / 999);
            currentFrame = new KeyFrame(Duration.seconds(baseScene.getTime() * i / 999),
                xValue = new KeyValue(object.translateXProperty(), x, Interpolator.LINEAR),
                yValue = new KeyValue(object.translateYProperty(), y, Interpolator.LINEAR)
            );

            switch (i) {
                case 0: {
                    trails[0].setStroke(Color.BLACK);
                    trails[0].setCenterX(x);
                    trails[0].setCenterY(y);
                    break;
                }
                case 1000 / 8: {
                    trails[1].setStroke(Color.BLACK);
                    trails[1].setCenterX(x);
                    trails[1].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 2: {
                    trails[2].setStroke(Color.BLACK);
                    trails[2].setCenterX(x);
                    trails[2].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 3: {
                    trails[3].setStroke(Color.BLACK);
                    trails[3].setCenterX(x);
                    trails[3].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 4: {
                    trails[4].setStroke(Color.BLACK);
                    trails[4].setCenterX(x);
                    trails[4].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 5: {
                    trails[5].setStroke(Color.BLACK);
                    trails[5].setCenterX(x);
                    trails[5].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 6: {
                    trails[6].setStroke(Color.BLACK);
                    trails[6].setCenterX(x);
                    trails[6].setCenterY(y);
                    break;
                }
                case 1000 / 8 * 7: {
                    trails[7].setStroke(Color.BLACK);
                    trails[7].setCenterX(x);
                    trails[7].setCenterY(y);
                    break;
                }
                case 999: {
                    trails[8].setStroke(Color.BLACK);
                    trails[8].setCenterX(x);
                    trails[8].setCenterY(y);
                    break;
                }
            }
            timeline.getKeyFrames().add(currentFrame);
        }

        timeline.setOnFinished(event -> timeline.getKeyFrames().clear());
    }

    private double calculateCurrentHeight(double currentTime) {
        double currentChangeInHeight = baseScene.getInitialYVelocity() * currentTime - baseScene.getGravity() / 2 * Math.pow(currentTime, 2);
        return animationPaneHeight - baseScene.getInitialHeight() - currentChangeInHeight;
    }

    public Circle getObject() {
        return object;
    }
}
