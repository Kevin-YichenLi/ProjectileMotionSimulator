package com.pms.project.views;

import com.pms.project.controllers.MainController;
import com.pms.project.models.BaseScene;

import javafx.application.Platform;
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
    private MainController mainController;

    // When defining new scene when switching scenes, please use these two constant for width and height
    public static final double stageWidth = 1800;
    public static final double stageHeight = 1000;

    public MainView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainController = new MainController(primaryStage);
        this.setTop(createMenu());
        this.setCenter(createContent());
    }

    /**
     * create the menubar on top of the scene
     *
     * @return the menubar
     */
    private MenuBar createMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem quit = new MenuItem("Quit");
        MenuItem aboutUs = new MenuItem("About us");
        MenuItem help = new MenuItem("Help");
        Menu settings = new Menu("Settings");
        
        quit.setOnAction(e -> mainController.onQuitButtonPressed());
        aboutUs.setOnAction(e ->mainController.onAboutUsPressed());
        help.setOnAction(e -> mainController.onHelpPressed());

        MenuItem theme = new MenuItem("Theme");
        MenuItem animation = new MenuItem("Animation");
        MenuItem general = new MenuItem("General");
        
        theme.setOnAction(e -> mainController.onThemeButtonPressed());
        animation.setOnAction(e-> mainController.onAnimationButtonPressed());
        general.setOnAction(event -> mainController.onGeneralSettingButtonPressed());

        fileMenu.getItems().addAll(quit,aboutUs, help);
        
        settings.getItems().addAll(theme, animation, general);

        menuBar.getMenus().addAll(fileMenu,settings );

        return menuBar;
    }

    /**
     * create images of the scene and buttons to switch scene
     *
     * @return the VBox containing these elements
     */
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
        
        targetGameButton.setOnAction(e -> mainController.onTargetGameButtonPressed());
        simulationButton.setOnAction(event -> mainController.onSimulationButtonPressed());

        // button to base scene to test, please delete at the end
        Button test = new Button("test");
        test.setOnAction(event -> mainController.onTestButtonPressed());

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(targetGameButton, simulationButton, vectorButton, test);
        buttons.setAlignment(Pos.CENTER);

        container.getChildren().addAll(title, sceneImages, buttons);
        container.setAlignment(Pos.CENTER);
        return container;
    }

}
