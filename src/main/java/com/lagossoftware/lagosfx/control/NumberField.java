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

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Number field
 * 
 * Original source code from Thomas Bolz
 * http://java.dzone.com/articles/javafx-numbertextfield-and
 * 
 * @author Thomas Bolz
 * @author Cem Ikta
 */
public class NumberField extends TextField {

    private final NumberFormat numberFormat;
    private ObjectProperty<BigDecimal> value = new SimpleObjectProperty<>();

    public NumberField(NumberFormat numberFormat) {
        this(BigDecimal.ZERO, numberFormat, 100);
    }

    public NumberField(NumberFormat numberFormat, double width) {
        this(BigDecimal.ZERO, numberFormat, width);
    }

    /**
     * Number field with properties.
     *
     * @param value decimal value
     * @param numberFormat number format
     * @param width min, max and pref width
     */
    public NumberField(BigDecimal value, NumberFormat numberFormat, double width) {
        super();
        this.numberFormat = numberFormat;
        setMinWidth(width);
        setMaxWidth(width);
        setPrefWidth(width);
        initHandlers();
        setValue(value);
        setAlignment(Pos.BOTTOM_RIGHT);
    }
    
    public final BigDecimal getValue() {
        return value.get();
    }

    public final void setValue(BigDecimal value) {
        this.value.set(value);
    }

    public ObjectProperty<BigDecimal> valueProperty() {
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
        valueProperty().addListener((ObservableValue<? extends BigDecimal> observable, 
                BigDecimal oldValue, BigDecimal newValue) -> {
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
            BigDecimal newValue = new BigDecimal(parsedNumber.toString());
            setValue(newValue);
            selectAll();
        } catch (ParseException ex) {
            // If parsing fails keep old number
            setText(numberFormat.format(value.get()));
        }
    }
}
