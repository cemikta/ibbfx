/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Integer field
 * 
 * original source code from Thomas Bolz
 * http://java.dzone.com/articles/javafx-numbertextfield-and
 * 
 * @author Thomas Bolz
 * @author Cem Ikta
 */
public class IntegerField extends TextField {

    private final NumberFormat numberFormat;
    private ObjectProperty<Integer> value = new SimpleObjectProperty<>();

    public IntegerField() {
        this(0, 100);
    }

    public IntegerField(double width) {
        this(0, width);
    }

    public IntegerField(Integer value) {
        this(0, 100);
    }

    /**
     * Integer field with value and width.
     *
     * @param value integer value
     * @param width min, max and pref width
     */
    public IntegerField(Integer value, double width) {
        super();
        setMinWidth(width);
        setMaxWidth(width);
        setPrefWidth(width);
        numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setGroupingUsed(false);
        initHandlers();
        setValue(value);
        setAlignment(Pos.BOTTOM_RIGHT);
    }
    
    public final Integer getValue() {
        return value.get();
    }

    public final void setValue(Integer value) {
        this.value.set(value);
    }

    public ObjectProperty<Integer> valueProperty() {
        return value;
    }

    private void initHandlers() {
        // try to parse when focus is lost or RETURN is hit
        setOnAction((ActionEvent arg0) -> {
            parseAndFormatInput();
        });

        focusedProperty().addListener((ObservableValue<? extends Boolean> observable, 
                Boolean oldValue, Boolean newValue) -> {
            if (!newValue) {
                parseAndFormatInput();
            }
        });

        // Set text in field if BigDecimal property is changed from outside.
        valueProperty().addListener((ObservableValue<? extends Integer> observable, 
                Integer oldValue, Integer newValue) -> {
            setText(numberFormat.format(newValue));
        });
    }

    /**
     * Tries to parse the user input to a number according to the provided
     * NumberFormat
     */
    private void parseAndFormatInput() {
        try {
            String input = getText();
            if (input == null || input.length() == 0) {
                return;
            }
            Number parsedNumber = numberFormat.parse(input);
            Integer newValue = new Integer(parsedNumber.toString());
            setValue(newValue);
            selectAll();
        } catch (ParseException ex) {
            // If parsing fails keep old number
            setText(numberFormat.format(value.get()));
        }
    }
}
