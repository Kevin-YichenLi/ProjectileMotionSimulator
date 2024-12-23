package com.pms.project.views;

import com.pms.project.controllers.MainController;
import com.pms.project.models.BaseScene;

import com.pms.project.models.GeneralSetting;
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
        Menu fileMenu = new Menu(GeneralSetting.getString("menu.file"));
        MenuItem quit = new MenuItem(GeneralSetting.getString("menuItem.quit"));
        MenuItem aboutUs = new MenuItem(GeneralSetting.getString("menuItem.aboutUs"));
        MenuItem help = new MenuItem(GeneralSetting.getString("menuItem.help"));
        Menu settings = new Menu(GeneralSetting.getString("menu.settings"));
        
        quit.setOnAction(e -> mainController.onQuitButtonPressed());
        aboutUs.setOnAction(e ->mainController.onAboutUsPressed());
        help.setOnAction(e -> mainController.onHelpPressed());

        MenuItem theme = new MenuItem(GeneralSetting.getString("menuItem.themeSetting"));
        MenuItem animation = new MenuItem(GeneralSetting.getString("menuItem.animationSetting"));
        MenuItem general = new MenuItem(GeneralSetting.getString("menuItem.generalSetting"));
        
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
        Label title = new Label(GeneralSetting.getString("label.title"));
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

        Button targetGameButton = new Button(GeneralSetting.getString("button.targetGame"));
        targetGameButton.setPrefSize(150, 20);
        Button simulationButton = new Button(GeneralSetting.getString("button.simulation"));
        simulationButton.setPrefSize(250, 20);
        Button vectorButton = new Button(GeneralSetting.getString("button.vector"));
        vectorButton.setPrefSize(150, 20);
        
        vectorButton.setOnAction(e -> mainController.onVectorButtonPressed());
        targetGameButton.setOnAction(e -> mainController.onTargetGameButtonPressed());
        simulationButton.setOnAction(event -> mainController.onSimulationButtonPressed());

        HBox buttons = new HBox(20);
        buttons.getChildren().addAll(targetGameButton, simulationButton, vectorButton);
        buttons.setAlignment(Pos.CENTER);

        container.getChildren().addAll(title, sceneImages, buttons);
        container.setAlignment(Pos.CENTER);
        return container;
    }

}
