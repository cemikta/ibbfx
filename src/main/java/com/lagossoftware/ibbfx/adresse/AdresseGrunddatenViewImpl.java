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
import com.lagossoftware.ibbfx.entity.Anrede;
import com.lagossoftware.ibbfx.entity.Bundesland;
import com.lagossoftware.ibbfx.entity.Land;
import com.lagossoftware.ibbfx.entity.Titel;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

/**
 * Adresse Grunddaten view implementation
 *
 * @author Cem Ikta
 */
public class AdresseGrunddatenViewImpl extends GridPane implements AdresseGrunddatenView {

    private TextField tfAdresseNr;
    private ComboBox<Anrede> cbAnrede;
    private ComboBox<Titel> cbTitel;
    private TextField tfVorname;
    private TextField tfNachname;
    private TextField tfFirma1;
    private TextField tfFirma2;
    private TextField tfFirma3;
    private TextField tfFirma4;
    private TextField tfKennzeichen1;
    private TextField tfKennzeichen2;
    
    private TextField tfPostfachPlz;
    private TextField tfPostfach;
    private TextField tfStrasse;
    private TextField tfPlz;
    private TextField tfOrt;
    private ComboBox<Land> cbLand;
    private ComboBox<Bundesland> cbBundesland;
    private TextField tfAnredeBrief;
    
    private ValidationSupport validationSupport;
 
    public AdresseGrunddatenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(10);

        tfAdresseNr = new TextField();
        tfAdresseNr.setPrefWidth(100);
        tfAdresseNr.setMinWidth(100);
        tfAdresseNr.setMaxWidth(100);
        tfAdresseNr.setEditable(false);

        cbAnrede = new ComboBox<>();
        cbAnrede.setPrefWidth(100);
        getValidationSupport().registerValidator(cbAnrede,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        cbTitel = new ComboBox<>();
        cbTitel.setPrefWidth(200);
        tfVorname = new TextField();
        tfVorname.setPrefWidth(300);
        getValidationSupport().registerValidator(tfVorname, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfNachname = new TextField();
        tfNachname.setPrefWidth(300);
        getValidationSupport().registerValidator(tfNachname, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        tfFirma1 = new TextField();
        tfFirma1.setPrefWidth(300);
        tfFirma2 = new TextField();
        tfFirma2.setPrefWidth(300);
        tfFirma3 = new TextField();
        tfFirma3.setPrefWidth(300);
        tfFirma4 = new TextField();
        tfFirma4.setPrefWidth(300);
        tfKennzeichen1 = new TextField();
        tfKennzeichen1.setPrefWidth(100);
        tfKennzeichen2 = new TextField();
        tfKennzeichen2.setPrefWidth(200);
        
        tfPostfachPlz = new TextField();
        tfPostfachPlz.setPrefWidth(100);
        tfPostfach = new TextField();
        tfPostfach.setPrefWidth(200);
        tfStrasse = new TextField();
        tfStrasse.setPrefWidth(300);
        tfPlz = new TextField();
        tfPlz.setPrefWidth(100);
        tfOrt = new TextField();
        tfOrt.setPrefWidth(200);
        cbLand = new ComboBox<>();
        cbLand.setPrefWidth(310);
        getValidationSupport().registerValidator(cbLand,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        cbBundesland = new ComboBox<>();
        cbBundesland.setPromptText(I18n.COMMON.getString("combobox.select"));
        cbBundesland.setPrefWidth(310);
        tfAnredeBrief = new TextField();
        tfAnredeBrief.setPrefWidth(300);


        add(new Label(I18n.ADRESSE.getString("adresse.form.adresseNr")), 0, 0);
        add(tfAdresseNr, 1, 0);
        add(new Label(I18n.ADRESSE.getString("adresse.form.anredeTitel")), 0, 1);
        add(cbAnrede, 1, 1);
        add(cbTitel, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.vorname")), 0, 2);
        add(tfVorname, 1, 2, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.nachname")), 0, 3);
        add(tfNachname, 1, 3, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.firma")), 0, 4);
        add(tfFirma1, 1, 4, 2, 1);
        add(tfFirma2, 1, 5, 2, 1);
        add(tfFirma3, 1, 6, 2, 1);
        add(tfFirma4, 1, 7, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.kennzeichen")), 0, 8);
        add(tfKennzeichen1, 1, 8);
        add(tfKennzeichen2, 2, 8);
        
        add(new TitledSeparator(I18n.ADRESSE.getString("adresse.form.anschrift")), 0, 9, 3, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.postfach")), 0, 10);
        add(tfPostfachPlz, 1, 10);
        add(tfPostfach, 2, 10);
        add(new Label(I18n.ADRESSE.getString("adresse.form.strasse")), 0, 11);
        add(tfStrasse, 1, 11, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.plzOrt")), 0, 12);
        add(tfPlz, 1, 12);
        add(tfOrt, 2, 12);
        add(new Label(I18n.ADRESSE.getString("adresse.form.land")), 0, 13);
        add(cbLand, 1, 13, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.bundesland")), 0, 14);
        add(cbBundesland, 1, 14, 2, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.anredeBrief")), 0, 15);
        add(tfAnredeBrief, 1, 15, 2, 1);
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabGrunddaten");
    }

    @Override
    public String getIconPath() {
        return Images.GRUNDDATEN_26;
    }

    @Override
    public TextField getTfAdresseNr() {
        return tfAdresseNr;
    }

    @Override
    public ComboBox<Anrede> getCbAnrede() {
        return cbAnrede;
    }

    @Override
    public ComboBox<Titel> getCbTitel() {
        return cbTitel;
    }

    @Override
    public TextField getTfVorname() {
        return tfVorname;
    }

    @Override
    public TextField getTfNachname() {
        return tfNachname;
    }

    @Override
    public TextField getTfFirma1() {
        return tfFirma1;
    }

    @Override
    public TextField getTfFirma2() {
        return tfFirma2;
    }

    @Override
    public TextField getTfFirma3() {
        return tfFirma3;
    }

    @Override
    public TextField getTfFirma4() {
        return tfFirma4;
    }

    @Override
    public TextField getTfKennzeichen1() {
        return tfKennzeichen1;
    }

    @Override
    public TextField getTfKennzeichen2() {
        return tfKennzeichen2;
    }

    @Override
    public TextField getTfPostfachPlz() {
        return tfPostfachPlz;
    }

    @Override
    public TextField getTfPostfach() {
        return tfPostfach;
    }

    @Override
    public TextField getTfStrasse() {
        return tfStrasse;
    }

    @Override
    public TextField getTfPlz() {
        return tfPlz;
    }

    @Override
    public TextField getTfOrt() {
        return tfOrt;
    }

    @Override
    public ComboBox<Land> getCbLand() {
        return cbLand;
    }

    @Override
    public ComboBox<Bundesland> getCbBundesland() {
        return cbBundesland;
    }

    @Override
    public TextField getTfAnredeBrief() {
        return tfAnredeBrief;
    }

    @Override
    public ValidationSupport getValidationSupport() {
        if (validationSupport == null) {
            validationSupport = new ValidationSupport();
            validationSupport.setValidationDecorator(new GraphicValidationDecoration());
        }
        
        return validationSupport;
    }

    @Override
    public Node asNode() {
        return this;
    }
    
}
