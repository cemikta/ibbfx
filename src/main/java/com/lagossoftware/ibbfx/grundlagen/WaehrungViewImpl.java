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
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.controlsfx.validation.Validator;

/**
 * Waehrung view implementation
 *
 * @author Cem Ikta
 */
public class WaehrungViewImpl extends AbstractBrowserFormView<Waehrung> implements WaehrungView {

    private TextField tfCode;
    private TextField tfBezeichnung;

    public WaehrungViewImpl() {
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

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("waehrung.code")), 0, 0);
        tfCode = new TextField();
        tfCode.setPrefWidth(100);
        tfCode.setMinWidth(100);
        tfCode.setPrefWidth(100);
        tfCode.setMaxWidth(100);
        GridPane.setHgrow(tfCode, Priority.NEVER);
        getValidationSupport().registerValidator(tfCode, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfCode, 1, 0);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.bezeichnung")), 0, 1);
        tfBezeichnung = new TextField();
        tfBezeichnung.setPrefWidth(300);
        getValidationSupport().registerValidator(tfBezeichnung, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfBezeichnung, 1, 1);

        return formPane;
    }

    @Override
    public TextField getTfCode() {
        return tfCode;
    }
    
    @Override
    public TextField getTfBezeichnung() {
        return tfBezeichnung;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("waehrung.title");
    }

}
