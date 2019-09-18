/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

/**
 * Button Bar Pane control
 *
 * @author Cem Ikta
 */
public class ButtonBarPane extends BorderPane {

    private Orientation orientation;
    private ToolBar buttonBar;
    private Node currentPagePane;

    public ButtonBarPane() {
        this(Orientation.VERTICAL);
    }

    public ButtonBarPane(Orientation orientation) {
        this.orientation = orientation;
        buildView();
    }

    private void buildView() {
        setId("button-bar-pane");
        
        buttonBar = new ToolBar();
        buttonBar.setId("button-bar-pane-toolbar");
        buttonBar.setOrientation(orientation);

        if (orientation == Orientation.VERTICAL) {
            setLeft(buttonBar);
        } else {
            setTop(buttonBar);
        }
    }

    /**
     * Adds new page to button bar pane.
     * 
     * @param imagePath page image path 
     * @param title page title
     * @param pagePane page node
     */
    public void addPage(String imagePath, String title, Node pagePane) {
        addPage(imagePath, title, pagePane, ContentDisplay.TOP);
    }

    /**
     * Adds new page to button bar pane.
     *
     * @param imagePath page image path
     * @param title page title
     * @param pagePane page node
     * @param contentDisplay content display
     */
    public void addPage(String imagePath, String title, Node pagePane, ContentDisplay contentDisplay) {
        Button btn = ViewHelpers.createIconButton(imagePath, title, contentDisplay);
        btn.setMaxWidth(Double.MAX_VALUE);
        if (contentDisplay == ContentDisplay.LEFT) {
            // icon on the left side, title on the right side.
            btn.getStyleClass().add("medium");
            btn.setAlignment(Pos.BASELINE_LEFT);
        } else {
            // icon on the top side, title on the bottom side and centered alignment
            btn.getStyleClass().add("large");
        }
        btn.setOnAction((ActionEvent event) -> {
            setSelectedButton(btn);
            showPage(pagePane);
        });
        buttonBar.getItems().add(btn);

        if (buttonBar.getItems().size() == 1) {
            setSelectedButton(btn);
            showPage(pagePane);
        }
    }

    private void showPage(Node pagePane) {
        if (currentPagePane != null) {
            setCenter(null);
        }
        this.currentPagePane = pagePane;
        setCenter(pagePane);
    }

    private void setSelectedButton(Button btnSelected) {
        buttonBar.getItems().stream().forEach((node) -> {
            ViewHelpers.removeStyleClasses(node, "selected");
        });
        
        btnSelected.getStyleClass().add("selected");
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
