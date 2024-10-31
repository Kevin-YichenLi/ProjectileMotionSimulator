package com.pms.project.views;

import java.util.ArrayList;
import java.util.List;

import com.pms.project.controllers.BaseSceneController;
import com.pms.project.controllers.MainController;
import com.pms.project.models.BaseScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class BaseSceneView extends BorderPane {
    // the value displayed with the change of the initial speed slider
    private StringProperty speedValue = new SimpleStringProperty();
    // the instance of the model used to store data
    private BaseScene baseScene = new BaseScene();
    // the Y position of "bottom" component
    private double bottomYPosition;
    private BaseSceneController controller;
    private Stage primaryStage;
    private MainController mainController;
    private Scale scaleTransform;
    private Rotate rotateTransform;
    private int animationPaneHeight = 670;
    private int animationPaneWidth = 1300;

    public BaseSceneView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        controller = new BaseSceneController(primaryStage, baseScene, animationPaneWidth, animationPaneHeight);
        this.setTop(createTop());
        this.setCenter(createCenter());
        this.setBottom(createBottom());
        mainController = new MainController(primaryStage);
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

        animationPane.setPrefSize(animationPaneWidth, animationPaneHeight);
        bottomYPosition += animationPaneHeight;

        // for testing, to be deleted
        animationPane.setBorder(Border.stroke(Color.BLACK));
        animationPane.getChildren().addAll(controller.getObject());

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
        //controller.updateInitialSpeed(speedSlider.getValue());

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
        scaleTransform = new Scale(1, 1);
        rotateTransform = new Rotate(0);
        HBox topBar = new HBox();

        Button backButton = new Button("Back to Main");
        backButton.setOnAction(event -> controller.onBackToMainPressed());
        Button zoomInButton = new Button("Zoom in");
        Button zoomOutButton = new Button("Zoom out");
        zoomInButton.setOnAction(event -> zoom(1.1));
        zoomOutButton.setOnAction(event -> zoom(0.9));

        MenuButton settingsButton = new MenuButton("Settings");
        MenuItem themeMenuItem = new MenuItem("Theme");
        MenuItem animationMenuItem = new MenuItem("Animation");
        MenuItem generalMenuItem = new MenuItem("General");
        
        themeMenuItem.setOnAction(e -> mainController.onThemeButtonPressed());
        animationMenuItem.setOnAction(e-> mainController.onAnimationButtonPressed());
        settingsButton.getItems().addAll(themeMenuItem, animationMenuItem, generalMenuItem);
        settingsButton.setAlignment(Pos.TOP_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(backButton, zoomInButton, zoomOutButton, spacer, settingsButton);
        this.getTransforms().addAll(scaleTransform);
        // this line is for testing only, please delete at the end
        topBar.setBorder(Border.stroke(Color.BLACK));

        double prefHeight = 27;
        topBar.setPrefHeight(prefHeight);
        bottomYPosition += prefHeight;

        return topBar;
    }

    protected void zoom(double factor) {
        scaleTransform.setX(scaleTransform.getX() * factor);
        scaleTransform.setY(scaleTransform.getY() * factor);
    }

    protected Region createBottom() {
        HBox hBox = new HBox();
        hBox.setPrefSize(MainView.stageWidth, bottomYPosition);
        hBox.setBorder(Border.stroke(Color.BLACK));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hBox.getChildren().addAll(createInitialHeightComponent(), spacer, createStartAndStopButton());
        return hBox ;
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
        startButton.setOnAction(event -> controller.onStartButtonPressed());

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

    public List<Label> getLabels() {
        List<Label> labels = new ArrayList<>();
        for (Node node : this.getChildren()) {
            if (node instanceof VBox) {
                for (Node child : ((VBox) node).getChildren()) {
                    if (child instanceof Label) {
                        labels.add((Label) child);
                    }
                }
            }
        }
        return labels;
    }
}
