/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx.adresse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.BoxPanel;
import com.lagossoftware.lagosfx.control.BoxPanelType;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * Adresse view implementation
 *
 * @author Cem Ikta
 */
public class AdressePreviewViewImpl extends BoxPanel implements AdressePreviewView {

    private Label lblAnredeTitelName;
    private Label lblFirma1;
    private Label lblFirma2;
    private Label lblPostfachPlz;
    private Label lblPostfach;
    private Label lblStrasse;
    private Label lblPlzOrt;
    private Label lblLand;

    private Label lblMobiltelefon;
    private Label lblTelefonPrivat;
    private Label lblTelefonDienst;
    private Label lblFaxPrivat;
    private Label lblFaxDienst;
    private Label lblSkype;

    private Label lblEmail;
    private Label lblHomepage;
    private Label lblFacebook;
    private Label lblTwitter;
    private Label lblXing;
    private Label lblLinkedin;
    private Label lblGooglePlus;

    public AdressePreviewViewImpl() {
        super(BoxPanelType.PREVIEW);
        buildView();
    }

    private void buildView() {
        setPrefHeight(220);

        lblAnredeTitelName = new Label();
        lblFirma1 = new Label();
        lblFirma2 = new Label();
        lblPostfachPlz = new Label();
        lblPostfachPlz.setGraphic(ViewHelpers.createImageView(Images.POSTFACH_16));
        lblPostfachPlz.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.postfach")));
        lblPostfach = new Label();
        lblStrasse = new Label();
        lblStrasse.setGraphic(ViewHelpers.createImageView(Images.LOCATION_16));
        lblStrasse.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.anschrift")));
        lblPlzOrt = new Label();
        lblLand = new Label();
        
        lblMobiltelefon = new Label();
        lblMobiltelefon.setGraphic(ViewHelpers.createImageView(Images.MOBILE_16));
        lblMobiltelefon.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.mobil")));
        lblTelefonPrivat = new Label();
        lblTelefonPrivat.setGraphic(ViewHelpers.createImageView(Images.PHONE_HOME_16));
        lblTelefonPrivat.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.telefonPrivat")));
        lblTelefonDienst = new Label();
        lblTelefonDienst.setGraphic(ViewHelpers.createImageView(Images.PHONE_WORK_16));
        lblTelefonDienst.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.telefonDienst")));
        lblFaxPrivat = new Label();
        lblFaxPrivat.setGraphic(ViewHelpers.createImageView(Images.FAX_HOME_16));
        lblFaxPrivat.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.faxPrivat")));
        lblFaxDienst = new Label();
        lblFaxDienst.setGraphic(ViewHelpers.createImageView(Images.FAX_WORK_16));
        lblFaxDienst.setTooltip(new Tooltip(
                I18n.ADRESSE.getString("adresse.preview.faxDienst")));
        lblEmail = new Label();
        lblEmail.setGraphic(ViewHelpers.createImageView(Images.EMAIL_16));
        lblHomepage = new Label();
        lblHomepage.setGraphic(ViewHelpers.createImageView(Images.HOMEPAGE_16));
        
        lblSkype = new Label();
        lblSkype.setGraphic(ViewHelpers.createImageView(Images.SKYPE_16));
        lblSkype.setTooltip(new Tooltip("Skype"));
        lblFacebook = new Label();
        lblFacebook.setGraphic(ViewHelpers.createImageView(Images.FACEBOOK_16));
        lblFacebook.setTooltip(new Tooltip("Facebook"));
        lblTwitter = new Label();
        lblTwitter.setGraphic(ViewHelpers.createImageView(Images.TWITTER_16));
        lblTwitter.setTooltip(new Tooltip("Twitter"));
        lblXing = new Label();
        lblXing.setGraphic(ViewHelpers.createImageView(Images.XING_16));
        lblXing.setTooltip(new Tooltip("Xing"));
        lblLinkedin = new Label();
        lblLinkedin.setGraphic(ViewHelpers.createImageView(Images.LINKEDIN_16));
        lblLinkedin.setTooltip(new Tooltip("LinkedIn"));
        lblGooglePlus = new Label();
        lblGooglePlus.setGraphic(ViewHelpers.createImageView(Images.GOOGLE_PLUS_16));
        lblGooglePlus.setTooltip(new Tooltip("Google+"));

        GridPane previewPane = new GridPane();
        previewPane.setHgap(200);
        previewPane.setVgap(5);

        previewPane.add(lblAnredeTitelName, 0, 0);
        previewPane.add(lblFirma1, 0, 1);
        previewPane.add(lblFirma2, 0, 2);
        previewPane.add(lblStrasse, 0, 3);
        previewPane.add(lblPlzOrt, 0, 4);
        previewPane.add(lblLand, 0, 5);
        previewPane.add(lblPostfachPlz, 0, 6);
        previewPane.add(lblPostfach, 0, 7);
        
        previewPane.add(lblMobiltelefon, 1, 0);
        previewPane.add(lblTelefonPrivat, 1, 1);
        previewPane.add(lblTelefonDienst, 1, 2);
        previewPane.add(lblFaxPrivat, 1, 3);
        previewPane.add(lblFaxDienst, 1, 4);
        previewPane.add(lblSkype, 1, 5);
        
        previewPane.add(lblEmail, 2, 0);
        previewPane.add(lblHomepage, 2, 1);
        previewPane.add(lblFacebook, 2, 2);
        previewPane.add(lblTwitter, 2, 3);
        previewPane.add(lblXing, 2, 4);
        previewPane.add(lblLinkedin, 2, 5);
        previewPane.add(lblGooglePlus, 2, 6);
        
        ScrollPane sp = new ScrollPane();
        sp.setContent(previewPane);
        sp.setPrefHeight(210);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        getChildren().add(sp);
    }

    @Override
    public Label getLblAnredeTitelName() {
        return lblAnredeTitelName;
    }

    @Override
    public Label getLblFirma1() {
        return lblFirma1;
    }

    @Override
    public Label getLblFirma2() {
        return lblFirma2;
    }

    @Override
    public Label getLblPostfachPlz() {
        return lblPostfachPlz;
    }

    @Override
    public Label getLblPostfach() {
        return lblPostfach;
    }

    @Override
    public Label getLblStrasse() {
        return lblStrasse;
    }

    @Override
    public Label getLblPlzOrt() {
        return lblPlzOrt;
    }

    @Override
    public Label getLblLand() {
        return lblLand;
    }

    @Override
    public Label getLblMobiltelefon() {
        return lblMobiltelefon;
    }

    @Override
    public Label getLblTelefonPrivat() {
        return lblTelefonPrivat;
    }

    @Override
    public Label getLblTelefonDienst() {
        return lblTelefonDienst;
    }

    @Override
    public Label getLblFaxPrivat() {
        return lblFaxPrivat;
    }

    @Override
    public Label getLblFaxDienst() {
        return lblFaxDienst;
    }

    @Override
    public Label getLblEmail() {
        return lblEmail;
    }

    @Override
    public Label getLblHomepage() {
        return lblHomepage;
    }

    @Override
    public Label getLblSkype() {
        return lblSkype;
    }

    @Override
    public Label getLblFacebook() {
        return lblFacebook;
    }

    @Override
    public Label getLblTwitter() {
        return lblTwitter;
    }

    @Override
    public Label getLblXing() {
        return lblXing;
    }

    @Override
    public Label getLblLinkedin() {
        return lblLinkedin;
    }

    @Override
    public Label getLblGooglePlus() {
        return lblGooglePlus;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
