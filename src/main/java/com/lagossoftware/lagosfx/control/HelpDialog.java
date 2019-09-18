/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Optional;

/**
 * Application Help dialog
 *
 * @author Cem Ikta
 */
public class HelpDialog extends Dialog<Void> {

    public HelpDialog() {
        buildView();
    }

    private void buildView() {
        setTitle(I18n.COMMON.getString("helpDialog.title"));
        setHeaderText(I18n.COMMON.getString("helpDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.APP_HELP_32));
        ButtonType btnTypeClose = new ButtonType(I18n.COMMON.getString("action.close"),
                ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(btnTypeClose);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 50, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Strg + H"), 0, 0);
        gridPane.add(new Label("Dashboard"), 1, 0);

        getDialogPane().setContent(gridPane);
    }

    public static Optional<Void> showDialog() {
        HelpDialog dialog = new HelpDialog();
        return dialog.showAndWait();
    }

}
