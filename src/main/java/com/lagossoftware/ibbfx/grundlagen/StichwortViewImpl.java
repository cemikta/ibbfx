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
import com.lagossoftware.ibbfx.entity.Stichwort;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Validator;

/**
 * Stichwort view implementation
 *
 * @author Cem Ikta
 */
public class StichwortViewImpl extends AbstractBrowserFormView<Stichwort> 
        implements StichwortView {

    private TextField tfBezeichnung;

    public StichwortViewImpl() {
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

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.bezeichnung")), 0, 0);
        tfBezeichnung = new TextField();
        tfBezeichnung.setPrefWidth(300);
        getValidationSupport().registerValidator(tfBezeichnung, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfBezeichnung, 1, 0);

        return formPane;
    }

    @Override
    public TextField getTfBezeichnung() {
        return tfBezeichnung;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("stichwort.title");
    }

}
