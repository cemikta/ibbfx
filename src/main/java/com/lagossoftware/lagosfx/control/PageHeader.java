/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Page header control
 *
 * @author Cem Ikta
 */
public class PageHeader extends HBox {

    private Label lblTitle;

    public PageHeader(String title, String imagePath) {
        setId("page-header");
        lblTitle = new Label(title,
                ViewHelpers.createImageView(imagePath));
        lblTitle.setGraphicTextGap(10);
        lblTitle.getStyleClass().add("page-title");
        getChildren().add(lblTitle);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        getChildren().add(spacer);
        
//        setPrefHeight(66);
//        setMinHeight(66);
//        setMaxHeight(66);
    }

}
