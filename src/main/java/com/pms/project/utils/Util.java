package com.pms.project.utils;

import java.util.Optional;

import com.pms.project.views.BaseSceneView;
import com.pms.project.views.MainView;
import com.pms.project.views.TargetGameView;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class Util {
    private Scale globalScaleTransform = new Scale(1, 1);
    private Stage primaryStage;
	private String selectedFont;
	private String selectedBackground;
	 private boolean isRotated = false;
	 
    public Util(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    
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

   
    public void rotate() {
        Scene currentScene = primaryStage.getScene();
        if (currentScene == null) return;

        // Get current width and height
        double currentWidth = currentScene.getWidth();
        double currentHeight = currentScene.getHeight();

        if (isRotated) {
            // Return to the original state (swap back width and height)
            double newWidth = currentHeight;
            double newHeight = currentWidth;

            // Apply new width and height to the scene
            currentScene.getWindow().setWidth(newWidth);
            currentScene.getWindow().setHeight(newHeight);

            // Reset scale to normal (no zoom)
            applyScale(1); // Reset scaling to 100%

            isRotated = false; // Reset rotation state
        } else {
            // Swap width and height
            double newWidth = currentHeight;
            double newHeight = currentWidth;

            // Apply new width and height to the scene
            currentScene.getWindow().setWidth(1500);
            currentScene.getWindow().setHeight(newHeight);

            // Calculate scale factor to fit everything and make it more zoomed out
            double scaleFactor = Math.min(newWidth / currentWidth, newHeight / currentHeight);
            
            
            scaleFactor *= 1.01; 

           
            applyScale(scaleFactor);

            isRotated = true;
        }

    
        adjustLayout();
    }

   
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

    
    private void adjustLayout() {
        Scene currentScene = primaryStage.getScene();
        if (currentScene == null) return;

        Pane root = (Pane) currentScene.getRoot();
        double newWidth = currentScene.getWidth();
        double newHeight = currentScene.getHeight();

        // Adjust buttons and other layout elements
        for (Node node : root.getChildren()) {
            if (node instanceof Button) {
                // Adjust the layout of each button based on new dimensions
                double newX = node.getLayoutY();
                double newY = node.getLayoutX();

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
    
    public void previewFont(String fontClass) {
        this.selectedFont = fontClass;
      
       /* Scene currentScene = primaryStage.getScene();
        if (currentScene != null) {
            currentScene.getRoot().getStyleClass().clear();
            currentScene.getRoot().getStyleClass().add(fontClass);
        }*/
    }

    public void previewBackground(String backgroundClass) {
        this.selectedBackground = backgroundClass;
        
       /* Scene currentScene = primaryStage.getScene();
        if (currentScene != null) {
            currentScene.getRoot().getStyleClass().clear();
            currentScene.getRoot().getStyleClass().add(backgroundClass); 
        }*/
    }

    public void goToBaseScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to  the base scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            BaseSceneView baseSceneView = new BaseSceneView(primaryStage);
            Scene scene = new Scene(baseSceneView, MainView.stageWidth, MainView.stageHeight);

            // Apply the selected font and background
            scene.getRoot().getStyleClass().clear();
            scene.getRoot().getStyleClass().add(selectedFont);
            scene.getRoot().getStyleClass().add(selectedBackground);

            // Apply the theme stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

            // Set the scene for the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    
    public void goToTargetScene(Stage primaryStage) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setContentText("Do you really want to  the target scene?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            TargetGameView targetGameView = new TargetGameView(primaryStage);
            Scene scene = new Scene(targetGameView, MainView.stageWidth, MainView.stageHeight);

            // Apply the selected font and background
            scene.getRoot().getStyleClass().clear();
            scene.getRoot().getStyleClass().add(selectedFont);
            scene.getRoot().getStyleClass().add(selectedBackground);

            // Apply the theme stylesheet
            scene.getStylesheets().add(getClass().getResource("/css/theme.css").toExternalForm());

            // Set the scene for the primaryStage
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    
}