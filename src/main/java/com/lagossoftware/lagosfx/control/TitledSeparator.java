/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Titled Separator control
 *
 * @author Cem Ikta
 */
public class TitledSeparator extends HBox {

    private Label lblTitle;

    /**
     * Creates titled separator.
     * 
     * @param title control's title
     */
    public TitledSeparator(String title) {
        this.getStyleClass().add("titled-separator");
        setAlignment(Pos.CENTER_LEFT);
        
        lblTitle = new Label(title);
        lblTitle.getStyleClass().add("label");
        Separator separator = new Separator();
        separator.getStyleClass().add("line");
        HBox.setHgrow(separator, Priority.ALWAYS);
        
        getChildren().addAll(lblTitle, separator);
    }

    public StringProperty textProperty() {
        return lblTitle.textProperty();
    }
    
    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }
    
}