/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.common;

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.lagosfx.control.NumberField;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * View Helpers
 *
 * @author Cem Ikta
 */
public class ViewHelpers {

    private final static Logger LOGGER = Logger.getLogger(ViewHelpers.class.getName());

    public ViewHelpers() {
    }

    /**
     * Create image from string path.
     *
     * @param imagePath image path
     * @return created image
     */
    public static Image createImage(String imagePath) {
        return new Image(IbbManagement.class.getResource(imagePath).toExternalForm());
    }

    /**
     * Create image view from string path.
     *
     * @param imagePath image path
     * @return created image view
     */
    public static ImageView createImageView(String imagePath) {
        return new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm()));
    }

    /**
     * Create icon button without title.
     *
     * @param imagePath image view path
     * @return icon button
     */
    public static Button createIconButton(String imagePath) {
        return createIconButton(imagePath, "");
    }

    /**
     * Create icon button with title.
     *
     * @param imagePath image view path
     * @param title button's titel
     * @return icon button
     */
    public static Button createIconButton(String imagePath, String title) {
        Button btn = new Button(title,
                new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        return btn;
    }

    /**
     * Create icon button with title and content position.
     *
     * @param imagePath image view path
     * @param title button's titel
     * @param contentDisplay content display
     * @return icon button
     */
    public static Button createIconButton(String imagePath, String title, ContentDisplay contentDisplay) {
        Button btn = new Button(title);
        btn.setGraphic(new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        btn.setContentDisplay(contentDisplay);

        return btn;
    }

    /**
     * Create icon toggle button without title.
     *
     * @param imagePath image view path
     * @return icon toggle button
     */
    public static ToggleButton createIconToggleButton(String imagePath) {
        return createIconToggleButton(imagePath, "");
    }

    /**
     * Create icon toggle button with title.
     *
     * @param imagePath image view path
     * @param title toggle button's titel
     * @return icon toggle button
     */
    public static ToggleButton createIconToggleButton(String imagePath, String title) {
        ToggleButton btn = new ToggleButton(title,
                new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        return btn;
    }

    /**
     * Create icon toggle button with title and content position.
     *
     * @param imagePath image view path
     * @param title button's titel
     * @param contentDisplay content display
     * @return icon toggle button
     */
    public static ToggleButton createIconToggleButton(String imagePath, String title,
            ContentDisplay contentDisplay) {

        ToggleButton btn = new ToggleButton(title);
        btn.setGraphic(new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        btn.setContentDisplay(contentDisplay);

        return btn;
    }

    /**
     * Create menu button with title and icon.
     *
     * @param imagePath image view path
     * @param title button's titel
     * @return menu button
     */
    public static MenuButton createMenuButton(String title, String imagePath) {
        MenuButton menuButton = new MenuButton(title,
                new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        return menuButton;
    }

    /**
     * Create icon hyperlink.
     *
     * @param imagePath image view path
     * @param title link's titel
     * @return hyperlink
     */
    public static Hyperlink createIconHyperlink(String title, String imagePath) {
        Hyperlink link = new Hyperlink(title,
                new ImageView(new Image(IbbManagement.class.getResource(imagePath).toExternalForm())));
        link.setMinWidth(Hyperlink.USE_PREF_SIZE);

        return link;
    }

    /**
     * Create DatePicker.
     *
     * @return date picker
     */
    public static DatePicker createDatePicker() {
        DatePicker dp = new DatePicker();
        dp.setPromptText("TT.MM.JJJJ");
        dp.setPrefWidth(150);
        dp.setShowWeekNumbers(true);

        return dp;
    }

    /**
     * Create Currencyfield.
     *
     * @return number field as currency field
     */
    public static NumberField createCurrencyField(double width, boolean editable) {
        NumberField currencyField = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        currencyField.setMinWidth(width);
        currencyField.setPrefWidth(width);
        currencyField.setMaxWidth(width);
        currencyField.setEditable(editable);

        return currencyField;
    }

    /**
     * Creates space label for GridPane.
     *
     * @return space label
     */
    public static Label createSpaceLabel() {
        Label lbl = new Label(" ");
        lbl.setMinWidth(40);
        lbl.setMaxWidth(40);
        lbl.setPrefWidth(40);

        return lbl;
    }

    public static void addStyleClasses(Node node, String... classes) {
        for (String styleClass : classes) {
            List<String> nodeClasses = node.getStyleClass();
            if (!nodeClasses.contains(styleClass)) {
                nodeClasses.add(styleClass);
            }
        }
    }

    public static void removeStyleClasses(Node node, String... classes) {
        for (String styleClass : classes) {
            List<String> nodeClasses = node.getStyleClass();
            if (nodeClasses.contains(styleClass)) {
                nodeClasses.remove(styleClass);
            }
        }
    }

}
