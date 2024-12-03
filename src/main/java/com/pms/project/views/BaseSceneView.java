package com.pms.project.views;

import com.pms.project.AnimationStatus;
import com.pms.project.controllers.BaseSceneController;
import com.pms.project.controllers.MainController;
import com.pms.project.models.BaseScene;
import com.pms.project.models.GeneralSetting;
import com.pms.project.utils.Util;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class BaseSceneView extends BorderPane {
    protected SimpleObjectProperty<AnimationStatus> status = new SimpleObjectProperty<>(AnimationStatus.PREPARED);
    // the value displayed with the change of the initial speed slider
    protected StringProperty speedValue = new SimpleStringProperty();
    // the instance of the model used to store data
    protected BaseScene baseScene = new BaseScene();
    protected Util util;
  
    // the Y position of "bottom" component
    protected double bottomYPosition;
    protected BaseSceneController controller;
    protected Stage primaryStage;
    protected MainController mainController;
    protected Scale scaleTransform;
    protected Rotate rotateTransform;
    protected int animationPaneHeight = 670;
    protected int animationPaneWidth = 1300;
    // path of the animation
    protected Circle[] trails = new Circle[9];
    // base of the animation object, varying depending on the initial height
    protected Rectangle base;
    protected double animationPaneLayoutX;
    protected double animationPaneLayoutY;

    public BaseSceneView(Stage primaryStage) {
        this.primaryStage = primaryStage;

        initializeBase();

        createAnimationPath();

        this.getStyleClass().add("default-font");
        controller = new BaseSceneController(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails, status);
        this.setTop(createTop());
        this.setCenter(createCenter());
        this.setBottom(createBottom());
        mainController = new MainController(primaryStage);
    }

    protected void createAnimationPath() {
        for (int i = 0; i < 9; i++) {
            Circle circle = new Circle(3, Color.TRANSPARENT);
            trails[i] = circle;
        }
    }

    protected void initializeBase() {
        base = new Rectangle();
        base.setX(0);
        base.setWidth(15);
        base.setFill(Color.TRANSPARENT);
        base.setFill(Color.LIGHTGRAY);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    protected Region createCenter() {
        Pane centerPane = new Pane();

        // Set the X position of the mass component to 0 (aligns to the left)
        Region massComponent = createMassComponent();
        massComponent.setLayoutX(0);  // X position set to 0
        massComponent.setLayoutY(450);

        // Set the X position of the gravity component to 0 (aligns to the left)
        Region gravityComponent = createGravityComponent();
        gravityComponent.widthProperty().addListener((observable, oldValue, newValue) -> 
            gravityComponent.setLayoutX(5) // X position set to 0
        );
        gravityComponent.setLayoutY(500);

        // Set the X position of the initial angle component to 0 (aligns to the left)
        Region initialAngleComponent = createInitialAngleComponent();
        initialAngleComponent.widthProperty().addListener((observable, oldValue, newValue) -> 
            initialAngleComponent.setLayoutX(0) // X position set to 0
        );
        initialAngleComponent.setLayoutY(650);

        // Set the X position of the initial speed component to 0 (aligns to the left)
        Region initialSpeedComponent = createInitialSpeedComponent();
        initialSpeedComponent.widthProperty().addListener((observable, oldValue, newValue) -> 
            initialSpeedComponent.setLayoutX(5) // X position set to 0
        );
        initialSpeedComponent.setLayoutX(5);  // X position set to 0
        initialSpeedComponent.setLayoutY(700);

        // Set the X position of the animation pane to 0 (aligns to the left)
        Region animationPane = createAnimationPane();
        animationPane.setLayoutX(150);  // X position set to 0
        animationPane.setLayoutY(120);

        animationPaneLayoutX = animationPane.getLayoutX();
        animationPaneLayoutY = animationPane.getLayoutY() + 30;

        bottomYPosition += 120;
        bottomYPosition = MainView.stageHeight - bottomYPosition;

        // Add all components to the center pane
        centerPane.getChildren().addAll(massComponent, gravityComponent, initialAngleComponent, initialSpeedComponent, animationPane);

        return centerPane;
    }

    protected Region createMassComponent() {
        VBox container = new VBox();

        Label massLabel = new Label(GeneralSetting.getString("label.mass"));
        Spinner<Double> spinner = createDoubleSpinner(100);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> controller.onMassValueChanged(newValue));

        container.getChildren().addAll(massLabel, spinner);
        return container;
    }

    protected Region createGravityComponent() {
        VBox container = new VBox();

        Label gravityLabel = new Label(GeneralSetting.getString("label.gravity"));

        Button earthButton = new Button(GeneralSetting.getString("button.earth"));
        earthButton.setOnAction(event -> controller.onEarthButtonPressed());
        earthButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button moonButton = new Button(GeneralSetting.getString("button.moon"));
        moonButton.setOnAction(event -> controller.onMoonButtonPressed());
        moonButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button marsButton = new Button(GeneralSetting.getString("button.mars"));
        marsButton.setOnAction(event -> controller.onMarsButtonPressed());
        marsButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button jupiterButton = new Button(GeneralSetting.getString("button.jupiter"));
        jupiterButton.setOnAction(event -> controller.onJupiterButtonPressed());
        jupiterButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        container.getChildren().addAll(gravityLabel, earthButton, moonButton, marsButton, jupiterButton);
        return container;
    }

    protected Region createAnimationPane() {
        Pane animationPane = new Pane();

        animationPane.setPrefSize(animationPaneWidth, animationPaneHeight);
        bottomYPosition += animationPaneHeight;

        // make overflow components invisible
        Rectangle clip = new Rectangle(animationPaneWidth, animationPaneHeight);
        animationPane.setClip(clip);

        // for testing, to be deleted
        animationPane.setBorder(Border.stroke(Color.BLACK));
        animationPane.getChildren().addAll(controller.getObject(), base);
        for (Circle circle : trails) {
            animationPane.getChildren().add(circle);
        }

        return animationPane;
    }

    protected Region createInitialAngleComponent() {
        VBox container = new VBox();

        Label initialAngleLabel = new Label(GeneralSetting.getString("label.initialAngle"));
        Spinner<Double> spinner = createDoubleSpinner(90);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));
        spinner.valueProperty().addListener(((observable, oldValue, newValue) -> controller.onInitialAngleValueChanged(newValue)));

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

        Label initialSpeedLabel = new Label(GeneralSetting.getString("label.initialSpeed"));

        Slider speedSlider = new Slider(0, 130, 20);
        speedSlider.setMajorTickUnit(1);
        speedSlider.setMinorTickCount(5);
        speedSlider.disableProperty().bind(status.isEqualTo(AnimationStatus.STOPPED).or(status.isEqualTo(AnimationStatus.PLAYED)));

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
                controller.onInitialSpeedValueChanged(newValue);
            }
        });

        container.getChildren().addAll(initialSpeedLabel, speedSlider, speedValueLabel);
        return container;
    }

    protected Region createTop() {
        scaleTransform = new Scale(1, 1);
        rotateTransform = new Rotate(0);
        HBox topBar = new HBox();

        // Buttons and Menu setup
        Button backButton = new Button(GeneralSetting.getString("button.back"));
        backButton.setOnAction(event -> controller.onBackToMainPressed());
        Button zoomInButton = new Button(GeneralSetting.getString("button.zoomIn"));
        Button zoomOutButton = new Button(GeneralSetting.getString("button.zoomOut"));
        zoomInButton.setOnAction(event -> zoom(1.1));
        zoomOutButton.setOnAction(event -> zoom(0.9));

        MenuButton settingsButton = new MenuButton(GeneralSetting.getString("menu.settings"));
        MenuItem themeMenuItem = new MenuItem(GeneralSetting.getString("menuItem.themeSetting"));
        MenuItem animationMenuItem = new MenuItem(GeneralSetting.getString("menuItem.animationSetting"));
        MenuItem generalMenuItem = new MenuItem(GeneralSetting.getString("menuItem.generalSetting"));

        themeMenuItem.setOnAction(e -> mainController.onThemeButtonPressed());
        animationMenuItem.setOnAction(e-> mainController.onAnimationButtonPressed());
        generalMenuItem.setOnAction(event -> mainController.onGeneralSettingButtonPressed());

        settingsButton.getItems().addAll(themeMenuItem, animationMenuItem, generalMenuItem);
        settingsButton.setAlignment(Pos.TOP_RIGHT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        topBar.getChildren().addAll(backButton, zoomInButton, zoomOutButton, spacer, settingsButton);
        this.getTransforms().addAll(scaleTransform);
        
        // Apply the 'top-bar' style to ensure default background color
        topBar.getStyleClass().add("top-bar");

        // Set the height of the top bar and update position
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

        // Set the background color of the bottom section to a green grass color
        hBox.setStyle("-fx-background-color: #D0E8C5;");  // Green grass color (LawnGreen)

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        hBox.getChildren().addAll(createInitialHeightComponent(), spacer, createStartAndStopButton());
        return hBox;
    }
   
    protected Region createInitialHeightComponent() {
        HBox container = new HBox(20);

        Label initialHeightLabel = new Label(GeneralSetting.getString("label.initialHeight"));
        Spinner<Double> spinner = createDoubleSpinner(300);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.STOPPED).or(status.isEqualTo(AnimationStatus.PLAYED)));

        spinner.valueProperty().addListener(((observable, oldValue, newValue) -> controller.onInitialHeightValueChanged(newValue, base)));

        container.getChildren().addAll(initialHeightLabel, spinner);
        return container;
    }

    public Region createStartAndStopButton() {
        HBox container = new HBox(20);

        Button startButton = new Button(GeneralSetting.getString("button.start"));
        startButton.setOnAction(event -> controller.onStartButtonPressed());

        Button refreshButton = new Button();
        refreshButton.setOnAction(event -> controller.onRefreshButtonPressed());
        Image refreshImage = new Image(this.getClass().getResource("/images/refresh.png").toExternalForm());
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setPreserveRatio(true);
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        refreshButton.setGraphic(refreshImageView);

        Button stopButton = new Button(GeneralSetting.getString("button.stop"));
        stopButton.setOnAction(event -> controller.onStopButtonPressed());

        container.getChildren().addAll(startButton, refreshButton, stopButton);
        return container;
    }


}
