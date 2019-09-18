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
package com.lagossoftware.ibbfx.grundlagen;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Konto;
import com.lagossoftware.ibbfx.entity.KontoType;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Validator;

/**
 * Konto view implementation
 *
 * @author Cem Ikta
 */
public class KontoViewImpl extends AbstractBrowserFormView<Konto> implements KontoView {

    private TextField tfKontoNr;
    private TextField tfName;
    private TextField tfKlasse;
    private ComboBox<KontoType> cbKontoType;

    public KontoViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }

    @Override
    public GridPane getFormPane() {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("konto.kontoNr")), 0, 0);
        tfKontoNr = new TextField();
        tfKontoNr.setPrefWidth(300);
        getValidationSupport().registerValidator(tfKontoNr, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfKontoNr, 1, 0);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.name")), 0, 1);
        tfName = new TextField();
        tfName.setPrefWidth(300);
        getValidationSupport().registerValidator(tfName, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfName, 1, 1);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("konto.klasse")), 0, 2);
        tfKlasse = new TextField();
        tfKlasse.setPrefWidth(300);
        formPane.add(tfKlasse, 1, 2);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("konto.kontoType")), 0, 3);
        cbKontoType = new ComboBox<>();
        cbKontoType.getItems().addAll(KontoType.AUSGABEN, KontoType.EINNAHMEN);
        cbKontoType.setPrefWidth(300);
        getValidationSupport().registerValidator(cbKontoType, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(cbKontoType, 1, 3);

        return formPane;
    }

    @Override
    public TextField getTfKontoNr() {
        return tfKontoNr;
    }
    
    @Override
    public TextField getTfName() {
        return tfName;
    }

    @Override
    public TextField getTfKlasse() {
        return tfKlasse;
    }

    @Override
    public ComboBox<KontoType> getCbKontoType() {
        return cbKontoType;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("konto.title");
    }

}
