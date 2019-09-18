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
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Adresse Kommunikation view implementation
 *
 * @author Cem Ikta
 */
public class AdresseKommunikationViewImpl extends GridPane implements AdresseKommunikationView {

    private TextField tfMobiltelefon;
    private TextField tfTelefonPrivat;
    private TextField tfTelefonDienst;
    private TextField tfFaxPrivat;
    private TextField tfFaxDienst;
    private TextField tfEmail;
    private TextField tfHomepage;

    private TextField tfSkype;
    private TextField tfFacebook;
    private TextField tfTwitter;
    private TextField tfXing;
    private TextField tfLinkedIn;
    private TextField tfGooglePlus;

    public AdresseKommunikationViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        tfMobiltelefon = new TextField();
        tfMobiltelefon.setPrefWidth(300);
        tfTelefonPrivat = new TextField();
        tfTelefonPrivat.setPrefWidth(300);
        tfTelefonDienst = new TextField();
        tfTelefonDienst.setPrefWidth(300);
        tfFaxPrivat = new TextField();
        tfFaxPrivat.setPrefWidth(300);
        tfFaxDienst = new TextField();
        tfFaxDienst.setPrefWidth(300);
        tfEmail = new TextField();
        tfEmail.setPrefWidth(300);
        tfHomepage = new TextField();
        tfHomepage.setPrefWidth(300);

        tfSkype = new TextField();
        tfSkype.setPrefWidth(300);
        tfFacebook = new TextField();
        tfFacebook.setPrefWidth(300);
        tfTwitter = new TextField();
        tfTwitter.setPrefWidth(300);
        tfXing = new TextField();
        tfXing.setPrefWidth(300);
        tfLinkedIn = new TextField();
        tfLinkedIn.setPrefWidth(300);
        tfGooglePlus = new TextField();
        tfGooglePlus.setPrefWidth(300);
        
        add(new Label(I18n.ADRESSE.getString("adresse.form.mobiltelefon")), 0, 0);
        add(tfMobiltelefon, 1, 0);
        add(new Label(I18n.ADRESSE.getString("adresse.form.telefonPrivat")), 0, 1);
        add(tfTelefonPrivat, 1, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.telefonDienst")), 0, 2);
        add(tfTelefonDienst, 1, 2);
        add(new Label(I18n.ADRESSE.getString("adresse.form.faxPrivat")), 0, 3);
        add(tfFaxPrivat, 1, 3);
        add(new Label(I18n.ADRESSE.getString("adresse.form.faxDienst")), 0, 4);
        add(tfFaxDienst, 1, 4);
        add(new Label(I18n.ADRESSE.getString("adresse.form.email")), 0, 5);
        add(tfEmail, 1, 5);
        add(new Label(I18n.ADRESSE.getString("adresse.form.homepage")), 0, 6);
        add(tfHomepage, 1, 6);
        
        add(new TitledSeparator(I18n.ADRESSE.getString("adresse.form.socialmedia")), 0, 7, 3, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.skype")), 0, 8);
        add(tfSkype, 1, 8);
        add(new Label(I18n.ADRESSE.getString("adresse.form.facebook")), 0, 9);
        add(tfFacebook, 1, 9);
        add(new Label(I18n.ADRESSE.getString("adresse.form.twitter")), 0, 10);
        add(tfTwitter, 1, 10);
        add(new Label(I18n.ADRESSE.getString("adresse.form.xing")), 0, 11);
        add(tfXing, 1, 11);
        add(new Label(I18n.ADRESSE.getString("adresse.form.linkedIn")), 0, 12);
        add(tfLinkedIn, 1, 12);
        add(new Label(I18n.ADRESSE.getString("adresse.form.googlePlus")), 0, 13);
        add(tfGooglePlus, 1, 13);
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabKommunikation");
    }

    @Override
    public String getIconPath() {
        return Images.KOMMUNIKATION_26;
    }

    @Override
    public TextField getTfMobiltelefon() {
        return tfMobiltelefon;
    }

    @Override
    public TextField getTfTelefonPrivat() {
        return tfTelefonPrivat;
    }

    @Override
    public TextField getTfTelefonDienst() {
        return tfTelefonDienst;
    }

    @Override
    public TextField getTfFaxPrivat() {
        return tfFaxPrivat;
    }

    @Override
    public TextField getTfFaxDienst() {
        return tfFaxDienst;
    }

    @Override
    public TextField getTfEmail() {
        return tfEmail;
    }

    @Override
    public TextField getTfHomepage() {
        return tfHomepage;
    }

    @Override
    public TextField getTfSkype() {
        return tfSkype;
    }

    @Override
    public TextField getTfFacebook() {
        return tfFacebook;
    }

    @Override
    public TextField getTfTwitter() {
        return tfTwitter;
    }

    @Override
    public TextField getTfXing() {
        return tfXing;
    }

    @Override
    public TextField getTfLinkedIn() {
        return tfLinkedIn;
    }

    @Override
    public TextField getTfGooglePlus() {
        return tfGooglePlus;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
