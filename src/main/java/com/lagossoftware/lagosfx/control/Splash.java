/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Splash control
 *
 * @author Cem Ikta
 */
public class Splash extends StackPane {

    private static final String PROGRESS_BAR_STYLE = "splash-progress-bar";
    private ImageView imageView;
    private ProgressBar loadProgress;
    private Label progressText;

    public Splash(String imagePath) {
        setPrefSize(500, 340);
        imageView = new ImageView(new Image(imagePath));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(440);
        loadProgress.getStyleClass().add(PROGRESS_BAR_STYLE);
        progressText = new Label("Lade Module...");
        progressText.setPrefWidth(440);
        progressText.setAlignment(Pos.CENTER);
        progressText.setTextFill(Color.WHITE);
        VBox progressBox = new VBox(5);
        progressBox.getChildren().addAll(progressText, loadProgress);

        getChildren().addAll(imageView, progressBox);
        setAlignment(Pos.CENTER);
        StackPane.setMargin(progressBox, new Insets(250, 30, 30, 30));
        setEffect(new DropShadow());
    }

    public ProgressBar getLoadProgress() {
        return loadProgress;
    }

    public Label getProgressText() {
        return progressText;
    }
}
