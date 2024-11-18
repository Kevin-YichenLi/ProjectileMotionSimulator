
package com.pms.project.utils;

import java.util.Optional;

import com.pms.project.views.MainView;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Util {
    private Scale globalScaleTransform = new Scale(1, 1);
    private Stage primaryStage;

    public Util(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Adjusts the global zoom level and applies it to the current scene's wrapper.
     * @param factor The zoom factor. Values > 1 zoom in, values < 1 zoom out.
     */
    public void zoom(double factor) {
        globalScaleTransform.setX(globalScaleTransform.getX() * factor);
        globalScaleTransform.setY(globalScaleTransform.getY() * factor);

        Scene currentScene = primaryStage.getScene();
        if (currentScene != null) {
            Pane root = (Pane) currentScene.getRoot();
            if (!root.getTransforms().contains(globalScaleTransform)) {
                root.getTransforms().add(globalScaleTransform);
            }
        }
    }

    /**
     * Swaps the width and height of the scene and repositions the buttons accordingly.
     * @param scaleFactor The scaling factor to adjust the buttons after swapping width and height.
     */
    public void rotate() {
        Scene currentScene = primaryStage.getScene();
        if (currentScene == null) return;

        // Get current width and height
        double currentWidth = currentScene.getWidth();
        double currentHeight = currentScene.getHeight();

        // Swap the width and height
        double newWidth = currentHeight;
        double newHeight = currentWidth;

        // Apply new width and height to the scene
        currentScene.getWindow().setWidth(newWidth);
        currentScene.getWindow().setHeight(newHeight);

        // Calculate the scale factor: ensure no zooming in occurs
        double scaleFactor = Math.min(newWidth / currentWidth, newHeight / currentHeight);

        // Apply scale factor to prevent zooming in
        scaleFactor = Math.max(1, scaleFactor); // Prevent zooming in

        // Apply scale to the scene
        applyScale(scaleFactor);

        // Reposition buttons and other elements
        Pane root = (Pane) currentScene.getRoot();
        for (Node node : root.getChildren()) {
            if (node instanceof javafx.scene.control.Button) {
                // Adjust the layout of each button based on new dimensions
                double newX = node.getLayoutY() * scaleFactor; // Swap x and y
                double newY = node.getLayoutX() * scaleFactor;

                // Ensure that buttons stay within the bounds of the scene
                double buttonWidth = node.getLayoutBounds().getWidth();
                double buttonHeight = node.getLayoutBounds().getHeight();

                newX = Math.min(newX, newWidth - buttonWidth);
                newY = Math.min(newY, newHeight - buttonHeight);

                

                // Update layout
                node.setLayoutX(newX);
                node.setLayoutY(newY);
            }
        }
    }

    /**
     * Apply scaling to fit the content properly within the new scene dimensions.
     * @param scaleFactor The scale factor to apply.
     */
    private void applyScale(double scaleFactor) {
        Scene currentScene = primaryStage.getScene();
        if (currentScene == null) return;

        Pane root = (Pane) currentScene.getRoot();

        // Apply the scaling transformation to the root
        globalScaleTransform.setX(scaleFactor);
        globalScaleTransform.setY(scaleFactor);

        if (!root.getTransforms().contains(globalScaleTransform)) {
            root.getTransforms().add(globalScaleTransform);
        }
    }

    /**
     * Switches the current scene of the primary stage and applies global transformations.
     * @param primaryStage The primary stage of the application.
     * @param scene The new scene to display.
     */
    public void switchScene(Stage primaryStage, Scene scene) {
        Pane root = (Pane) scene.getRoot();

        if (!root.getTransforms().contains(globalScaleTransform)) {
            root.getTransforms().add(globalScaleTransform);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }



    public void goBack(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to go back to the main scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            MainView mainView = new MainView(primaryStage);
            Scene scene = new Scene(mainView, MainView.stageWidth, MainView.stageHeight);
            switchScene(primaryStage, scene);
        }
    }
}
