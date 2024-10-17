package com.pms.project.views;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BaseSceneView extends BorderPane {
    private Stage primaryStage;
    public BaseSceneView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setTop(createTop());
        this.setCenter(createCenter());
    }

    protected Region createCenter() {
        Pane centerPane = new Pane();
        centerPane.getChildren().addAll(createMassComponent(), createGravityComponent(), createInitialAngleComponent(),
                createInitialSpeedComponent(), createAnimationPane());
        return centerPane;
    }

    protected Region createMassComponent() {
        VBox container = new VBox();

        Label massLabel = new Label("Mass");
        TextField textField = new TextField();
        textField.setPromptText("Enter mass here");

        container.getChildren().addAll(massLabel, textField);
        return container;
    }

    protected Region createGravityComponent() {
        VBox container = new VBox();

        Label gravityLabel = new Label("Gravity");
        Button earthButton = new Button("Earth");
        Button moonButton = new Button("Moon");
        Button marsButton = new Button("Mars");
        Button jupiterButton = new Button("Jupiter");

        container.getChildren().addAll(gravityLabel, earthButton, moonButton, marsButton, jupiterButton);
        return container;
    }

    protected Region createAnimationPane() {
        Pane animationPane = new Pane();
        animationPane.setPrefSize(1000, 700);
        // code ....

        // for testing, to be deleted
        animationPane.setBorder(Border.stroke(Color.BLACK));

        return animationPane;
    }

    protected Region createInitialAngleComponent() {
        VBox container = new VBox();

        Label initialAngleLabel = new Label("Initial Angle");
        TextField initialAngleTextField = new TextField();
        initialAngleTextField.setPromptText("Enter initial angle here");

        container.getChildren().addAll(initialAngleLabel, initialAngleTextField);
        return container;
    }

    protected Region createInitialSpeedComponent() {
        VBox container = new VBox();

        Label initialSpeedLabel = new Label("Initial Speed");
        Slider speedSlider = new Slider(0, 500, 20);
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(5);

        container.getChildren().addAll(initialSpeedLabel, speedSlider);
        return container;
    }

    protected Region createTop() {
        HBox topBar = new HBox();

        Button backButton = new Button("Back to Main");
        Button zoomInButton = new Button("Zoom in");
        Button zoomOutButton = new Button("Zoom out");

        MenuButton settingsButton = new MenuButton("Settings");
        MenuItem themeMenuItem = new MenuItem("Theme");
        MenuItem animationMenuItem = new MenuItem("Animation");
        MenuItem generalMenuItem = new MenuItem("General");

        settingsButton.getItems().addAll(themeMenuItem, animationMenuItem, generalMenuItem);
        settingsButton.setAlignment(Pos.TOP_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(backButton, zoomInButton, zoomOutButton, spacer, settingsButton);

        // this line is for testing only, please delete at the end
        topBar.setBorder(Border.stroke(Color.BLACK));
        return topBar;
    }

    protected Region createBottom() {
        return null;
    }
}
