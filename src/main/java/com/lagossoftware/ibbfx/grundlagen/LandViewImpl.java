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
import com.lagossoftware.ibbfx.entity.Land;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.controlsfx.validation.Validator;

/**
 * Land view implementation
 *
 * @author Cem Ikta
 */
public class LandViewImpl extends AbstractBrowserFormView<Land> implements LandView {

    private TextField tfIsocode2;
    private TextField tfIsocode3;
    private TextField tfName;

    public LandViewImpl() {
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

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("land.isocode2")), 0, 0);
        tfIsocode2 = new TextField();
        tfIsocode2.setMinWidth(100);
        tfIsocode2.setPrefWidth(100);
        tfIsocode2.setMaxWidth(100);
        GridPane.setHgrow(tfIsocode2, Priority.NEVER);
        getValidationSupport().registerValidator(tfIsocode2, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfIsocode2, 1, 0);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("land.isocode3")), 0, 1);
        tfIsocode3 = new TextField();
        tfIsocode3.setMinWidth(100);
        tfIsocode3.setPrefWidth(100);
        tfIsocode3.setMaxWidth(100);
        GridPane.setHgrow(tfIsocode3, Priority.NEVER);
        getValidationSupport().registerValidator(tfIsocode3, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfIsocode3, 1, 1);
        
        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.name")), 0, 2);
        tfName = new TextField();
        tfName.setPrefWidth(300);
        getValidationSupport().registerValidator(tfName, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfName, 1, 2);

        return formPane;
    }

    @Override
    public TextField getTfIsocode2() {
        return tfIsocode2;
    }

    @Override
    public TextField getTfIsocode3() {
        return tfIsocode3;
    }
    
    @Override
    public TextField getTfName() {
        return tfName;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("land.title");
    }

}
