package com.pms.project.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MainView extends BorderPane {
    private Stage primaryStage;

    public MainView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setTop(createMenu());
        this.setCenter(createContent());
    }

    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();

        Menu quit = new Menu("Quit");
        Menu aboutUs = new Menu("About us");
        Menu help = new Menu("Help");
        Menu settings = new Menu("Settings");

        MenuItem theme = new MenuItem("Theme");
        MenuItem animation = new MenuItem("Animation");
        MenuItem resources = new MenuItem("Resources");

        settings.getItems().addAll(theme, animation, resources);

        menuBar.getMenus().addAll(quit, aboutUs, help, settings);

        return menuBar;
    }

    private Region createContent() {
        VBox container = new VBox(10);
        Label title = new Label("Projectile Motion Simulation");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        title.setPadding(new Insets(0, 0, 200, 0));

        Image targetGameImage = new Image(this.getClass().getResource("/images/target game image.png").toExternalForm());
        ImageView targetGameImageView = new ImageView(targetGameImage);
        targetGameImageView.setPreserveRatio(true);
        targetGameImageView.setFitHeight(150);
        targetGameImageView.setFitWidth(250);

        Image simulationImage = new Image(this.getClass().getResource("/images/simulation image.png").toExternalForm());
        ImageView simulationImageView = new ImageView(simulationImage);
        simulationImageView.setPreserveRatio(true);
        simulationImageView.setFitHeight(150);
        simulationImageView.setFitWidth(250);

        Image vectorImage = new Image(this.getClass().getResource("/images/vector image.png").toExternalForm());
        ImageView vectorImageView = new ImageView(vectorImage);
        vectorImageView.setPreserveRatio(true);
        vectorImageView.setFitHeight(150);
        vectorImageView.setFitWidth(250);

        HBox sceneImages = new HBox(20);
        sceneImages.getChildren().addAll(targetGameImageView, simulationImageView, vectorImageView);
        sceneImages.setAlignment(Pos.CENTER);

        Button targetGameButton = new Button("Target Game");
        targetGameButton.setPrefSize(150, 20);
        Button simulationButton = new Button("Projectile Motion Simulation");
        simulationButton.setPrefSize(250, 20);
        Button vectorButton = new Button("Vectors");
        vectorButton.setPrefSize(150, 20);

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(targetGameButton, simulationButton, vectorButton);
        buttons.setAlignment(Pos.CENTER);

        container.getChildren().addAll(title, sceneImages, buttons);
        container.setAlignment(Pos.CENTER);
        return container;
    }

}
