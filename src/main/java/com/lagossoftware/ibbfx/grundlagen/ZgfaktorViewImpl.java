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
import com.lagossoftware.ibbfx.entity.Zgfaktor;
import com.lagossoftware.ibbfx.entity.ZgfaktorBedingung1Type;
import com.lagossoftware.ibbfx.entity.ZgfaktorBedingung2Type;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.controlsfx.validation.Validator;

import java.text.DecimalFormat;

/**
 * Zgfaktor view implementation
 *
 * @author Cem Ikta
 */
public class ZgfaktorViewImpl extends AbstractBrowserFormView<Zgfaktor>
        implements ZgfaktorView {

    private TextField tfBezeichnung;
    private ComboBox<ZgfaktorBedingung1Type> cbBedingung1;
    private ComboBox<ZgfaktorBedingung2Type> cbBedingung2;
    private NumberField nfAufschlag;

    public ZgfaktorViewImpl() {
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

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("zgfaktor.bedingung1")), 0, 1);
        cbBedingung1 = new ComboBox<>();
        cbBedingung1.getItems().addAll(ZgfaktorBedingung1Type.JUGEND_UND_SCHULE, 
                ZgfaktorBedingung1Type.NICHT_JUGEND_UND_SCHULE);
        cbBedingung1.setPrefWidth(300);
        getValidationSupport().registerValidator(cbBedingung1,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(cbBedingung1, 1, 1);

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("zgfaktor.bedingung2")), 0, 2);
        cbBedingung2 = new ComboBox<>();
        cbBedingung2.getItems().addAll(
                ZgfaktorBedingung2Type.WGB_TAGE_GROESSER_NULL, 
                ZgfaktorBedingung2Type.WGB_TAGE_GLEICH_NULL);
        cbBedingung2.setPrefWidth(300);
        getValidationSupport().registerValidator(cbBedingung2,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(cbBedingung2, 1, 2);

        formPane.add(new Label(I18n.GRUNDLAGEN.getString("grundlagen.aufschlag")), 0, 3);
        nfAufschlag = new NumberField(new DecimalFormat("#,##0.00"));
        nfAufschlag.setMinWidth(100);
        nfAufschlag.setPrefWidth(100);
        nfAufschlag.setMaxWidth(100);
        GridPane.setHgrow(nfAufschlag, Priority.NEVER);
        getValidationSupport().registerValidator(nfAufschlag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(nfAufschlag, 1, 3);

        return formPane;
    }

    @Override
    public TextField getTfBezeichnung() {
        return tfBezeichnung;
    }

    @Override
    public ComboBox<ZgfaktorBedingung1Type> getCbBedingung1() {
        return cbBedingung1;
    }

    @Override
    public ComboBox<ZgfaktorBedingung2Type> getCbBedingung2() {
        return cbBedingung2;
    }

    @Override
    public NumberField getNfAufschlag() {
        return nfAufschlag;
    }

    @Override
    public String getHeaderTitle() {
        return I18n.GRUNDLAGEN.getString("zgfaktor.title");
    }

}
