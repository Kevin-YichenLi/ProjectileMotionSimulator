package com.pms.project.views;

import com.pms.project.controllers.SimulationController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimulationView extends BaseSceneView{
    private final SimulationController simulationController = new SimulationController();

    public SimulationView(Stage primaryStage) {
        super(primaryStage);
        this.setBottom(createBottom());
        this.getChildren().add(createDetector());
    }

    private Region createAnimationSpeedControlComponent() {
        VBox container = new VBox(20);

        RadioButton slow = new RadioButton("Slow");
        RadioButton normal = new RadioButton("Normal");
        normal.setSelected(true);
        RadioButton fast = new RadioButton("Fast");

        ToggleGroup toggleGroup = new ToggleGroup();

        slow.setToggleGroup(toggleGroup);
        normal.setToggleGroup(toggleGroup);
        fast.setToggleGroup(toggleGroup);

        container.getChildren().addAll(slow, normal, fast);
        return container;
    }

    private Region createBackwardAndForwardButton() {
        VBox container = new VBox(20);

        Button forward = new Button("Forward");
        Button backward = new Button("Backward");

        container.getChildren().addAll(forward, backward);
        return container;
    }

    private Group createDetector() {
        double detectorLayoutX = 300;

        Rectangle scope = new Rectangle(200, 100);
        scope.setFill(Color.TRANSPARENT);
        scope.setStroke(Color.BLACK);

        Label timeLbl = new Label("Time:");
        Label heightLbl = new Label("Height:");
        Label rangeLbl = new Label("Range:");

        Text timeValue = new Text();
        Text heightValue = new Text();
        Text rangeValue = new Text();

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
        detector.setLayoutY(0);

        detectiveArea.setLayoutX(scope.getWidth() + radius - 3);
        detectiveArea.setLayoutY(scope.getHeight() + radius - 3);

        simulationController.enableDetectorDragAndDrop(detector, detector.getLayoutX(), detector.getLayoutY());

        return detector;
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
}
