/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.control;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.FahrtkasseDetail;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextFlow;
import org.apache.poi.hssf.usermodel.HeaderFooter;

import java.util.Arrays;
import java.util.Optional;

/**
 * Application Abaout dialog
 *
 * @author Cem Ikta
 */
public class AboutDialog extends Dialog<Void> {

    public AboutDialog() {
        buildView();
    }

    private void buildView() {
        setTitle(I18n.COMMON.getString("aboutDialog.title"));
        setHeaderText(I18n.COMMON.getString("aboutDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.APP_ABOUT_32));

        TabPane tabPanePages = new TabPane(buildAboutTab(), buildCreditTab());
        tabPanePages.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPanePages.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING);
        tabPanePages.setPrefSize(500, 300);
        tabPanePages.setMinSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPanePages.setMaxSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);

        ButtonType btnTypeClose = new ButtonType(I18n.COMMON.getString("action.close"),
                ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().add(btnTypeClose);
        getDialogPane().setContent(tabPanePages);
    }

    private Tab buildAboutTab() {
        Tab aboutTab = new Tab();
        aboutTab.setText(I18n.COMMON.getString("aboutDialog.aboutPage"));

        Label lblAppTitle = new Label(I18n.COMMON.getString("app.title"));
        lblAppTitle.setStyle("-fx-font-weight: bold;");
        Label lblCompany = new Label(I18n.COMMON.getString("aboutDialog.company"));
        Label lblCommunication = new Label(I18n.COMMON.getString("aboutDialog.communication"));

        VBox aboutBox = new VBox(10);
        aboutBox.setPadding(new Insets(20, 20, 10, 20));
        aboutBox.getChildren().addAll(lblAppTitle, lblCompany, lblCommunication);
        aboutTab.setContent(aboutBox);

        return aboutTab;
    }

    private Tab buildCreditTab() {
        Tab creditTab = new Tab();
        creditTab.setText(I18n.COMMON.getString("aboutDialog.creditPage"));

        Label lblAppIcons = new Label(I18n.COMMON.getString("aboutDialog.appIcons"));
        lblAppIcons.setStyle("-fx-font-weight: bold;");
        Label lblIcons8 = new Label(I18n.COMMON.getString("aboutDialog.icons8"));
        Label lblIconsdb = new Label(I18n.COMMON.getString("aboutDialog.iconsdb"));

        VBox creditBox = new VBox(10);
        creditBox.setPadding(new Insets(20, 20, 10, 20));
        creditBox.getChildren().addAll(lblAppIcons, lblIcons8, lblIconsdb);
        creditTab.setContent(creditBox);

        return creditTab;
    }

    public static Optional<Void> showDialog() {
        AboutDialog dialog = new AboutDialog();
        return dialog.showAndWait();
    }

}
