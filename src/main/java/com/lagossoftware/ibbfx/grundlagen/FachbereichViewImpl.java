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
import com.lagossoftware.ibbfx.entity.Fachbereich;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Validator;

/**
 * Fachbereich view implementation
 *
 * @author Cem Ikta
 */
public class FachbereichViewImpl extends AbstractBrowserFormView<Fachbereich> 
        implements FachbereichView {

    private TextField tfAbkuerzung;
    private TextField tfName;

    public FachbereichViewImpl() {
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

        tfAbkuerzung= new TextField();
        tfAbkuerzung.setMinWidth(100);
        tfAbkuerzung.setPrefWidth(100);
        tfAbkuerzung.setMaxWidth(100);
        getValidationSupport().registerValidator(tfAbkuerzung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        tfName = new TextField();
        tfName.setPrefWidth(300);
        getValidationSupport().registerValidator(tfName,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.abkuerzung")), 0, 0);
        formPane.add(tfAbkuerzung, 1, 0);

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.name")), 0, 1);
        formPane.add(tfName, 1, 1);

        return formPane;
    }

    @Override
    public TextField getTfAbkuerzung() {
        return tfAbkuerzung;
    }

    @Override
    public TextField getTfName() {
        return tfName;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("fachbereich.title");
    }

}
