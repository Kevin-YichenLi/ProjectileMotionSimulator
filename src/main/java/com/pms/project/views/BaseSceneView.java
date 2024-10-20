package com.pms.project.views;

import com.pms.project.controllers.BaseSceneController;
import com.pms.project.models.BaseScene;
import com.sun.jdi.Value;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BaseSceneView extends BorderPane {
    // the value displayed with the change of the initial speed slider
    private StringProperty speedValue = new SimpleStringProperty();
    // the instance of the model used to store data
    private BaseScene baseScene = new BaseScene();
    // the Y position of bottom component
    private double bottomYPosition;
    private BaseSceneController controller;
    private Stage primaryStage;
    public BaseSceneView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.setTop(createTop());
        this.setCenter(createCenter());
        this.setBottom(createBottom());
        controller = new BaseSceneController(primaryStage, baseScene);
    }

    protected Region createCenter() {
        Pane centerPane = new Pane();

        Region massComponent = createMassComponent();
        massComponent.setLayoutX(0);
        massComponent.setLayoutY(80);

        // set the absolute position of components within the center of the main stack pane
        Region gravityComponent = createGravityComponent();
        gravityComponent.widthProperty().addListener((observable, oldValue, newValue) ->
            gravityComponent.setLayoutX(MainView.stageWidth - newValue.doubleValue())
        );
        gravityComponent.setLayoutY(80);

        Region initialAngleComponent = createInitialAngleComponent();
        initialAngleComponent.widthProperty().addListener((observable, oldValue, newValue) ->
                initialAngleComponent.setLayoutX(MainView.stageWidth - newValue.doubleValue())
        );
        initialAngleComponent.setLayoutY(250);

        Region initialSpeedComponent = createInitialSpeedComponent();
        initialSpeedComponent.widthProperty().addListener((observable, oldValue, newValue) ->
                initialSpeedComponent.setLayoutX(MainView.stageWidth - newValue.doubleValue())
        );
        initialSpeedComponent.setLayoutY(400);

        Region animationPane = createAnimationPane();
        animationPane.setLayoutX(150);
        animationPane.setLayoutY(120);
        bottomYPosition += 120;
        bottomYPosition = MainView.stageHeight - bottomYPosition;

        centerPane.getChildren().addAll(massComponent, gravityComponent, initialAngleComponent, initialSpeedComponent, animationPane);

        return centerPane;
    }

    protected Region createMassComponent() {
        VBox container = new VBox();

        Label massLabel = new Label("Mass");
        Spinner<Double> spinner = createDoubleSpinner(100);
        spinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
                controller.updateMass(newValue);
            }
        });

        container.getChildren().addAll(massLabel, spinner);
        return container;
    }

    protected Region createGravityComponent() {
        VBox container = new VBox();

        Label gravityLabel = new Label("Gravity");
        Button earthButton = new Button("Earth");
        earthButton.setOnAction(event -> controller.onEarthButtonPressed());
        Button moonButton = new Button("Moon");
        moonButton.setOnAction(event -> controller.onMoonButtonPressed());
        Button marsButton = new Button("Mars");
        marsButton.setOnAction(event -> controller.onMarsButtonPressed());
        Button jupiterButton = new Button("Jupiter");
        jupiterButton.setOnAction(event -> controller.onJupiterButtonPressed());

        container.getChildren().addAll(gravityLabel, earthButton, moonButton, marsButton, jupiterButton);
        return container;
    }

    protected Region createAnimationPane() {
        Pane animationPane = new Pane();
        animationPane.setPrefSize(1300, 670);
        bottomYPosition += 670;
        // code for animation....

        // for testing, to be deleted
        animationPane.setBorder(Border.stroke(Color.BLACK));

        return animationPane;
    }

    protected Region createInitialAngleComponent() {
        VBox container = new VBox();

        Label initialAngleLabel = new Label("Initial Angle");
        Spinner<Double> spinner = createDoubleSpinner(101);
        spinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
                controller.updateInitialAngle(newValue);
            }
        });

        container.getChildren().addAll(initialAngleLabel, spinner);
        return container;
    }

    protected Spinner<Double> createDoubleSpinner(double maxValue) {
        Spinner<Double> spinner = new Spinner<>();
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0,
                maxValue, 0.0, 0.1);
        spinner.setValueFactory(valueFactory);
        spinner.setEditable(true);
        return spinner;
    }

    protected Region createInitialSpeedComponent() {
        VBox container = new VBox();

        Label initialSpeedLabel = new Label("Initial Speed");

        Slider speedSlider = new Slider(0, 500, 20);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(5);

        Label speedValueLabel = new Label();
        speedValueLabel.textProperty().bind(speedValue);

        // set the initial value for the displayed value and the data in model class
        speedValue.set(String.valueOf(speedSlider.getValue()));
        controller.updateInitialSpeed(speedSlider.getValue());

        // update the data in model class dynamically and update the displayed value dynamically
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                speedValue.set(String.format("%.2f", newValue.doubleValue()));
                controller.updateInitialSpeed(newValue.doubleValue());
            }
        });

        container.getChildren().addAll(initialSpeedLabel, speedSlider, speedValueLabel);
        return container;
    }

    protected Region createTop() {
        HBox topBar = new HBox();

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> controller.onBackToMainPressed());
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
        HBox hBox = new HBox();
        hBox.setPrefSize(MainView.stageWidth, bottomYPosition);
        hBox.setBackground(Background.fill(Color.LIGHTGREEN));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hBox.getChildren().addAll(createInitialHeightComponent(), spacer, createStartAndStopButton());
        return hBox;
    }

    protected Region createInitialHeightComponent() {
        HBox container = new HBox(20);

        Label initialHeightLabel = new Label("Initial Height");
        Spinner<Double> spinner = createDoubleSpinner(100);
        spinner.valueProperty().addListener(new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
                controller.updateInitialHeight(newValue);
            }
        });

        container.getChildren().addAll(initialHeightLabel, spinner);
        return container;
    }

    protected Region createStartAndStopButton() {
        HBox container = new HBox(20);

        Button startButton = new Button("Start");

        Button refreshButton = new Button();
        Image refreshImage = new Image(this.getClass().getResource("/images/refresh.png").toExternalForm());
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setPreserveRatio(true);
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        refreshButton.setGraphic(refreshImageView);

        Button stopButton = new Button("Stop");

        container.getChildren().addAll(startButton, refreshButton, stopButton);
        return container;
    }
}
