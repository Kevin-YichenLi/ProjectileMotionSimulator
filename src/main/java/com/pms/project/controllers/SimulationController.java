package com.pms.project.controllers;

import com.pms.project.models.BaseScene;
import com.pms.project.utils.Util;
import com.pms.project.views.MainView;
import com.pms.project.views.SimulationView;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimulationController {
    private double mouseX; // the initial x position of cursor when the mouse click the detector relative to the detector
    private double mouseY; // the initial y position of cursor when the mouse click the detector relative to the detector
    private Circle[] trails;
    // the absolute coordinates for the center of the circle
    private double detectorX;
    private double detectorY;
    // the coordinate for the center of the circle relative to top left corner of the detector
    private double relativeX;
    private double relativeY;
    private BaseScene baseScene;
    private double animationPaneWidth;
    private double animationPaneHeight;

    public SimulationController(Circle[] trails, double detectorX, double detectorY, double relativeX, double relativeY,
                                BaseScene baseScene, double animationPaneWidth, double animationPaneHeight) {
        this.trails = trails;
        this.detectorX = detectorX;
        this.detectorY = detectorY;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.baseScene = baseScene;
        this.animationPaneHeight = animationPaneHeight;
        this.animationPaneWidth = animationPaneWidth;

    }

    public void enableDetectorDragAndDrop(Group group, double initialX, double initialY, SimpleStringProperty displayedTime,
                                          SimpleStringProperty displayedHeight, SimpleStringProperty displayedRange,
                                          double animationPaneLayoutX, double animationPaneLayoutY) {
        // Mouse pressed: calculate the initial offset
        group.setOnMousePressed(event -> {
            mouseX = event.getSceneX() - group.getLayoutX();
            mouseY = event.getSceneY() - group.getLayoutY();
        });

        // Mouse dragged: update the position while maintaining the offset
        group.setOnMouseDragged(event -> {
            group.setLayoutX(event.getSceneX() - mouseX);
            group.setLayoutY(event.getSceneY() - mouseY);

            // update detectorX and detectorY
            detectorX = group.getLayoutX() + relativeX - animationPaneLayoutX;
            detectorY = group.getLayoutY() + relativeY - animationPaneLayoutY;

            double[] trailCoordinates = detectorTrailOverlap();

            if (trailCoordinates == null) {
                displayedHeight.set("");
                displayedRange.set("");
                displayedTime.set("");
            } else {
                double x = trailCoordinates[0];
                double y = trailCoordinates[1];

                displayedHeight.set(String.format("%.2f", animationPaneHeight - y - 3));
                displayedRange.set(String.format("%.2f", x - 8));
                double time = baseScene.getTime() * (x - 8) / baseScene.getDistance();
                displayedTime.set(String.format("%.2f", time));
            }
        });

        group.setOnMouseReleased(event -> {
            group.setLayoutX(initialX);
            group.setLayoutY(initialY);

            displayedHeight.set("");
            displayedRange.set("");
            displayedTime.set("");
        });
    }

    private double[] detectorTrailOverlap() {
        if (trails[0].getStroke() != Color.BLACK) {
            return null;
        }

        for (Circle circle : trails) {
            double centerX = circle.getCenterX();
            double centerY = circle.getCenterY();

            if (detectorX < centerX + 6 && detectorX > (centerX - 6) && detectorY < (centerY + 6) && detectorY > (centerY - 6)) {
                double[] output = {centerX, centerY};
                return output;
            }
        }

        return null;
    }

    public void onAnimationSpeedChanged(String selectedSpeed, Timeline timeline) {
        switch (selectedSpeed) {
            case "slow" -> timeline.setRate(0.5);
            case "normal" -> timeline.setRate(1);
            case "fast" -> timeline.setRate(2);
        }
    }

    public void onForwardPressed(double objectX, Timeline timeline) {
        for (Circle circle : trails) {
            double trailX = circle.getCenterX();

            if (objectX < trailX) {
                double time = baseScene.getTime() * (trailX - 8) / baseScene.getDistance();
                timeline.jumpTo(Duration.seconds(time));
                return;
            }
        }
    }

    public void onBackwardPressed(double objectX, Timeline timeline) {
        for (int i = 0; i < trails.length; i++) {
            double trailX = trails[i].getCenterX();

            if (objectX <= trailX) {
                double previousTrailX;

                if (i == 0) {
                    previousTrailX = trails[0].getCenterX();
                } else {
                    previousTrailX = trails[i - 1].getCenterX();
                }

                double time = baseScene.getTime() * (previousTrailX - 8) / baseScene.getDistance();
                timeline.jumpTo(Duration.seconds(time));
                return;
            }
        }
    }

    public void onRefreshButtonPressed(Stage primaryStage) {
        SimulationView simulationView = new SimulationView(primaryStage);
        Scene scene = new Scene(simulationView, MainView.stageWidth, MainView.stageHeight);
        new Util(primaryStage).switchScene(primaryStage, scene);
    }
}
