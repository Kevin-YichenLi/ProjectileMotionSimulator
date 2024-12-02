package com.pms.project.views;

import com.pms.project.AnimationStatus;
import com.pms.project.controllers.SimulationController;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimulationView extends BaseSceneView{
    private SimpleStringProperty displayedTime = new SimpleStringProperty();
    private SimpleStringProperty displayedHeight = new SimpleStringProperty();
    private SimpleStringProperty displayedRange = new SimpleStringProperty();
    private SimulationController simulationController;

    public SimulationView(Stage primaryStage) {
        super(primaryStage);
        this.setBottom(createBottom());
        this.getChildren().add(createDetector());
    }

    private Region createAnimationSpeedControlComponent() {
        VBox container = new VBox(20);

        Button slow = new Button("Slow");
        Button normal = new Button("Normal");
       
        Button fast = new Button("Fast");

        // Add event handlers to simulate toggle behavior
        slow.setOnAction(event -> onSpeedButtonClicked(slow, "slow"));
        normal.setOnAction(event -> onSpeedButtonClicked(normal, "normal"));
        fast.setOnAction(event -> onSpeedButtonClicked(fast, "fast"));

        container.getChildren().addAll(slow, normal, fast);
        return container;
    }

    private void onSpeedButtonClicked(Button selectedButton, String speed) {
      
        // Notify the controller with the selected speed
        simulationController.onAnimationSpeedChanged(speed, controller.getTimeline());
    }

    private Region createBackwardAndForwardButton() {
        VBox container = new VBox(20);

        Button forward = new Button("Forward");
        forward.setOnAction(event -> simulationController.onForwardPressed(controller.getObject().getTranslateX(), controller.getTimeline()));
        Button backward = new Button("Backward");
        backward.setOnAction(event -> simulationController.onBackwardPressed(controller.getObject().getTranslateX(), controller.getTimeline()));

        backward.disableProperty().bind(status.isEqualTo(AnimationStatus.PREPARED).or(status.isEqualTo(AnimationStatus.FINISHED)));
        forward.disableProperty().bind(status.isEqualTo(AnimationStatus.PREPARED).or(status.isEqualTo(AnimationStatus.FINISHED)));

        container.getChildren().addAll(forward, backward);
        return container;
    }

    private Group createDetector() {
        double detectorLayoutX = 0;

        Rectangle scope = new Rectangle(100, 75);
        scope.setFill(Color.TRANSPARENT);
        scope.setStroke(Color.BLACK);

        Label timeLbl = new Label("Time:");
        Label heightLbl = new Label("Height:");
        Label rangeLbl = new Label("Range:");

        Text timeValue = new Text();
        timeValue.textProperty().bind(displayedTime);
        Text heightValue = new Text();
        heightValue.textProperty().bind(displayedHeight);
        Text rangeValue = new Text();
        rangeValue.textProperty().bind(displayedRange);

        VBox container = new VBox(10, new HBox(10, timeLbl, timeValue),
                new HBox(10, heightLbl, heightValue), new HBox(10, rangeLbl, rangeValue));

        // a circle with a cross inside
        double radius = 10;
        Path detectiveArea = new Path(
                new MoveTo(radius, 0),
                new ArcTo(radius, radius, 0, -radius, 0, true, true),
                new ArcTo(radius, radius, 0, radius, 0, true, true),
                new MoveTo(-radius, 0),
                new LineTo(radius, 0),
                new MoveTo(0, -radius),
                new LineTo(0, radius)
        );
        detectiveArea.setFill(Color.TRANSPARENT);
        detectiveArea.setStroke(Color.BLACK);

        Group detector = new Group(scope, container, detectiveArea);
        detector.setLayoutX(detectorLayoutX);
        detector.setLayoutY(40);

        // the coordinate for the center of the circle relative to top left corner of the detector
        double relativeX = scope.getWidth() + radius - 3;
        double relativeY = scope.getHeight() + radius - 3;

        detectiveArea.setLayoutX(relativeX);
        detectiveArea.setLayoutY(relativeY);

        double centerX = detector.getLayoutX() + relativeX;
        double centerY = detector.getLayoutY() + relativeY;

        createSimulationController(centerX, centerY, relativeX, relativeY);
        simulationController.enableDetectorDragAndDrop(detector, detector.getLayoutX(), detector.getLayoutY(),
                displayedTime, displayedHeight, displayedRange, animationPaneLayoutX, animationPaneLayoutY);

        return detector;
    }

    private void createSimulationController(double detectorPointX, double detectorPointY, double relativeX, double relativeY) {
        simulationController = new SimulationController(trails, detectorPointX, detectorPointY, relativeX, relativeY, baseScene, animationPaneWidth, animationPaneHeight);
    }

    @Override
    protected Region createBottom() {
        HBox hBox = new HBox(50);
        hBox.setPrefSize(MainView.stageWidth, bottomYPosition);

        // Set the background color of the bottom section to a green grass color
        hBox.setStyle("-fx-background-color: #D0E8C5;");  // Green grass color (LawnGreen)

        hBox.setBorder(Border.stroke(Color.BLACK));

        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        hBox.getChildren().addAll(createInitialHeightComponent(), spacer1, createAnimationSpeedControlComponent(),
                createBackwardAndForwardButton(), spacer2, createStartAndStopButton());
        return hBox;
    }

    @Override
    public Region createStartAndStopButton() {
        HBox container = new HBox(20);

        Button startButton = new Button("Start");
        startButton.setOnAction(event -> controller.onStartButtonPressed());

        Button refreshButton = new Button();
        refreshButton.setOnAction(event -> simulationController.onRefreshButtonPressed(primaryStage));
        Image refreshImage = new Image(this.getClass().getResource("/images/refresh.png").toExternalForm());
        ImageView refreshImageView = new ImageView(refreshImage);
        refreshImageView.setPreserveRatio(true);
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        refreshButton.setGraphic(refreshImageView);

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(event -> controller.onStopButtonPressed());

        container.getChildren().addAll(startButton, refreshButton, stopButton);
        return container;
    }
}
