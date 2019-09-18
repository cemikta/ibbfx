/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;

/**
 * Application Header Bar
 *
 * @author Cem Ikta
 */
public class AppHeaderBar extends HBox {

    private Label lblTitle = new Label();
    private StringProperty titleText = new SimpleStringProperty("Home");

    public AppHeaderBar() {
        getStyleClass().addAll("app-header-bar");

        lblTitle.getStyleClass().add("title");
        lblTitle.setManaged(false);
        lblTitle.textProperty().bind(titleText);
        getChildren().add(lblTitle);

        Pane spacer = new Pane();
        setHgrow(spacer, Priority.ALWAYS);
        getChildren().add(spacer);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        final double width = getWidth();
        final double height = getHeight();
        lblTitle.setPrefWidth(300);
        final double titleWidth = lblTitle.getPrefWidth();
        double leftItemsWidth = getPadding().getLeft();

        for (Node item : getChildren()) {
            if (item == lblTitle) {
                break;
            }
            leftItemsWidth += item.getLayoutBounds().getWidth();
            Insets margins = getMargin(item);
            if (margins != null) {
                leftItemsWidth += margins.getLeft() + margins.getRight();
            }
            leftItemsWidth += getSpacing();
        }
        if ((leftItemsWidth + (titleWidth / 2)) < (width / 2)) {
            lblTitle.setVisible(true);
            layoutInArea(lblTitle, 0, 0, getWidth(), height, 0, HPos.CENTER, VPos.CENTER);
        } else {
            lblTitle.setVisible(false);
        }
    }

    public void addLeftItems(Node... items) {
        getChildren().addAll(0, Arrays.asList(items));
    }

    public void addRightItems(Node... items) {
        getChildren().addAll(items);
    }

    public StringProperty titleTextProperty() {
        return titleText;
    }

    public String getTitleText() {
        return titleText.get();
    }

    public void setTitleText(String text) {
        titleText.set(text);
    }

}
