package com.pms.project.views;

import com.pms.project.AnimationStatus;
import com.pms.project.controllers.TargetGameController;
import com.pms.project.models.TargetGame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TargetGameView extends BaseSceneView {
    protected TargetGameController targetGameController;
    private final Button newTargetButton;

    public TargetGameView(Stage primaryStage) {

        super(primaryStage);
        targetGameController = new TargetGameController(primaryStage, baseScene, animationPaneWidth, animationPaneHeight, trails, status);

        TargetGame targetGame = targetGameController.getTargetGame();
        Circle target = targetGame.getTarget();

        // Set initial position of the target
        target.setCenterX(targetGame.getTargetX());
        target.setCenterY(targetGame.getTargetY());

        // Create the button for a new target
        newTargetButton = new Button("New Target");
        newTargetButton.setOnAction(event -> targetGameController.onNewTargetButtonPressed());


        ((Pane) this.getCenter()).getChildren().addAll(target, newTargetButton);

        targetGame.getTarget().setLayoutX(200);
        targetGame.getTarget().setLayoutY(200);

        newTargetButton.setLayoutX(5);
        newTargetButton.setLayoutY(350);
        newTargetButton.setStyle("-fx-background-color: #f4cccc; -fx-text-fill: black; -fx-border-color: #b45454;");


    }


    public Region createStartAndStopButton() {
        HBox container = new HBox(20);

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> targetGameController.onStartButtonPressed());

        Button refreshButton = new Button();
        refreshButton.setOnAction(event -> targetGameController.onRefreshButtonPressed());
        Image refreshImage = new Image(this.getClass().getResource("/images/refresh.png").toExternalForm());
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setPreserveRatio(true);
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        refreshButton.setGraphic(refreshImageView);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> targetGameController.onStopButtonPressed());

        container.getChildren().addAll(startButton, refreshButton, stopButton);
        return container;
    }

    @Override
    protected Region createMassComponent() {
        VBox container = new VBox();

        Label massLabel = new Label("Mass");
        Spinner<Double> spinner = createDoubleSpinner(100);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));
        spinner.valueProperty().addListener((observable, oldValue, newValue) -> targetGameController.onMassValueChanged(newValue));

        container.getChildren().addAll(massLabel, spinner);
        return container;
    }

    @Override
    protected Region createGravityComponent() {
        VBox container = new VBox();

        Label gravityLabel = new Label("Gravity");

        Button earthButton = new Button("Earth");
        earthButton.setOnAction(event -> targetGameController.onEarthButtonPressed());
        earthButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button moonButton = new Button("Moon");
        moonButton.setOnAction(event -> targetGameController.onMoonButtonPressed());
        moonButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button marsButton = new Button("Mars");
        marsButton.setOnAction(event -> targetGameController.onMarsButtonPressed());
        marsButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        Button jupiterButton = new Button("Jupiter");
        jupiterButton.setOnAction(event -> targetGameController.onJupiterButtonPressed());
        jupiterButton.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));

        container.getChildren().addAll(gravityLabel, earthButton, moonButton, marsButton, jupiterButton);
        return container;
    }

    @Override
    protected Region createInitialAngleComponent() {
        VBox container = new VBox();

        Label initialAngleLabel = new Label("Initial Angle");
        Spinner<Double> spinner = createDoubleSpinner(101);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.PLAYED).or(status.isEqualTo(AnimationStatus.STOPPED)));
        spinner.valueProperty().addListener(((observable, oldValue, newValue) -> targetGameController.onInitialAngleValueChanged(newValue)));

        container.getChildren().addAll(initialAngleLabel, spinner);
        return container;
    }

    @Override
    protected Region createInitialSpeedComponent() {
        VBox container = new VBox();

        Label initialSpeedLabel = new Label("Initial Speed");

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
                targetGameController.onInitialSpeedValueChanged(newValue);
            }
        });

        container.getChildren().addAll(initialSpeedLabel, speedSlider, speedValueLabel);
        return container;
    }

    @Override
    protected Region createInitialHeightComponent() {
        HBox container = new HBox(20);

        Label initialHeightLabel = new Label("Initial Height");
        Spinner<Double> spinner = createDoubleSpinner(100);
        spinner.disableProperty().bind(status.isEqualTo(AnimationStatus.STOPPED).or(status.isEqualTo(AnimationStatus.PLAYED)));

        spinner.valueProperty().addListener(((observable, oldValue, newValue) -> targetGameController.onInitialHeightValueChanged(newValue, base)));

        container.getChildren().addAll(initialHeightLabel, spinner);
        return container;
    }

    @Override
    protected Region createAnimationPane() {
        Pane animationPane = new Pane();

        animationPane.setPrefSize(animationPaneWidth, animationPaneHeight);
        bottomYPosition += animationPaneHeight;

        // make overflow components invisible
        Rectangle clip = new Rectangle(animationPaneWidth, animationPaneHeight);
        animationPane.setClip(clip);

        // for testing, to be deleted
        animationPane.setBorder(Border.stroke(Color.BLACK));
        animationPane.getChildren().addAll(targetGameController.getObject(), base);
        for (Circle circle : trails) {
            animationPane.getChildren().add(circle);
        }

        return animationPane;
    }
}
